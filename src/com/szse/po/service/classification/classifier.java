package com.szse.po.service.classification;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
import com.szse.po.dao.*;
public class classifier {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Textdata td=new Textdata();
		TextdataDAO tdd=new TextdataDAO();
		List<Textdata> l=tdd.findAll();
		HashMap<String,Integer> codemap=new HashMap<String,Integer>();
		for(int i=0;i<l.size();i++)
		{
			td=l.get(i);
			String code=getGubaCode(td.getUrl());
			if(code.equals("002161"))
			System.out.println(td.getUrl()+td.getTime()+td.getTitle()+td.getContent());
			if(!codemap.containsKey(code))
			{
				codemap.put(code, 1);
			}
			else
			{
				codemap.put(code, codemap.get(code)+1);
			}
			
		}
		/*Iterator iter = codemap.entrySet().iterator();
		while (iter.hasNext()) {
		Map.Entry entry = (Map.Entry) iter.next();
		Object key = entry.getKey();
		Object val = entry.getValue();
		System.out.println(key.toString()+":"+val.toString());
		}*/
		
		Map<String, Integer> map = codemap;

		List<Map.Entry<String, Integer>> infoIds =
		    new ArrayList<Map.Entry<String, Integer>>(map.entrySet());

		
		//排序
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {   
		    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {      
		        return (o2.getValue() - o1.getValue()); 
		        //return (o1.getKey()).toString().compareTo(o2.getKey());
		    }
		}); 

		//排序后
		for (int i = 0; i < infoIds.size(); i++) {
		    String id = infoIds.get(i).toString();
		    if(infoIds.get(i).getValue()>5)
		    System.out.println(id);
		}
		
		
	}
	
	public static Miningresult classify(Textdata td)
	{
		Miningresult mr=new Miningresult();
		
		
		return mr;
	}
	
	public static String getGubaCode(String url)
	{
		//System.out.println(url);
		if(url.split("/").length>1)
		{
			String a=url.split("/")[url.split("/").length-1].split(",")[1];
			Pattern p = Pattern.compile("\\d{6}");
	    	Matcher m = p.matcher(a);
	    	if (m.find()) 
	    	{
	    		if(!a.startsWith("60"))
	    		{
	    			return m.group();
	    		}
	    	}
		}
		
		return null;
	}
	
	

}
