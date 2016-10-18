package com.kyyc.generator.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import org.apache.commons.lang3.StringUtils;

public class JarFileReaderUtil {

	public static void unCompressJar(String jarFilePath) {
		JarEntry jar;
		try {
			
			String unCompressFilePath = StringUtils.removeEnd(jarFilePath, ".jar");
			File unCompressFile = new File(unCompressFilePath);
			if (!unCompressFile.exists()) {
				unCompressFile.mkdirs();
			}

			FileOutputStream out;
			@SuppressWarnings("resource")
			JarInputStream jarIn = new JarInputStream(new FileInputStream(jarFilePath));

			while ((jar = jarIn.getNextJarEntry()) != null) {
				String name = jar.getName();
				if (jar.isDirectory()) {
					name = name.substring(0, name.length() - 1);
					File file = new File(unCompressFilePath + "/" + name);
					file.mkdir();
				} else {
					if (name.lastIndexOf("/") != -1) {
						String fname = unCompressFilePath  + "/" +  jar.getName().substring(0, jar.getName().lastIndexOf("/"));
						File dt = new File(fname);
						if (!dt.exists()) {
							dt.mkdirs();
						}
					}
					File file = new File(unCompressFilePath  + "/" +  jar.getName());
					file.createNewFile();
					out = new FileOutputStream(file);
					int b;
					while ((b = jarIn.read()) != -1) {
						out.write(b);
					}
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}