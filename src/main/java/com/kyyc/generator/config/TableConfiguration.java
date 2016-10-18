package com.kyyc.generator.config;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author MaQinZh
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "table")
public class TableConfiguration implements Serializable {

	private static final long serialVersionUID = -8638828421944900088L;

	@XmlElement(name = "module")
	private String module;

	@XmlElement(name = "tableName")
	private String tableName;

	@XmlElement(name = "tableDesc")
	private String tableDesc;

	/**
	 * @return the module
	 */
	public String getModule() {
		return module;
	}

	/**
	 * @param module
	 *            the module to set
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName
	 *            the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the tableDesc
	 */
	public String getTableDesc() {
		return tableDesc;
	}

	/**
	 * @param tableDesc
	 *            the tableDesc to set
	 */
	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc;
	}
}