package com.szse.po.service.classification;
import java.util.*;
public class Analysis {

	//0:code 1:name 2:region 3:industry //board
	//MainBoard SMEBoard ChiNextBoard
	public HashMap<String,String> findByCode(String code)
	{
		HashMap<String,String> result=new HashMap<String,String>();
		for(String[] temp:Data.MainBoard)
		{
			if(temp[0].equals(code))
			{
				result.put("code", temp[0]);
				result.put("name", temp[1]);
				result.put("region", temp[2]);
				result.put("industry", temp[3]);
				return result;
			}
		}
		for(String[] temp:Data.SMEBoard)
		{
			if(temp[0].equals(code))
			{
				result.put("code", temp[0]);
				result.put("name", temp[1]);
				result.put("region", temp[2]);
				result.put("industry", temp[3]);
				return result;
			}
		}
		for(String[] temp:Data.ChiNextBoard)
		{
			if(temp[0].equals(code))
			{
				result.put("code", temp[0]);
				result.put("name", temp[1]);
				result.put("region", temp[2]);
				result.put("industry", temp[3]);
				return result;
			}
		}
		return null;
		
	}
	

}
