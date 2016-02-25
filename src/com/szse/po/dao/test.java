package com.szse.po.dao;

public class test {
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Textdata u=new Textdata();
		//u.setMid(13);
		u.setContent("awdf");
		u.setUrl("fewf");
		TextdataDAO ud=new TextdataDAO();
		ud.save(u);
		System.out.println(u.getTid());
	}

}
