package com.szse.po.controller;

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
         out.write("{\"message\":\"二货麦砸\"}");
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
      

      public void CorpResult(HttpServletRequest request,
              HttpServletResponse response) {
      	
      }  
      
      @RequestMapping(value="/board")
      public void BoardResult(HttpServletRequest request,
              HttpServletResponse response) throws IOException {
    	  Map<String,Integer> map=Statistics.getBoardInfo(null);
    	  JSONObject entry=new JSONObject();
		    //entry.put("target", "course");
		    entry.put("main", map.get("main"));
		    entry.put("sme", map.get("sme"));
		    entry.put("chinext", map.get("chinext"));
		    entry.put("sum", map.get("sum"));
		    System.out.println(entry.toString());
		    response.setContentType("application/json");
	         PrintWriter out = response.getWriter(); 
	         out.write(entry.toString());
		    
      }  
      public void RegionResult(HttpServletRequest request,
              HttpServletResponse response) {
      	
      }  
      public void NegAlarmResult(HttpServletRequest request,
              HttpServletResponse response) {
      	
      }  
      public void IndustryResult(HttpServletRequest request,
              HttpServletResponse response) {
      	
      }  
      public void AttentionResult(HttpServletRequest request,
              HttpServletResponse response) {
      	
      }  
}
