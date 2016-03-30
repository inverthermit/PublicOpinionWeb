package com.szse.po.service.tools;

import java.util.*;

import com.szse.po.service.classification.Analysis;
import com.szse.po.service.classification.Classifier;

public class Statistics {

	public static Map<String,Integer> getBoardInfo(List<Map.Entry<String, Integer>> infoIds)
	{
		if(infoIds==null)
			infoIds=Classifier.classifynotsave();
		int MainCount=0;
		int SMECount=0;
		int ChiNextCount=0;
		for (int i = 0; i < infoIds.size(); i++) {
		    String id = infoIds.get(i).getKey();
		    int n=infoIds.get(i).getValue();
		    HashMap<String,String> info=Analysis.findByCode(id);
		    if(info!=null){
		    	String temp=info.get("board");
		    	if(temp!=null)
		    	{
		    		if(temp.equals("main"))
		    			MainCount+=n;
		    		else if(temp.equals("sme"))
		    			SMECount+=n;
		    		else if(temp.equals("chinext"))
		    			ChiNextCount+=n;
		    		
		    	}
		    }
		    
		}
		int sum=MainCount+SMECount+ChiNextCount;
		System.out.println("主板："+MainCount+"；中小板："+SMECount+"；创业板："+ChiNextCount+"|共："+sum);
		
		Map<String,Integer> result=new HashMap<String,Integer>();
		result.put("main",MainCount);
		result.put("sme",SMECount);
		result.put("chinext",ChiNextCount);
		result.put("sum",sum);
		return result;
	}
	
	public static Map<String,Integer> getIndustryInfo(List<Map.Entry<String, Integer>> infoIds)
	{
		if(infoIds==null)
			infoIds=Classifier.classifynotsave();
		Map<String,Integer> result=new HashMap<String,Integer>();
		for (int i = 0; i < infoIds.size(); i++) {
		    String id = infoIds.get(i).getKey();
		    int n=infoIds.get(i).getValue();
		    HashMap<String,String> info=Analysis.findByCode(id);
		    if(info!=null){
		    	String temp=info.get("industry");
		    	if(temp!=null&&!temp.equals(""))
		    	{
		    		if(!result.containsKey(temp))
		    		{
		    			result.put(temp, n);
		    		}
		    		else
		    		{
		    			result.put(temp, result.get(temp)+n);
		    		}
		    		
		    	}
		    }
		    
		}
		Iterator iter = result.entrySet().iterator();
		while (iter.hasNext()) {
		Map.Entry entry = (Map.Entry) iter.next();
		Object key = entry.getKey();
		Object val = entry.getValue();
		System.out.println(key+","+val);
		}
		return result;
	}
	
	public static Map<String,Integer> getRegionInfo(List<Map.Entry<String, Integer>> infoIds)
	{
		if(infoIds==null)
			infoIds=Classifier.classifynotsave();
		Map<String,Integer> result=new HashMap<String,Integer>();
		for (int i = 0; i < infoIds.size(); i++) {
		    String id = infoIds.get(i).getKey();
		    int n=infoIds.get(i).getValue();
		    HashMap<String,String> info=Analysis.findByCode(id);
		    if(info!=null){
		    	String temp=info.get("region");
		    	if(temp!=null&&!temp.equals(""))
		    	{
		    		if(!result.containsKey(temp))
		    		{
		    			result.put(temp, n);
		    		}
		    		else
		    		{
		    			result.put(temp, result.get(temp)+n);
		    		}
		    		
		    	}
		    }
		    
		}
		Iterator iter = result.entrySet().iterator();
		while (iter.hasNext()) {
		Map.Entry entry = (Map.Entry) iter.next();
		Object key = entry.getKey();
		Object val = entry.getValue();
		System.out.println(key+","+val);
		}
		return result;
	}

	public static Map<String,Double> getTop10Info(List<Map.Entry<String, Integer>> infoIds)
	{
		if(infoIds==null)
			infoIds=Classifier.classifynotsaveNeg();
		Map<String,Double> result=new HashMap<String,Double>();
		for (int i = 0; i < infoIds.size(); i++) {
			if(i>=10)
				break;
		    String id = infoIds.get(i).getKey();
		    int n=infoIds.get(i).getValue();
		    HashMap<String,String> info=Analysis.findByCode(id);
		    if(info!=null){
		    	String temp=info.get("name");
		    	String key=id+"#"+temp;
		    	if(key!=null&&!key.equals("#"))
		    	{
		    		if(!result.containsKey(key))
		    		{
		    			result.put(key, n*3.66);
		    		}
		    	}
		    }
		    
		}
		Iterator iter = result.entrySet().iterator();
		while (iter.hasNext()) {
		Map.Entry entry = (Map.Entry) iter.next();
		Object key = entry.getKey();
		Object val = entry.getValue();
		System.out.println(key+","+val);
		}
		return result;
		
	}
	
	public static Map<String,Double> getNetizenAttention(List<Map.Entry<String, Integer>> infoIds)
	{
		if(infoIds==null)
			infoIds=Classifier.classifynotsave();
		Map<String,Double> result=new HashMap<String,Double>();
		for (int i = 0; i < infoIds.size(); i++) {
			if(i>=10)
				break;
		    String id = infoIds.get(i).getKey();
		    int n=infoIds.get(i).getValue();
		    HashMap<String,String> info=Analysis.findByCode(id);
		    if(info!=null){
		    	String temp=info.get("name");
		    	String key=id+"#"+temp;
		    	if(key!=null&&!key.equals("#"))
		    	{
		    		if(!result.containsKey(key))
		    		{
		    			result.put(key, n*1.0);
		    		}
		    	}
		    }
		    
		}
		Iterator iter = result.entrySet().iterator();
		while (iter.hasNext()) {
		Map.Entry entry = (Map.Entry) iter.next();
		Object key = entry.getKey();
		Object val = entry.getValue();
		System.out.println(key+","+val);
		}
		return result;
		
	}
}
