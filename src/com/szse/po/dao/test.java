package com.szse.po.dao;
import java.net.URL;

import com.szse.po.dao.tools.*;
public class test {
	 
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		
		Readability r=new Readability(new URL("http://guba.eastmoney.com/news,300168,262955298.html"),3000);
		r.init();
		String cleanHtml = r.outerHtml();
		System.out.println(cleanHtml);
		
		
		
		
		
	}
	
	public static void DAOtest()
	{
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
