package com.szse.sentiment.analysis;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SumCalculation sumcal=new SumCalculation();
		sumcal.dicInit(Config.ROOT_PATH);
		int[] result=sumcal.score("现在的形式极其非常被看好");
		System.out.println(result[0]+","+result[1]);
	}

}
