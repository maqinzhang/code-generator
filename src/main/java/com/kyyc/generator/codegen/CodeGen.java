package com.kyyc.generator.codegen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.codehaus.plexus.util.FileUtils;
import org.codehaus.plexus.util.StringUtils;

import com.kyyc.generator.codegen.SpyUtil.TableMetaData;
import com.kyyc.generator.config.AuthorConfiguration;
import com.kyyc.generator.config.GeneratorConfiguration;
import com.kyyc.generator.config.TableConfiguration;
import com.kyyc.generator.util.FieldCodeUtil;
import com.kyyc.generator.util.JarFileReaderUtil;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public final class CodeGen {

	private static final String TEMPLATE_PATH = "ftl";
	private static final List<String> TYPES = new ArrayList<String>();

	private static Configuration CFG = new Configuration(Configuration.VERSION_2_3_22);

	private static File templates = null;

	static {
		TYPES.add(0, "Model.ftl");
		TYPES.add(1, "Mapper.ftl");
		TYPES.add(2, "Mapper.xml.ftl");
		TYPES.add(3, "Service.ftl");
		TYPES.add(4, "Controller.ftl");
		TYPES.add(5, "View_List.ftl");
		TYPES.add(6, "View_ListContent.ftl");
		TYPES.add(7, "View_Info.ftl");
		TYPES.add(8, "View_Detail.ftl");

		String clsPath = CodeGen.class.getClassLoader().getResource(TEMPLATE_PATH).getFile();
		String jarPath = StringUtils.substring(clsPath, 0, clsPath.indexOf("!")).replace("file:/", "");
		JarFileReaderUtil.unCompressJar(jarPath);
		String ftlPath = clsPath.replace(".jar!", "").replace("file:/", "");
		templates = new File(ftlPath);
		try {
			// 设置模板目录
			CFG.setDirectoryForTemplateLoading(templates);
			// 设置默认编码格式
			CFG.setLocale(Locale.CHINA);
			CFG.setDefaultEncoding("UTF-8");
		} catch (IOException e) {
			System.out.println("加载freemark配置文件出错！" + e);
		}
		CFG.setObjectWrapper(new DefaultObjectWrapper(Configuration.getVersion()));
	}

	private static void process(String tmpName, String projectOutput, String webViewOutput, Map<String, Object> dataMap) throws Exception {
		String module = dataMap.get("module").toString();
		String classname = dataMap.get("classname").toString();
		String _package = dataMap.get("package").toString().replace(".", "/");
		StringBuilder projectBuilder = new StringBuilder("");
		StringBuilder webViewBuilder = new StringBuilder("");
		Template template = CFG.getTemplate(tmpName);
		int type = TYPES.indexOf(tmpName);
		switch (type) {
		case 1:
			projectBuilder.append(projectOutput).append("/").append(_package).append("/dao/").append(module).append("/");
			FileUtils.forceMkdir(new File(projectBuilder.toString()));
			projectBuilder.append(classname).append("Mapper").append(".java");
			break;
		case 2:
			projectBuilder.append(projectOutput).append("/").append(_package).append("/dao/").append(module).append("/mapper/");
			FileUtils.forceMkdir(new File(projectBuilder.toString()));
			projectBuilder.append(classname).append("Mapper").append(".xml");
			break;
		case 3:
			projectBuilder.append(projectOutput).append("/").append(_package).append("/service/").append(module).append("/");
			FileUtils.forceMkdir(new File(projectBuilder.toString()));
			projectBuilder.append(classname).append("Service").append(".java");
			break;
		case 4:
			projectBuilder.append(projectOutput).append("/").append(_package).append("/controller/").append(module).append("/");
			FileUtils.forceMkdir(new File(projectBuilder.toString()));
			projectBuilder.append(classname).append("Controller").append(".java");
			break;
		case 5:
			webViewBuilder.append(webViewOutput).append("/").append(module).append("/");
			FileUtils.forceMkdir(new File(webViewBuilder.toString()));
			webViewBuilder.append(FieldCodeUtil.firstCharacterToLower(classname)).append("List").append(".jsp");
			break;
		case 6:
			webViewBuilder.append(webViewOutput).append("/").append(module).append("/");
			FileUtils.forceMkdir(new File(webViewBuilder.toString()));
			webViewBuilder.append(FieldCodeUtil.firstCharacterToLower(classname)).append("ListContent").append(".jsp");
			break;
		case 7:
			webViewBuilder.append(webViewOutput).append("/").append(module).append("/");
			FileUtils.forceMkdir(new File(webViewBuilder.toString()));
			webViewBuilder.append(FieldCodeUtil.firstCharacterToLower(classname)).append("Info").append(".jsp");
			break;
		case 8:
			webViewBuilder.append(webViewOutput).append("/").append(module).append("/");
			FileUtils.forceMkdir(new File(webViewBuilder.toString()));
			webViewBuilder.append(FieldCodeUtil.firstCharacterToLower(classname)).append("Detail").append(".jsp");
			break;
		default:
			projectBuilder.append(projectOutput).append("/").append(_package).append("/model/").append(module).append("/");
			FileUtils.forceMkdir(new File(projectBuilder.toString()));
			projectBuilder.append(classname).append(".java");
			break;
		}
		if (!"".equals(projectBuilder.toString())) {
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(
					projectBuilder.toString())), "UTF-8"));
			template.process(dataMap, out);
			out.flush();
			out.close();
		}

		if (!"".equals(webViewBuilder.toString())) {
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(
					webViewBuilder.toString())), "UTF-8"));
			template.process(dataMap, out);
			out.flush();
			out.close();
		}
	}

	/**
	 * 执行方法、生成模块代码
	 * 
	 * <pre>
	 * </pre>
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void gen(GeneratorConfiguration configuration) throws Exception {

		List<TableConfiguration> tableList = configuration.getTableConfigurations();
		for (TableConfiguration table : tableList) {

			String tableName = table.getTableName();

			String module = table.getModule();
			String classname = FieldCodeUtil.firstCharacterToUpper(FieldCodeUtil.getFieldName(tableName));
			String id = FieldCodeUtil.getFieldName(table.getTablePk());
			String moduleDescr = table.getTableDesc();
			AuthorConfiguration author = configuration.getAuthorConfiguration();

			String projectOutput = configuration.getJavaProjectGeneratorConfiguration().getTargetProject();
			String packageOutput = configuration.getJavaProjectGeneratorConfiguration().getTargetPackage();
			String webViewOutput = configuration.getJavaProjectGeneratorConfiguration().getTargetWebView();

			String createDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("author", author);
			dataMap.put("createDate", createDate);
			dataMap.put("module", module);
			dataMap.put("classname", classname);
			dataMap.put("id", id);
			dataMap.put("moduleDescr", moduleDescr);
			dataMap.put("package", packageOutput);

			TableMetaData tableMetaData = SpyUtil.spyDB(configuration.getJdbcConnectionConfiguration(), tableName);
			dataMap.put("tableMetaData", tableMetaData);

			File[] files = templates.listFiles();

			for (File file : files) {
				process(file.getName(), projectOutput, webViewOutput, dataMap);
			}
		}
	}
}