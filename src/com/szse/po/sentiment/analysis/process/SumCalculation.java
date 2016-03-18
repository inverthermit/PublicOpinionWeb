
package com.szse.po.sentiment.analysis.process;

import java.util.*;
import java.io.*;

import com.szse.po.sentiment.analysis.NLPIR.code.NlpirDemo;

public class SumCalculation {

	
	public static ArrayList<String> posdict=null;
	public static ArrayList<String> negdict=null;
	
	public static ArrayList<String> mostdict =null;
	public static ArrayList<String> verydict =null;
	public static ArrayList<String> moredict =null;
	public static ArrayList<String> ishdict =null;
	public static ArrayList<String> insufficientdict =null;
	public static ArrayList<String> inversedict =null;
	public static String argu = "D:\\MyEclipse2015WorkSpace\\JnaTest_NLPIR\\ICTCLAS2015\\";
    public static NlpirDemo nlp=null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//dicInit(Config.ROOT_PATH);
		
	}
	
	public static void nlpInit()
	{
		if(nlp==null)
		nlp=new NlpirDemo(argu);
	}
	
	public static void nlpUpdate()
	{
		nlp=new NlpirDemo(argu);
	}
	
	public boolean dicInit(String filepath)
	{
		if(posdict==null&&negdict==null&&mostdict==null&&verydict==null&&moredict==null&&ishdict==null&&
				insufficientdict==null&&inversedict==null	)
		{
		posdict=readTXTList(filepath+"posdict.txt");
		negdict=readTXTList(filepath+"negdict.txt");
		mostdict=readTXTList(filepath+"mostdict.txt");
		verydict=readTXTList(filepath+"verydict.txt");
		moredict=readTXTList(filepath+"moredict.txt");
		ishdict=readTXTList(filepath+"ishdict.txt");
		insufficientdict=readTXTList(filepath+"insufficientdict.txt");
		inversedict=readTXTList(filepath+"inversedict.txt");
		return true;
		}
		else
			return true;
	}
	
	public boolean dicUpdate(String filepath)
	{
		
		posdict=readTXTList(filepath+"posdict.txt");
		negdict=readTXTList(filepath+"negdict.txt");
		mostdict=readTXTList(filepath+"mostdict.txt");
		verydict=readTXTList(filepath+"verydict.txt");
		moredict=readTXTList(filepath+"moredict.txt");
		ishdict=readTXTList(filepath+"ishdict.txt");
		insufficientdict=readTXTList(filepath+"insufficientdict.txt");
		inversedict=readTXTList(filepath+"inversedict.txt");
		return true;
	}
	
	public ArrayList<String> readTXTList(String path)
	{
		
		try{
			ArrayList<String> result=new ArrayList<String>();
			//BufferedReader br=new BufferedReader(new FileReader(path));
			InputStreamReader isr = new InputStreamReader(new FileInputStream(path), "GBK");
			BufferedReader br = new BufferedReader(isr);
			while(br.ready())
			{
				String line=br.readLine();
				if(!line.equals(""))
				{
					result.add(line);
					//System.out.println(line);
				}
										
			}
			br.close();
			System.out.println(result.size()+"----"+path);
			return result;
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<int[]> scoreParagragh(String para)
	{
		ArrayList<int[]> result=new ArrayList();
		ArrayList<String> sentences=splitTools.splitSentences(para);
		for(String sen:sentences)
		{
			int[] score=score(sen);
			result.add(score);
			//System.out.println(score[0]+","+score[1]);
		}
		return result;
	}
	
	public int[] score(String sentence)
	{
		
		int i = 0; //记录扫描到的词的位置
        int a = 0; //记录情感词的位置
        int poscount = 0; //积极词的第一次分值
        int poscount2 = 0; //积极词反转后的分值
        int poscount3 = 0; //积极词的最后分值（包括叹号的分值）
        int negcount = 0;
        int negcount2 = 0;
        int negcount3 = 0;
        ArrayList<String> segtmp =null;//SPLIT the SENTENCE to words
        nlpInit();
        segtmp=nlp.parseWordReturnList(sentence);
        //System.out.println(segtmp);
        for(String word:segtmp)
        {
        	//System.out.println(word);
        	if(posdict.contains(word))
        	{
        		//System.out.println(word+":pos");
        		poscount += 1;                
                int c = 0;
                for(int x=a;x<i;x++){//扫描情感词前的程度词
                	String w=segtmp.get(x);
                    if(mostdict.contains(w))
                        poscount *= 4.0;
                    else if ( verydict.contains(w))
                        poscount *= 3.0;
                    else if ( moredict.contains(w))
                        poscount *= 2.0;
                    else if ( ishdict.contains(w))
                        poscount /= 2.0;
                    else if ( insufficientdict.contains(w))
                        poscount /= 4.0;
                    else if ( inversedict.contains(w))
                        c += 1;
                }
                if (c%2==1){ //扫描情感词前的否定词数
                    poscount *= -1.0;
                    poscount2 += poscount;
                    poscount = 0;
                    poscount3 = poscount + poscount2 + poscount3;
                    poscount2 = 0;
                }
                else{
                    poscount3 = poscount + poscount2 + poscount3;
                    poscount = 0;
                }
                a = i + 1; //情感词的位置变化
        	}
        	else if(negdict.contains(word))
        	{
        		//System.out.println(word);
        		negcount += 1;
                int d = 0;
        		for(int x=a;x<i;x++){//扫描情感词前的程度词
                	String w=segtmp.get(x);
                    if(mostdict.contains(w))
                    	negcount *= 4.0;
                    else if ( verydict.contains(w))
                    	negcount *= 3.0;
                    else if ( moredict.contains(w))
                    	negcount *= 2.0;
                    else if ( ishdict.contains(w))
                    	negcount /= 2.0;
                    else if ( insufficientdict.contains(w))
                    	negcount /= 4.0;
                    else if ( inversedict.contains(w))
                    	d += 1;
        		}
                if (d%2==1){
                    negcount *= -1.0;
                    negcount2 += negcount;
                    negcount = 0;
                    negcount3 = negcount + negcount2 + negcount3;
                    negcount2 = 0;
                }
                else{
                    negcount3 = negcount + negcount2 + negcount3;
                    negcount = 0;
                }
                a = i + 1;
        	}
        	else if( word.equals("!")){ //判断句子是否有感叹号
                for(int y=segtmp.size()-1;y>=0;y--)//#扫描感叹号前的情感词，发现后权值+2，然后退出循环
                {
                	String w2=segtmp.get(y);
                	if (posdict.contains(w2)||negdict.contains(w2))
                	{
                		poscount3 += 2;
                        negcount3 += 2;
                        break ;
                	}
                        
                }
        	}
        	i++;
                         
        }
        //以下是防止出现负数的情况
        int pos_count = 0;
        int neg_count = 0;
        if (poscount3 < 0 && negcount3 > 0){
            neg_count += negcount3 - poscount3;
            pos_count = 0;
        }
        else if( negcount3 < 0 && poscount3 > 0){
            pos_count = poscount3 - negcount3;
            neg_count = 0;
        }
        else if( poscount3 < 0 && negcount3 < 0){
            neg_count = -poscount3;
            pos_count = -negcount3;
        }
        else{
            pos_count = poscount3;
            neg_count = negcount3;
        }
        int[] result={pos_count,neg_count};
        return result;
	                
	            
	}

}
