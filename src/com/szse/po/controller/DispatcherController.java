package com.szse.po.controller;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
      

      
      @RequestMapping(value="/board")
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
      
      @RequestMapping(value="/region")
      public void RegionResult(HttpServletRequest request,
              HttpServletResponse response) throws IOException{
    	 
    	 Map<String,Integer> map=Statistics.getRegionInfo(null);
    	 JSONObject entry=new JSONObject();
    	 entry.put("type", "region");
    	 JSONObject value=new JSONObject();
    	 
    	 Iterator iter=map.entrySet().iterator();
    	 while(iter.hasNext())
    	 {
    		 Map.Entry entry1=(Map.Entry) iter.next();
    		 value.put((String)entry1.getKey(), entry1.getValue());
    	 }
    	 
		 entry.put("value", value);
	     System.out.println(value.toString());
	     response.setCharacterEncoding("UTF-8");
	     response.setContentType("application/json");
         PrintWriter out = response.getWriter(); 
         out.write(value.toString());
      	
      }  
      
      @RequestMapping(value="/industry")
      public void IndustryResult(HttpServletRequest request,
              HttpServletResponse response) throws IOException{
    	  
    	 Map<String,Integer> map=Statistics.getIndustryInfo(null);
     	 JSONObject entry=new JSONObject();
     	 entry.put("type", "industry");
     	 JSONObject value=new JSONObject();
     	 
     	 Iterator iter=map.entrySet().iterator();
     	 while(iter.hasNext())
     	 {
     		 Map.Entry entry1=(Map.Entry) iter.next();
     		 value.put((String)entry1.getKey(), entry1.getValue());
     	 }
     	 
 		 entry.put("value", value);
 	     System.out.println(value.toString());
 	     response.setCharacterEncoding("UTF-8");
 	     response.setContentType("application/json");
         PrintWriter out = response.getWriter(); 
         out.write(value.toString());
      	
      } 
      
      @RequestMapping(value="/top10")
      public void Top10NegAlarmResult(HttpServletRequest request,
              HttpServletResponse response) throws IOException{
    	  //getTop10Info();
    	  Map<String,Double> map=Statistics.getTop10Info(null);
      	 JSONObject entry=new JSONObject();
      	 entry.put("type", "top10");
      	 JSONObject value=new JSONObject();
      	 
      	 Iterator iter=map.entrySet().iterator();
      	 while(iter.hasNext())
      	 {
      		 Map.Entry entry1=(Map.Entry) iter.next();
      		 value.put((String)entry1.getKey(), entry1.getValue());
      	 }
      	 
  		 entry.put("value", value);
  	     System.out.println(value.toString());
  	     response.setCharacterEncoding("UTF-8");
  	     response.setContentType("application/json");
          PrintWriter out = response.getWriter(); 
          out.write(value.toString());
      }  

     
      @RequestMapping(value="/netizen")
      public void NetizenAttentionResult(HttpServletRequest request,
              HttpServletResponse response) throws IOException{
    	  //getTop10Info();
    	  Map<String,Double> map=Statistics.getTop10Info(null);
      	 JSONObject entry=new JSONObject();
      	 entry.put("type", "netizen");
      	 JSONObject value=new JSONObject();
      	 
      	 Iterator iter=map.entrySet().iterator();
      	 while(iter.hasNext())
      	 {
      		 Map.Entry entry1=(Map.Entry) iter.next();
      		 value.put((String)entry1.getKey(), entry1.getValue());
      	 }
      	 
  		 entry.put("value", value);
  	     System.out.println(value.toString());
  	     response.setCharacterEncoding("UTF-8");
  	     response.setContentType("application/json");
          PrintWriter out = response.getWriter(); 
          out.write(value.toString());
      }  
      
      public void CorpResult(HttpServletRequest request,
              HttpServletResponse response) {
      	
      }  
      
      
}
