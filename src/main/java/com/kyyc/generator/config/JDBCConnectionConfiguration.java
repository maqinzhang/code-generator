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
@XmlRootElement(name="jdbcConnection")
public class JDBCConnectionConfiguration implements Serializable {

	private static final long serialVersionUID = 4279990274677396471L;

	@XmlElement(name = "driverClass")
	private String driverClass;

	@XmlElement(name = "connectionURL")
	private String connectionURL;

	@XmlElement(name = "username")
	private String username;

	@XmlElement(name = "password")
	private String password;

	/**
	 * @return the driverClass
	 */
	public String getDriverClass() {
		return driverClass;
	}

	/**
	 * @param driverClass
	 *            the driverClass to set
	 */
	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	/**
	 * @return the connectionURL
	 */
	public String getConnectionURL() {
		return connectionURL;
	}

	/**
	 * @param connectionURL
	 *            the connectionURL to set
	 */
	public void setConnectionURL(String connectionURL) {
		this.connectionURL = connectionURL;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
