package com.szse.po.service.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.szse.po.dao.Miningresult;
import com.szse.po.dao.MiningresultDAO;
import com.szse.po.dao.Textdata;
import com.szse.po.dao.TextdataDAO;
import com.szse.po.sentiment.analysis.NLPIR.code.*;
import com.szse.po.service.classification.Data;

public class SaveTools {
	public static void main(String[] args)
	{
		//saveKeywords();
		saveVector();
	}

	public static void saveKeywords()//Save NLPIR output Keywords to 'miningresult'
	{
		Textdata td=new Textdata();
		TextdataDAO tdd=new TextdataDAO();
		Miningresult mr=null;
		MiningresultDAO mrd=new MiningresultDAO();
		List<Textdata> l=tdd.findAll();
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		for(int i=0;i<l.size();i++)
		{
			td=l.get(i);
			
			String text=td.getTitle()+" "+td.getContent();
			if(!text.equals(""))
			{
				mr=(Miningresult)mrd.findByTid(td.getTid()).get(0);
				mr.setKeywords(NlpirDemo.getKeywords(text));
				mr.setUpdatetime(time);
				mrd.save(mr);
			}
			
			
			
		}
	}
	
	public static void saveVector()//Save Content Vector 'textdata'
	{
		String argu = "D:\\MyEclipse2015WorkSpace\\JnaTest_NLPIR\\ICTCLAS2015\\";
		// String system_charset = "GBK";//GBK----0
		NlpirDemo nd=new NlpirDemo(argu);
		Textdata td=null;
		Textdata tdtemp=null;
		TextdataDAO tdd=new TextdataDAO();
		List<Textdata> l=tdd.findAll();
		for(int i=0;i<l.size();i++)
		{
			td=l.get(i);
			
			String text=td.getTitle()+" "+td.getContent();
			//System.out.println(text);
			if(!text.equals(""))
			{
				ArrayList<String> list=nd.parseWordReturnList(text);
				StringBuffer sb=new StringBuffer();
				for(int k=0;k<list.size();k++)
				{
					sb.append(list.get(k));
					if(k<list.size()-1)
					{
						sb.append(" ");
					}
				}
				//This saving method is extremely strange!
				/*
				//System.out.println(new String(sb));
				td.setVector(new String(sb));
				//tdd=new TextdataDAO();
				System.out.println(td.getTid());
				tdd.save(td);
				System.out.println(td.getTid());
				*/
				tdtemp=(Textdata)tdd.findById(td.getTid());
				//System.out.println(td.getTid());
				//td.setTid(td.getTid());
				tdtemp.setVector(new String(sb));
				tdd.save(tdtemp);
				//System.out.println(tdtemp.getTid());
				
				
			}
			
			
			
		}
	}
	
	public static void saveRIB()//Save Region,Industry,Board to 'miningresult'
	{
		
	}

}
