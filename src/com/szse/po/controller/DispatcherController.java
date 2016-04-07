package com.szse.po.controller;
import java.text.DecimalFormat;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Ace.controller.User;
import com.szse.po.service.tools.Statistics;
@Controller
public class DispatcherController {

	 @RequestMapping(value="/hello")

     public void hello(HttpServletRequest request,
             HttpServletResponse response) throws IOException{
     	response.setContentType("application/json");
         System.out.println("spring mvc hello world!");
         PrintWriter out = response.getWriter(); 
         out.write("{\"message\":\"from controller.\"}");
         //return "index";

     }

    

     //hello world

     @RequestMapping(value="/ok")

     

     public void ok(User user, HttpServletRequest request,
             HttpServletResponse response) {
     	System.out.println();
         String result =
             "{\"name\":\""+user.getUsername()+"\",\"pwd\":\""+user.getPassword()+"\"}";//user
             //�ӵ�ǰ̨��������ݣ���ƴ�ӳ��µ�json���� 
         response.setContentType("application/json");//����response�Ĵ����ʽΪjson 
         System.out.println(result); 
         try {
         	 
              PrintWriter out = response.getWriter(); 
              out.write(result);//��ҳ���ϴ���json���� 
         } catch (IOException e) { 
              e.printStackTrace(); 
         } 
     }  
      

      
      @RequestMapping(value="/boarddata.do")
      public void BoardResult(HttpServletRequest request,
              HttpServletResponse response) throws IOException {
    	 
    	 Map<String,Integer> map=Statistics.getBoardInfo(null);
     	 JSONObject entry=new JSONObject();
     	 entry.put("type", "board");
     	 JSONObject value=new JSONObject();
     	 
     	 //entry.put("target", "course");
 		 value.put("main", map.get("main"));
 		 value.put("sme", map.get("sme"));
 		 value.put("chinext", map.get("chinext"));
 		 value.put("sum", map.get("sum"));
 		 
 		 entry.put("value", value);
 	     System.out.println(value.toString());
 	     response.setCharacterEncoding("UTF-8");
 	     response.setContentType("application/json");
          PrintWriter out = response.getWriter(); 
          out.write(value.toString());
		    
      }  
      
      @RequestMapping(value="/regiondata.do")
      public void RegionResult(HttpServletRequest request,
              HttpServletResponse response) throws IOException{
    	 StringBuffer resultString=new StringBuffer();
    	 resultString.append("{");
    	 Map<String,Integer> map=Statistics.getRegionInfo(null);
    	 JSONObject result = new JSONObject();  
    	 JSONObject data=new JSONObject();  
    	 JSONObject entry=new JSONObject();
    	 result.put("type", "region");
    	 
    	 //Count Sum
    	 int sum=0;
    	 Iterator countiter=map.entrySet().iterator();
    	 while(countiter.hasNext())
    	 {
    		 Map.Entry entry0=(Map.Entry) countiter.next();
    		 sum+=(int)entry0.getValue();
    	 }
    	 
    	 Iterator iter=map.entrySet().iterator();
    	 int index=1;
    	 JSONObject obj = new JSONObject();   
    	 while(iter.hasNext())
    	 {
    		 Map.Entry entry1=(Map.Entry) iter.next();
    		 
    		 JSONObject innerobj = new JSONObject();
    		 double val=((int)entry1.getValue()/(sum+0.0)*100);
    		 //Round to 2 decimal places
    		 DecimalFormat df = new DecimalFormat("#.00");  
             if(df.format(val).startsWith("."))
            	 innerobj.put("value", "0"+df.format(val)+"%");
             else
    		 innerobj.put("value", df.format(val)+"%");
    		 innerobj.put("index",index);
    		 
    		 //Get state color
    		 int[] stateArr={10,7,5,4,3,1,0};
    		 int state=7;
    		 for(int i=0;i<stateArr.length;i++)
    		 {
    			 if(val>stateArr[i])
    			 {
    				 state=i;
    				 break;
    			 }
    		 }
    		 innerobj.put("stateInitColor",state);
    		 
    		 index++;
    		 obj.put((String)entry1.getKey(), innerobj);
    		 if(index!=2)
    		 {
    			 resultString.append(",");
    		 }
    		 resultString.append("\""+(String)entry1.getKey()+"\":"+innerobj.toString());
			  /*obj.put("key", (String)entry1.getKey());
			  obj.put("value", entry1.getValue());
			  */
    		 //data.(obj);
    	 }
    	 resultString.append("}");
 		 //result.put("data", data);
	     System.out.println(resultString.toString());
	     response.setCharacterEncoding("UTF-8");
	     response.setContentType("application/json");
         PrintWriter out = response.getWriter(); 
         out.write(resultString.toString());
      	
      }
      
      @RequestMapping(value="/industrydata.do")
      public void IndustryResult(HttpServletRequest request,
              HttpServletResponse response) throws IOException{
    	  
    	 Map<String,Integer> map=Statistics.getIndustryInfo(null);
    	 JSONObject result = new JSONObject();  
 		 JSONArray data=new JSONArray();  
    	 JSONObject entry=new JSONObject();
    	 result.put("type", "industry");
    	 Iterator sumiter=map.entrySet().iterator();
    	 int sum=0;
    	 while(sumiter.hasNext())
    	 {
    		 Map.Entry entry1=(Map.Entry) sumiter.next();
    		 JSONObject obj = new JSONObject();   
			 sum+= (int)entry1.getValue();
    	 }
    	 Iterator iter=map.entrySet().iterator();
    	 while(iter.hasNext())
    	 {
    		 Map.Entry entry1=(Map.Entry) iter.next();
    		 JSONObject obj = new JSONObject();   
			  obj.put("key", ((String)entry1.getKey()).split(" ")[1]);
			  obj.put("value", (int)((int)entry1.getValue()*30/(sum+0.0))+1);
			  data.put(obj);
    	 }
 		 result.put("data", data);
	     System.out.println(result.toString());
	     response.setCharacterEncoding("UTF-8");
	     response.setContentType("application/json");
         PrintWriter out = response.getWriter(); 
         out.write(result.toString());
      	
      } 
      
      @RequestMapping(value="/top10data.do")
      public void Top10NegAlarmResult(HttpServletRequest request,
              HttpServletResponse response) throws IOException{
    	  //getTop10Info();
    	  Map<String,Double> map=Statistics.getTop10Info(null);
    	  JSONObject result = new JSONObject();  
  		 JSONArray data=new JSONArray();  
     	 JSONObject entry=new JSONObject();
     	 result.put("type", "top10");
     	 Iterator iter=map.entrySet().iterator();
     	 while(iter.hasNext())
     	 {
     		 Map.Entry entry1=(Map.Entry) iter.next();
     		 JSONObject obj = new JSONObject();   
 			  obj.put("key", (String)entry1.getKey());
 			  obj.put("value", entry1.getValue());
 			  data.put(obj);
     	 }
  		 result.put("data", data);
 	     System.out.println(result.toString());
 	     response.setCharacterEncoding("UTF-8");
 	     response.setContentType("application/json");
          PrintWriter out = response.getWriter(); 
          out.write(result.toString());
      }  

     
      @RequestMapping(value="/netizendata.do")
      public void NetizenAttentionResult(HttpServletRequest request,
              HttpServletResponse response) throws IOException{
    	  //getTop10Info();
    	  Map<String,Double> map=Statistics.getTop10Info(null);
    	  JSONObject result = new JSONObject();  
  		 JSONArray data=new JSONArray();  
     	 JSONObject entry=new JSONObject();
     	 result.put("type", "top10");
     	 Iterator iter=map.entrySet().iterator();
     	 while(iter.hasNext())
     	 {
     		 Map.Entry entry1=(Map.Entry) iter.next();
     		 JSONObject obj = new JSONObject();   
 			  obj.put("key", (String)entry1.getKey());
 			  obj.put("value", entry1.getValue());
 			  data.put(obj);
     	 }
  		 result.put("data", data);
 	     System.out.println(result.toString());
 	     response.setCharacterEncoding("UTF-8");
 	     response.setContentType("application/json");
          PrintWriter out = response.getWriter(); 
          out.write(result.toString());
      }  
      
      public void CorpResult(HttpServletRequest request,
              HttpServletResponse response) {
      	
      }  
      
      
}
