package com.szse.po.dao.tools;

import java.net.MalformedURLException;
import java.net.URL;

public class test {

	public static void main(String[] argu) throws Exception
	{
		URL url=new URL("http://guba.eastmoney.com/news,002786,271462873.html");
		Readability r=new Readability(url,2000);
		r.init();
		String cleanHtml = r.outerHtml();
		System.out.println(cleanHtml);
	}
}
