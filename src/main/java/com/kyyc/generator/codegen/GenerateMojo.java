package com.kyyc.generator.codegen;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import com.kyyc.generator.config.GeneratorConfiguration;
import com.kyyc.generator.util.FileUtil;
import com.kyyc.generator.util.JaxbUtil;
import com.kyyc.generator.util.JaxbUtil.CollectionWrapper;

/**
 * 
 * 自定义插件
 * 
 * <p>
 * use cmd：<br/>
 * mvn code-generator:generate <br/>
 * mvn com.kyyc.generator:code-generator-maven-plugin:0.0.1-SNAPSHOT:generate
 * </p>
 * 
 * @author MaQinZh
 * @requiresDependencyResolution runtime
 */
@Mojo(name = "generate")
public class GenerateMojo extends AbstractMojo {

	@Parameter(defaultValue = "${configurationFile}")
	private String configurationFile;

	@Component
	private MavenProject project;

	private ClassLoader getClassLoader() throws MojoExecutionException {
		try {
			List<String> classpathElements = project.getCompileClasspathElements();
			classpathElements.add(project.getBuild().getOutputDirectory());
			classpathElements.add(project.getBuild().getTestOutputDirectory());
			URL urls[] = new URL[classpathElements.size()];

			for (int i = 0; i < classpathElements.size(); ++i) {
				urls[i] = new File((String) classpathElements.get(i)).toURI().toURL();
			}
			return new URLClassLoader(urls, getClass().getClassLoader());
		} catch (Exception e)// gotta catch em all
		{
			throw new MojoExecutionException("Couldn't create a classloader.", e);
		}
	}

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {

		System.out.println("代码开始生成，请稍等！！！");
		File configFile = new File(configurationFile);
		if (!configFile.exists()) {
			throw new MojoExecutionException("配置文件不存在！");
		}
		String xmlString = "";
		try {
			xmlString = FileUtil.readToBuffer(configurationFile);
		} catch (IOException e) {
			throw new MojoExecutionException("解析配置文件内容出错！", e);
		}

		/**
		 * XML转换成对象
		 */
		JaxbUtil resultBinder = new JaxbUtil(GeneratorConfiguration.class, CollectionWrapper.class);
		GeneratorConfiguration generatorConfiguration = resultBinder.fromXml(xmlString);

		System.out.println("顺利解析XML配置文件！");

		Thread currentThread = Thread.currentThread();
		ClassLoader oldClassLoader = currentThread.getContextClassLoader();
		try {
			/**
			 * 替换ClassLoader
			 */
			currentThread.setContextClassLoader(getClassLoader());
			/**
			 * 执行业务调用
			 */
			CodeGen.gen(generatorConfiguration);
		} catch (Exception e) {
			throw new MojoExecutionException("生成代码的时候出错！", e);
		} finally {
			currentThread.setContextClassLoader(oldClassLoader);
		}
		System.out.println("代码顺利生成，请查看！！！");
	}

	/**
	 * @return the configurationFile
	 */
	public String getConfigurationFile() {
		return configurationFile;
	}

	/**
	 * @param configurationFile
	 *            the configurationFile to set
	 */
	public void setConfigurationFile(String configurationFile) {
		this.configurationFile = configurationFile;
	}
}
