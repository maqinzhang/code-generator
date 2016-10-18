package com.kyyc.generator.config;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author MaQinZh
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "generatorConfiguration")
public class GeneratorConfiguration implements Serializable {

	private static final long serialVersionUID = -4828463109286773051L;

	@XmlElement(name = "id")
	private String id;

	@XmlElement(name = "jdbcConnection")
	private JDBCConnectionConfiguration jdbcConnectionConfiguration;

	@XmlElement(name = "javaProjectGenerator")
	private JavaProjectGeneratorConfiguration javaProjectGeneratorConfiguration;

	@XmlElement(name = "table")
	private ArrayList<TableConfiguration> tableConfigurations;

	@XmlElement(name = "author")
	private AuthorConfiguration authorConfiguration;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the jdbcConnectionConfiguration
	 */
	public JDBCConnectionConfiguration getJdbcConnectionConfiguration() {
		return jdbcConnectionConfiguration;
	}

	/**
	 * @param jdbcConnectionConfiguration
	 *            the jdbcConnectionConfiguration to set
	 */
	public void setJdbcConnectionConfiguration(JDBCConnectionConfiguration jdbcConnectionConfiguration) {
		this.jdbcConnectionConfiguration = jdbcConnectionConfiguration;
	}

	/**
	 * @return the javaProjectGeneratorConfiguration
	 */
	public JavaProjectGeneratorConfiguration getJavaProjectGeneratorConfiguration() {
		return javaProjectGeneratorConfiguration;
	}

	/**
	 * @param javaProjectGeneratorConfiguration
	 *            the javaProjectGeneratorConfiguration to set
	 */
	public void setJavaProjectGeneratorConfiguration(JavaProjectGeneratorConfiguration javaProjectGeneratorConfiguration) {
		this.javaProjectGeneratorConfiguration = javaProjectGeneratorConfiguration;
	}

	/**
	 * @return the tableConfigurations
	 */
	public ArrayList<TableConfiguration> getTableConfigurations() {
		return tableConfigurations;
	}

	/**
	 * @param tableConfigurations
	 *            the tableConfigurations to set
	 */
	public void setTableConfigurations(ArrayList<TableConfiguration> tableConfigurations) {
		this.tableConfigurations = tableConfigurations;
	}

	/**
	 * @return the authorConfiguration
	 */
	public AuthorConfiguration getAuthorConfiguration() {
		return authorConfiguration;
	}

	/**
	 * @param authorConfiguration
	 *            the authorConfiguration to set
	 */
	public void setAuthorConfiguration(AuthorConfiguration authorConfiguration) {
		this.authorConfiguration = authorConfiguration;
	}
}
