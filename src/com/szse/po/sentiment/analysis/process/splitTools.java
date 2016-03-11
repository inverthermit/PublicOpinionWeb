package com.szse.po.sentiment.analysis.process;
import java.util.*;

public class splitTools {

	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a="a,s.e?f!d;w，x。v？z！w；e";
		ArrayList<String> sp=splitSentences(a);
		for(String b:sp)
		System.out.println(b);

	}
	
	
	
	public static ArrayList<String> splitSentences(String passage)
	{
		ArrayList<String> result=new ArrayList<String>();
		//String[] s={",",".","?","!",";","，","。","？","！","；"};
		passage=passage.replace("!", "*excl*!");
		passage=passage.replace("！", "*excl*！");
		String[] sp= passage.split("[,.?!;，。？！；]");
		for(int i=0;i<sp.length;i++)
		{
			result.add(sp[i].replace("*excl*", "!"));
		}
		return result;
	}
	
	public int[] fun(String sentence)
	{
		ArrayList<String> posdict=new ArrayList<String>();
	    ArrayList<String> negdict=new ArrayList<String>();
		ArrayList<String> segtmp =new ArrayList<String>();
		ArrayList<String> mostdict =new ArrayList<String>();
		ArrayList<String> verydict =new ArrayList<String>();
		ArrayList<String> moredict =new ArrayList<String>();
		ArrayList<String> ishdict =new ArrayList<String>();
		ArrayList<String> insufficientdict =new ArrayList<String>();
		ArrayList<String> inversedict =new ArrayList<String>();
		int i = 0; //记录扫描到的词的位置
        int a = 0; //记录情感词的位置
        int poscount = 0; //积极词的第一次分值
        int poscount2 = 0; //积极词反转后的分值
        int poscount3 = 0; //积极词的最后分值（包括叹号的分值）
        int negcount = 0;
        int negcount2 = 0;
        int negcount3 = 0;
        for(String word:segtmp)
        {
        	if(posdict.contains(word))
        	{
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
