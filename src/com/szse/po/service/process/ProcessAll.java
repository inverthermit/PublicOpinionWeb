package com.szse.po.service.process;
import com.szse.po.sentiment.analysis.process.SentimentAnalysis;
import com.szse.po.service.LDA.*;
import com.szse.po.service.classification.Classifier;
import com.szse.po.service.spider.Spider;
import com.szse.po.service.tools.SaveTools;
import com.szse.po.service.tools.Statistics;

import java.util.*;

import net.sf.json.JSONObject;
public class ProcessAll {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*//Spider the Newest Data.
		Spider spi=new Spider();
		spi.StartSpider();
		*/
		//Classify and Get the Stock Code.
		System.out.println("开始分类...");
		List<Map.Entry<String, Integer>> list=Classifier.classify();
		
		//Analyze the Sentiment.
		System.out.println("分类结束，开始情感分析...");
		SentimentAnalysis.analyseAll();
		
		//Get the Keywords & Vectors.
		System.out.println("情感分析结束，开始提取关键词..");
		SaveTools.saveKeywords();
		
		System.out.println("提取关键词结束，开始保存向量模型...");
		SaveTools.saveVector();
		
		
		//LDA Topic Generation.
		System.out.println("向量模型保存结束，开始生成LDA主题...");
		SaveTools.saveFilesLDA();
		LDA lda=new LDA();
		//int count=lda.count("D:\\PublicOpinionWebData\\20163241500\\000002ID.txt");
		//System.out.println(count);
		//lda.processFolder("D:\\PublicOpinionWebData\\20163241500");
		
		//List<Map.Entry<String, Integer>> list=Classifier.classifynotsave();
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
		
		System.out.println("本次数据更新结束。");
		
		//Single Passage->topic   topic->topic words
		//List<Map.Entry<String, Integer>> list=Classifier.classifynotsave();
		//Statistics.getBoardInfo(list);
		//Statistics.getRegionInfo(list);
		//Statistics.getIndustryInfo(list);
		//Classifier.classifynotsaveNeg();
		/*
		Map<String,Integer> map=Statistics.getBoardInfo(null);
  	  JSONObject entry=new JSONObject();
		    //entry.put("target", "course");
		    entry.put("main", map.get("main"));
		    entry.put("sme", map.get("sme"));
		    entry.put("chinext", map.get("chinext"));
		    entry.put("sum", map.get("sum"));
		    System.out.println(entry.toString());*/
		
	}
	
	

}
