package com.kyyc.generator.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 文件操作代码
 */
public class FileUtil {
	/**
	 * 将文本文件中的内容读入到buffer中
	 * 
	 * @param filePath
	 *            文件路径
	 * @throws IOException
	 *             异常
	 */
	public static String readToBuffer(String filePath) throws IOException {
		StringBuffer buffer = new StringBuffer();
		InputStream is = new FileInputStream(filePath);
		String line; // 用来保存每行读取的内容
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		line = reader.readLine(); // 读取第一行
		while (line != null) { // 如果 line 为空说明读完了
			buffer.append(line); // 将读到的内容添加到 buffer 中
			buffer.append("\n"); // 添加换行符
			line = reader.readLine(); // 读取下一行
		}
		reader.close();
		is.close();
		return buffer.toString();
	}
}
