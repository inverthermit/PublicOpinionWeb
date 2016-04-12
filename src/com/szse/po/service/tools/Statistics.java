package com.szse.po.service.tools;

import java.util.*;
import java.util.Map.Entry;

import com.szse.po.service.classification.Analysis;
import com.szse.po.service.classification.Classifier;

public class Statistics {

	public static void main(String[] args)
	{
		getTop10Info(null);
	}
	public static List<Map.Entry<String, Integer>> classifylist=Classifier.classifynotsave();
	public static List<Map.Entry<String, Integer>> classifylistNeg=Classifier.classifynotsaveNeg();
	public static Map<String,Integer> getBoardInfo(List<Map.Entry<String, Integer>> infoIds)
	{
		if(infoIds==null)
			infoIds=classifylist;
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
			infoIds=classifylist;
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
			infoIds=classifylist;
		Map<String,Integer> result=new HashMap<String,Integer>();
		for (int i = 0; i < infoIds.size(); i++) {
		    String id = infoIds.get(i).getKey();
		    int n=infoIds.get(i).getValue();
		    HashMap<String,String> info=Analysis.findByCode(id);
		    if(info!=null){
		    	String temp=getRegionPinyin(info.get("region"));
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
		result=sortMap(result);
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
			infoIds=classifylistNeg;
		Map<String,Double> result=new HashMap<String,Double>();
		
		for (int i = 0; i < infoIds.size(); i++) {
			
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
		    			if(result.size()==10)
		    				break;
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
			infoIds=classifylist;
		Map<String,Double> result=new HashMap<String,Double>();
		for (int i = 0; i < infoIds.size(); i++) {
			
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
		    			if(result.size()==10)
		    				break;
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

	public static ArrayList<String> getgetContentbyCode(String code)
	{
		return SaveTools.getContentbyCode(code);
	}
	public static String getRegionPinyin(String chinese)
	{
		for(int i=0;i<provinces.length;i++)
		{
			if(chinese.equals(provinces[i][0]))
				return provinces[i][1];
		}
		System.out.println(chinese+" has no Pinyin!");
		return chinese;
	}


	 public static Map sortMap(Map oldMap) {  
	        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(oldMap.entrySet());  
	        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {  
	  
	            @Override  
	            public int compare(Entry<java.lang.String, Integer> arg0,  
	                    Entry<java.lang.String, Integer> arg1) {  
	                return  arg1.getValue()-arg0.getValue() ;  
	            }  
	        });  
	        Map newMap = new LinkedHashMap();  
	        for (int i = 0; i < list.size(); i++) {  
	            newMap.put(list.get(i).getKey(), list.get(i).getValue());  
	        }  
	        return newMap;  
	    }  
	
	
	public static String[][] provinces={
		{"黑龙江","heilongjiang"},
		{"河北","hebei"},
		{"甘肃","gansu"},
		{"云南","yunnan"},
		{"四川","sichuan"},
		{"吉林","jilin"},
		{"辽宁","liaoning"},
		{"青海","qinghai"},
		{"陕西","shanxi"},
		{"河南","henan"},
		{"山东","shandong"},
		{"山西","shanxi"},
		{"安徽","anhui"},
		{"湖北","hubei"},
		{"湖南","hunan"},
		{"江苏","jiangsu"},
		{"贵州","guizhou"},
		{"浙江","zhejiang"},
		{"江西","jiangxi"},
		{"广东","guangdong"},
		{"福建","fujian"},
		{"台湾","taiwan"},
		{"海南","hainan"},
		{"广西","guangxi"},
		{"内蒙古","neimenggu"},
		{"宁夏","ningxia"},
		{"新疆","xinjiang"},
		{"西藏","xizang"},
		{"澳门","aomen"},
		{"北京","beijing"},
		{"上海","shanghai"},
		{"香港","xianggang"},
		{"天津","tianjin"},
		{"重庆","chongqing"}
	};
}
