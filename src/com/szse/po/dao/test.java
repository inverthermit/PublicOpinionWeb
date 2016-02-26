package com.szse.po.dao;

public class test {
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Textdata u=new Textdata();
		//u.setMid(13);
		u.setContent("王尼玛王尼玛");
		u.setUrl("fewf");
		TextdataDAO ud=new TextdataDAO();
		ud.save(u);
		System.out.println(u.getTid());
		
		TextdataDAO ud1=new TextdataDAO();
		Textdata td=ud1.findById(3);
		String a=(td.getContent());
		System.out.println(a);
		
	}

}
