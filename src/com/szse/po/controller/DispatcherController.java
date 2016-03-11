package com.szse.po.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;

import com.Ace.controller.User;

public class DispatcherController {

	  @RequestMapping(value="/hello")

      public String login(){

          System.out.println("spring mvc hello world!");

          return "index";

      }

     

      //hello world

      @RequestMapping(value="/ok")

      

      public void CorpResult(HttpServletRequest request,
              HttpServletResponse response) {
      	
      }  
      public void BoardResult(HttpServletRequest request,
              HttpServletResponse response) {
      	
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
