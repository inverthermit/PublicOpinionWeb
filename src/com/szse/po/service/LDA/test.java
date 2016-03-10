package com.szse.po.service.LDA;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LDACmdOption ldaOption = new LDACmdOption();   
        ldaOption.est = true;  
        ldaOption.estc = false;  
        ldaOption.modelName = "model-trs20151218";  
        ldaOption.dfile = "trssplitresult.txt";  
        ldaOption.dir="D:\\MyEclipse2015WorkSpace\\JGibbLDA-v.1.0\\model-trs20151218";
        ldaOption.alpha = 0.5;  
        ldaOption.beta = 0.1;  
        ldaOption.K =100;  
        ldaOption.niters = 2000;   
        Estimator estimator = new Estimator();  
        estimator.init(ldaOption);  
        estimator.estimate();  
        
	}

}
