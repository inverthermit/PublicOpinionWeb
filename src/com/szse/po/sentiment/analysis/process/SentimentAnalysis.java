package com.szse.po.sentiment.analysis.process;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.szse.po.dao.Miningresult;
import com.szse.po.dao.MiningresultDAO;
import com.szse.po.dao.Textdata;
import com.szse.po.dao.TextdataDAO;
public class SentimentAnalysis {

	/**
	 * @param args
	 */

	public static void analyseAll()
	{
		Textdata td=new Textdata();
		TextdataDAO tdd=new TextdataDAO();
		Miningresult mr=new Miningresult();
		MiningresultDAO mrd=new MiningresultDAO();
		List<Textdata> l=tdd.findAll();
		HashMap<String,Integer> codemap=new HashMap<String,Integer>();
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		for(int i=0;i<l.size();i++)
		{
			td=l.get(i);
			mr=(Miningresult)mrd.findByTid(td.getTid()).get(0);
			HashMap<String,String> paraSent=anaylse(td.getTitle()+" "+td.getContent());
			String ssent=paraSent.get("S");
			if(ssent!=null&&ssent.split(",").length==2)
			{
				mr.setPsentiment(Double.parseDouble(ssent.split(",")[0]));
				mr.setNsentiment(Double.parseDouble(ssent.split(",")[1]));
				mr.setUpdatetime(time);
				mrd.save(mr);
			}
			/*	mr.setTid(td.getTid());
				mr.setLcname(code);
				mr.setLcrelated("1");
				
				mr.setUpdatetime(time);
				mrd.save(mr);
				
				if(!codemap.containsKey(code))
				{
					codemap.put(code, 1);
				}
				else
				{
					codemap.put(code, codemap.get(code)+1);
				}*/
			
			
			
		}
		
	}

	
	
	public static HashMap<String,String> anaylse(String paragragh) {
		// TODO Auto-generated method stub

		HashMap<String,String> final_result=new HashMap<String,String>();
		SumCalculation sumcal=new SumCalculation();
		sumcal.dicInit(Config.ROOT_PATH);
		ArrayList<int[]> result=sumcal.scoreParagragh(paragragh);
		//System.out.println(result[0]+","+result[1]);
		
		//Average Calculation
		int pos=0,neg=0;
		for(int[] seg:result)
		{
			pos+=seg[0];
			neg+=seg[1];
		}
		double posAverage=pos/(result.size()+0.0);
		double negAverage=neg/(result.size()+0.0);
		
		
		//Variance calculation
		double posVariance=0;
		double negVariance=0;
		for(int[] seg:result)
		{
			posVariance+=(seg[0]-posAverage)*(seg[0]-posAverage);
			negVariance+=(seg[1]-negAverage)*(seg[1]-negAverage);
		}
		posVariance/=result.size();
		negVariance/=result.size();
		
		//Simplify Result
		int posSimp=0,negSimp=0;
		if(posAverage==negAverage&&posAverage!=0)
		{
			posSimp=1;
			negSimp=1;
		}
		else if(posAverage==negAverage&&posAverage==0)
		{
			posSimp=0;
			negSimp=0;
		}
		else if(posAverage>negAverage)
		{
			posSimp=1;
			negSimp=0;
		}
		else
		{
			posSimp=0;
			negSimp=1;
		}
		
		
		//Output Result
		/*
		System.out.println("Overall Average:"+posAverage+","+negAverage);
		System.out.println("Overall Variance:"+posVariance+","+negVariance);
		System.out.println("Simplified Result:"+posSimp+","+negSimp);
		*/
		final_result.put("A", posAverage+","+negAverage);//Overall Average
		final_result.put("V",posVariance+","+negVariance);//Overall Variance
		final_result.put("S",posSimp+","+negSimp);//Simplified Result
		
		return final_result;
	}

}
