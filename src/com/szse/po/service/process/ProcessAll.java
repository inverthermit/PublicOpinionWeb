package com.szse.po.service.process;
import com.szse.po.service.classification.Classifier;
import com.szse.po.service.tools.*;
import com.szse.po.service.spider.*;
import com.szse.po.sentiment.analysis.*;
import com.szse.po.sentiment.analysis.process.SentimentAnalysis;
public class ProcessAll {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Spider the Newest Data.
		//Spider spi=new Spider();
		//spi.StartSpider();
		
		//Classify and Get the Stock Code.
		Classifier.classify();
		
		//Analyze the Sentiment.
		//SentimentAnalysis.analyseAll();
		
		//Get the Keywords.
		//SaveTools.saveKeywords();
		
		//LDA Topic Generation.
		
		
		
		
	}
	
	

}
