package com.szse.po.dao;

/**
 * Miningresult entity. @author MyEclipse Persistence Tools
 */

public class Miningresult implements java.io.Serializable {

	// Fields

	private Integer mid;
	private Integer tid;
	private Double psentiment;
	private Double nsentiment;
	private Integer textNum;
	private String keywords;
	private String updatetime;
	private String lcrelated;
	private String lcname;
	private String categoryid;
	private String industry;
	private String region;

	// Constructors

	/** default constructor */
	public Miningresult() {
	}

	/** minimal constructor */
	public Miningresult(Integer tid, String updatetime) {
		this.tid = tid;
		this.updatetime = updatetime;
	}

	/** full constructor */
	public Miningresult(Integer tid, Double psentiment, Double nsentiment,
			Integer textNum, String keywords, String updatetime,
			String lcrelated, String lcname, String categoryid,
			String industry, String region) {
		this.tid = tid;
		this.psentiment = psentiment;
		this.nsentiment = nsentiment;
		this.textNum = textNum;
		this.keywords = keywords;
		this.updatetime = updatetime;
		this.lcrelated = lcrelated;
		this.lcname = lcname;
		this.categoryid = categoryid;
		this.industry = industry;
		this.region = region;
	}

	// Property accessors

	public Integer getMid() {
		return this.mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public Integer getTid() {
		return this.tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public Double getPsentiment() {
		return this.psentiment;
	}

	public void setPsentiment(Double psentiment) {
		this.psentiment = psentiment;
	}

	public Double getNsentiment() {
		return this.nsentiment;
	}

	public void setNsentiment(Double nsentiment) {
		this.nsentiment = nsentiment;
	}

	public Integer getTextNum() {
		return this.textNum;
	}

	public void setTextNum(Integer textNum) {
		this.textNum = textNum;
	}

	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getLcrelated() {
		return this.lcrelated;
	}

	public void setLcrelated(String lcrelated) {
		this.lcrelated = lcrelated;
	}

	public String getLcname() {
		return this.lcname;
	}

	public void setLcname(String lcname) {
		this.lcname = lcname;
	}

	public String getCategoryid() {
		return this.categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

}