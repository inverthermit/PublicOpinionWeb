package com.szse.po.service.sentimentword;

public class ProgressBar {


	private static int count=1;  
    private static boolean isStart=false;  
      
    public void processbarshow(int num,int total)  
    {  
        /** 
         * 总共显示30个 
         * ______________________________ 
         * ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ 
         */         
        int process=num*30/total;  
        if(!isStart){  
            System.out.println("______________________________");  
            isStart=true;  
        }  
        if(count>30){  
            count=1;  
            isStart=false;  
        }         
        if(process==count){  
            System.out.print("■");  
            count++;  
        }  
        if(process==30){  
            System.out.println();
        }  
    }    
    public void init()
    {
    	count=1;
    	isStart=false;
    }

}
