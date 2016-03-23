package com.szse.po.service.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
		saveKeywords();
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
	
	public static void saveRIB()//Save Region,Industry,Board to 'miningresult'
	{
		
	}

}
