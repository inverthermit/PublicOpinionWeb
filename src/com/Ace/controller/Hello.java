package com.Ace.controller;

 

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

 



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

 

@Controller

public class Hello {

   

    //hello world

        @RequestMapping(value="/hello")

        public void hello(HttpServletRequest request,
                HttpServletResponse response) throws IOException{
        	response.setContentType("application/json");
            System.out.println("spring mvc hello world!");
            PrintWriter out = response.getWriter(); 
            out.write("{\"message\":\"spring mvc hello world!\"}");
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

         

 

}