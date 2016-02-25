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

        public String hello(){

            System.out.println("spring mvc hello world!");

            return "index";

        }

       

        //hello world

        @RequestMapping(value="/ok")

        

        public void ok(User user, HttpServletRequest request,
                HttpServletResponse response) {
        	System.out.println();
            String result =
                "{\"name\":\""+user.getUsername()+"\",\"pwd\":\""+user.getPassword()+"\"}";//user
                //接到前台传到的数据，并拼接成新的json对象 
            response.setContentType("application/json");//设置response的传输格式为json 
            System.out.println(result); 
            try {
            	 
                 PrintWriter out = response.getWriter(); 
                 out.write(result);//给页面上传输json对象 
            } catch (IOException e) { 
                 e.printStackTrace(); 
            } 
        }  

         

 

}