/*
 * Copyright (C) 2007 by
 * 
 * 	Xuan-Hieu Phan
 *	hieuxuan@ecei.tohoku.ac.jp or pxhieu@gmail.com
 * 	Graduate School of Information Sciences
 * 	Tohoku University
 * 
 *  Cam-Tu Nguyen
 *  ncamtu@gmail.com
 *  College of Technology
 *  Vietnam National University, Hanoi
 *
 * JGibbsLDA is a free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation; either version 2 of the License,
 * or (at your option) any later version.
 *
 * JGibbsLDA is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JGibbsLDA; if not, write to the Free Software Foundation,
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 */

package com.szse.po.service.LDA;

import java.io.*;
import java.util.ArrayList;

import org.kohsuke.args4j.*;

public class LDA {
	
	public static void main(String args[]){
		LDACmdOption option = new LDACmdOption();
		CmdLineParser parser = new CmdLineParser(option);
		
		try {
			if (args.length == 0){
				showHelp(parser);
				return;
			}
			
			parser.parseArgument(args);
			
			if (option.est || option.estc){
				Estimator estimator = new Estimator();
				estimator.init(option);
				estimator.estimate();
			}
			else if (option.inf){
				Inferencer inferencer = new Inferencer();
				inferencer.init(option);
				
				Model newModel = inferencer.inference();
			
				for (int i = 0; i < newModel.phi.length; ++i){
					//phi: K * V
					System.out.println("-----------------------\ntopic" + i  + " : ");
					for (int j = 0; j < 10; ++j){
						System.out.println(inferencer.globalDict.id2word.get(j) + "\t" + newModel.phi[i][j]);
					}
				}
			}
		}
		catch (CmdLineException cle){
			System.out.println("Command line error: " + cle.getMessage());
			showHelp(parser);
			return;
		}
		catch (Exception e){
			System.out.println("Error in main: " + e.getMessage());
			e.printStackTrace();
			return;
		}
	}
	
	public static void showHelp(CmdLineParser parser){
		System.out.println("LDA [options ...] [arguments...]");
		parser.printUsage(System.out);
	}

	public void process(String code,String indir,String outdir)
	{
		LDACmdOption ldaOption = new LDACmdOption();   
        ldaOption.est = true;  
        ldaOption.estc = false;  
        ldaOption.modelName = code+"model";  
        //ldaOption.dfile = "trssplitresult.txt";  
        //ldaOption.dir="D:\\MyEclipse2015WorkSpace\\JGibbLDA-v.1.0\\model-trs20151218";
        ldaOption.dfile = code+".txt";  
        ldaOption.dir=outdir;//+code+"model"
        File file=new File(ldaOption.dir);
        file.mkdirs();
        ldaOption.alpha = 0.5;  
        ldaOption.beta = 0.1;  
        ldaOption.K =3;  
        ldaOption.niters = 20;   
        Estimator estimator = new Estimator();  
        estimator.init(ldaOption);  
        estimator.estimate();
	}
	/*
	public ArrayList<String> processFolder(String path)
	{
		File file=new File(path);
		File[] tempList = file.listFiles();
		//System.out.println("该目录下对象个数："+tempList.length);
		for (int i = 0; i < tempList.length; i++) {
		if (tempList[i].isFile()) {
			
			if(!tempList[i].toString().endsWith("ID.txt")&&count(tempList[i].toString())>1)
			{
				System.out.println("文     件："+tempList[i]);
			}
		}
	  }
	}
	
	public int count(String filename){
		try{
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        byte[] c = new byte[1024];
        int count = 0;
        int readChars = 0;
        while ((readChars = is.read(c)) != -1) {
            for (int i = 0; i < readChars; ++i) {
                if (c[i] == '\n')
                    ++count;
            }
        }
        is.close();
        return count;
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
		return 0;
    }
	*/
}
