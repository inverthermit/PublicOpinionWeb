package com.szse.po.service.LDA;
import java.io.*;
import java.util.*;
public class ResultAnalysis {
	public static void main(String[] args)
	{
		passage2topic("D:/PublicOpinionWebData/20163241500/300431/model-final.theta");
	}
	public static ArrayList<Integer> passage2topic(String thetafilename)
	{
		ArrayList<Integer> result=new ArrayList<Integer>();
		try{
		BufferedReader br=new BufferedReader(new FileReader(thetafilename));
		while(br.ready())
		{
			String line=br.readLine();
			String[] arr=line.split(" ");
			double max=0;
			int index=0;
			for(int i=0;i<arr.length;i++)
			{
				double temp=Double.parseDouble(arr[i]);
				if(temp>max)
				{
					max=temp;
					index=i;
				}
			}
			result.add(index);
			//System.out.println(index);
		}
		br.close();
		return result;
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
		return null;
	}
	
	//public static void get

}
