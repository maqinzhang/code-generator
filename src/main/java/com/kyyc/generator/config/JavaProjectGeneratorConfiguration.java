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
@XmlRootElement(name = "javaModelGenerator")
public class JavaProjectGeneratorConfiguration implements Serializable {

	private static final long serialVersionUID = 759822426364496006L;

	@XmlElement(name = "targetPackage")
	private String targetPackage;

	@XmlElement(name = "targetProject")
	private String targetProject;

	@XmlElement(name = "targetWebView")
	private String targetWebView;

	/**
	 * @return the targetPackage
	 */
	public String getTargetPackage() {
		return targetPackage;
	}

	/**
	 * @param targetPackage
	 *            the targetPackage to set
	 */
	public void setTargetPackage(String targetPackage) {
		this.targetPackage = targetPackage;
	}

	/**
	 * @return the targetProject
	 */
	public String getTargetProject() {
		return targetProject;
	}

	/**
	 * @param targetProject
	 *            the targetProject to set
	 */
	public void setTargetProject(String targetProject) {
		this.targetProject = targetProject;
	}

	/**
	 * @return the targetWebView
	 */
	public String getTargetWebView() {
		return targetWebView;
	}

	/**
	 * @param targetWebView
	 *            the targetWebView to set
	 */
	public void setTargetWebView(String targetWebView) {
		this.targetWebView = targetWebView;
	}
}