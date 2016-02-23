package com.szse.po.dao;

/**
 * Systemlog entity. @author MyEclipse Persistence Tools
 */

public class Systemlog implements java.io.Serializable {

	// Fields

	private Integer sid;
	private String time;
	private String category;
	private String content;

	// Constructors

	/** default constructor */
	public Systemlog() {
	}

	/** full constructor */
	public Systemlog(String time, String category, String content) {
		this.time = time;
		this.category = category;
		this.content = content;
	}

	// Property accessors

	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}