package com.szse.po.dao;

/**
 * Outputresult entity. @author MyEclipse Persistence Tools
 */

public class Outputresult implements java.io.Serializable {

	// Fields

	private Integer oid;
	private String time;
	private String content;

	// Constructors

	/** default constructor */
	public Outputresult() {
	}

	/** full constructor */
	public Outputresult(String time, String content) {
		this.time = time;
		this.content = content;
	}

	// Property accessors

	public Integer getOid() {
		return this.oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}