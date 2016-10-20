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

	@XmlElement(name = "tableName")
	private String tableName;

	@XmlElement(name = "tablePk")
	private String tablePk;

	@XmlElement(name = "tableDesc")
	private String tableDesc;

	@XmlElement(name = "module")
	private String module;

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return this.tableName;
	}

	/**
	 * @param tableName
	 *            the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the tablePk
	 */
	public String getTablePk() {
		return this.tablePk;
	}

	/**
	 * @param tablePk
	 *            the tablePk to set
	 */
	public void setTablePk(String tablePk) {
		this.tablePk = tablePk;
	}

	/**
	 * @return the tableDesc
	 */
	public String getTableDesc() {
		return this.tableDesc;
	}

	/**
	 * @param tableDesc
	 *            the tableDesc to set
	 */
	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc;
	}

	/**
	 * @return the module
	 */
	public String getModule() {
		return this.module;
	}

	/**
	 * @param module
	 *            the module to set
	 */
	public void setModule(String module) {
		this.module = module;
	}
}