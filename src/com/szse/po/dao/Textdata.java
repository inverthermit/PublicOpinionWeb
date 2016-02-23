package com.szse.po.dao;

/**
 * Textdata entity. @author MyEclipse Persistence Tools
 */

public class Textdata implements java.io.Serializable {

	// Fields

	private Integer tid;
	private String url;
	private String time;
	private String content;
	private String vector;

	// Constructors

	/** default constructor */
	public Textdata() {
	}

	/** full constructor */
	public Textdata(String url, String time, String content, String vector) {
		this.url = url;
		this.time = time;
		this.content = content;
		this.vector = vector;
	}

	// Property accessors

	public Integer getTid() {
		return this.tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getVector() {
		return this.vector;
	}

	public void setVector(String vector) {
		this.vector = vector;
	}

}