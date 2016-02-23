package com.szse.po.dao;

/**
 * Userconfig entity. @author MyEclipse Persistence Tools
 */

public class Userconfig implements java.io.Serializable {

	// Fields

	private Integer cid;
	private String uid;
	private String config;

	// Constructors

	/** default constructor */
	public Userconfig() {
	}

	/** full constructor */
	public Userconfig(String uid, String config) {
		this.uid = uid;
		this.config = config;
	}

	// Property accessors

	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getConfig() {
		return this.config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

}