package com.szse.po.service.process;
import com.szse.po.sentiment.analysis.process.SentimentAnalysis;
import com.szse.po.service.LDA.*;
import com.szse.po.service.classification.Classifier;
import com.szse.po.service.spider.Spider;
import com.szse.po.service.tools.SaveTools;
import com.szse.po.service.tools.Statistics;

import java.util.*;
public class ProcessAll {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		//Spider the Newest Data.
		Spider spi=new Spider();
		spi.StartSpider();
		
		//Classify and Get the Stock Code.
		List<Map.Entry<String, Integer>> list=Classifier.classify();
		
		//Analyze the Sentiment.
		SentimentAnalysis.analyseAll();
		
		//Get the Keywords & Vectors.
		SaveTools.saveKeywords();
		
		
		SaveTools.saveVector();
		
		
		//LDA Topic Generation.
		SaveTools.saveFilesLDA();
		LDA lda=new LDA();
		int count=lda.count("D:\\PublicOpinionWebData\\20163241500\\000002ID.txt");
		System.out.println(count);
		//lda.processFolder("D:\\PublicOpinionWebData\\20163241500");
		
		List<Map.Entry<String, Integer>> list=Classifier.classifynotsave();
		ArrayList<String> anaList=new ArrayList<String>();
		String path="D:\\PublicOpinionWebData\\20163241500\\";
		for (int i = 0; i < list.size(); i++) {
		    
		    if(list.get(i).getValue()>5)
		    {
		    	String code = list.get(i).getKey();
		    	anaList.add(code);
		    	//if(code.equals("002226"))
		    	lda.process(code, path+code+"/");
		    }
		    //System.out.println(code);
		}
		*/
		
		//Single Passage->topic   topic->topic words
		//List<Map.Entry<String, Integer>> list=Classifier.classifynotsave();
		//Statistics.getBoardInfo(list);
		//Statistics.getRegionInfo(list);
		//Statistics.getIndustryInfo(list);
		Classifier.classifynotsaveNeg();
		
	}
	
	

}
