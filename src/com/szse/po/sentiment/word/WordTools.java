package com.szse.po.sentiment.word;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
public class WordTools {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	
	public static double NUM=922;
	public static ArrayList<String> wordmap=new ArrayList<String>();
	public static String ROOTPATH="d://SentimentDic/";
	public static String PASSAGEPATH="D:\\MyEclipse2015WorkSpace\\JnaTest_NLPIR\\trssplitresult20160118.txt";
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		genWordmap();
		System.out.println(wordmap.size());
		System.out.println("\ngenPMap...");
		HashMap<String,Double> PMap=genPMap();
		//HashMap<String,Double> PMap=getMapFile(ROOTPATH+"wordfre.txt");
		System.out.println(PMap.size());
		System.out.println("\ngenPPxxMap...");
		HashMap<String,Double> PPxxMap=genPxxMap(Pwords,true);
		//HashMap<String,Double> PPxxMap=getMapFile(ROOTPATH+"Pwordwordfre.txt");
		System.out.println(PPxxMap.size());
		System.out.println("\ngenNPxxMap...");
		HashMap<String,Double> NPxxMap=genPxxMap(Nwords,false);
		//HashMap<String,Double> NPxxMap=getMapFile(ROOTPATH+"Nwordwordfre.txt");
		System.out.println(NPxxMap.size());
		System.out.println("\ngenSO_PMIMap...");
		LinkedHashMap<String,Double> result=genSO_PMIMap(PMap,PPxxMap,NPxxMap);
		
	}
	
	
	
	public static HashMap<String,Double> getMapFile(String path) throws Exception
	{
		HashMap<String,Double> result=new HashMap<String,Double>();
		BufferedReader br=new BufferedReader(new FileReader(path));
		while(br.ready())
		{
			String line=br.readLine();
			String[] temp=line.split(" ");
			if(temp.length>1)
			result.put(temp[0],Double.parseDouble(temp[1]));
		}
		br.close();
		return result;
	}
	
	/**
	 * @author Tim
	 * @return ArrayList of P(word), which can be converted into a 1*N matrix. Map(word,probability)
	 * @throws Exception
	 */
	public static void genWordmap()
	{
		HashSet<String> hs=new HashSet<String>();
		try{
			BufferedReader br=new BufferedReader(new FileReader(PASSAGEPATH));
			br.readLine();
			while(br.ready())
			{
				String line=br.readLine();
				String[] sp=line.split(" ");
				for(int i=0;i<sp.length;i++)
				{
					if(!sp[i].equals(""))
					{
						//select only chinese
						String regex=".*[0-9a-zA-Z]+.*";
						Matcher m=Pattern.compile(regex).matcher(sp[i]);
						String regex1=".*[^\u4e00-\u9fa5]+.*";
						Matcher m1=Pattern.compile(regex1).matcher(sp[i]);
						//System.out.println(m.matches());//true
						if(!m.matches()&&!m1.matches())
						hs.add(sp[i]);
					}
				}
			}
			br.close();
			Iterator<String> iterator=hs.iterator();
			while(iterator.hasNext()){
				wordmap.add(iterator.next());
			}
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
		
	}
	
	/**
	 * @author Tim
	 * @return Map of P(word), which can be converted into a 1*N matrix. Map(word,probability)
	 * @throws Exception
	 */
	public static HashMap<String,Double> genPMap() throws Exception
	{
		HashMap<String,Integer> map=new HashMap<String,Integer>();
		HashMap<String,Double> result=new HashMap<String,Double>();
		StringBuffer text=new StringBuffer();
		BufferedReader br=new BufferedReader(new FileReader(PASSAGEPATH));
		br.readLine();
		int total=getTotalLines(PASSAGEPATH);
		double count=0;
		ProgressBar pb=new ProgressBar();
		pb.init();
		while(br.ready())
		{
			String line=br.readLine();
			count++;
			pb.processbarshow((int)count, total);
			for(String a:wordmap)
			{
				if(line.contains(a))
				{
					if(!map.containsKey(a))
					{
						map.put(a, 1);
					}
					else
					{
						int num=map.get(a);
						map.put(a, num+1);
					}
				}
			}
		}
		System.out.println(count);
		br.close();
		
		Iterator iter = sortMapByValue1(map).entrySet().iterator();
		while (iter.hasNext()) {
		Map.Entry entry = (Map.Entry) iter.next();
		String key = (String)entry.getKey();
		int val = (int)entry.getValue();
		double fre=val/count;
		result.put(key, fre);
		text.append(key+" "+fre+"\r\n");
		}
		write2File(ROOTPATH+"wordfre.txt",text.toString());//Single word frequency
		return result;
	}
	
	/**
	 * @author Tim
	 * P(word1,word2) word1 are Seeds. Number of Seeds is M. word2 are normal words except seeds.
	 * @return  Map of P(word1,word2), which can be converted into a M*N matrix. Map(word1&word2,probability)
	 * @throws Exception
	 */
	public static HashMap<String,Double> genPxxMap(String[] seeds,boolean positive) throws Exception
	{
		HashMap<String,Integer> map=new HashMap<String,Integer>();
		HashMap<String,Double> result=new HashMap<String,Double>();
		StringBuffer text=new StringBuffer();
		BufferedReader br=new BufferedReader(new FileReader(PASSAGEPATH));
		br.readLine();
		double count=0;
		NUM=getTotalLines(PASSAGEPATH);
		ProgressBar pb=new ProgressBar();
		pb.init();
		while(br.ready())
		{
			String line=br.readLine();
			count++;
			
			pb.processbarshow((int)count,(int)NUM);
			for(String a:wordmap)
			{
				for(int i=0;i<seeds.length;i++)
				{
					if(!seeds[i].equals(a))
					{
						
						if(line.contains(seeds[i])&&line.contains(a))
						{
							String key=seeds[i]+"&"+a;
							if(!map.containsKey(key))
							{
								map.put(key, 1);
							}
							else
							{
								int num=map.get(key);
								map.put(key, num+1);
							}
						}
					}
				}
				
			}
		}
		br.close();
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
		Map.Entry entry = (Map.Entry) iter.next();
		String key = (String)entry.getKey();
		int val = (int)entry.getValue();
		double fre=val/count;
		result.put(key, fre);
		text.append(key+" "+fre+"\r\n");
		}
		String prefix="";
		if(positive)
		{
			prefix="P";
		}
		else
			prefix="N";
		write2File(ROOTPATH+prefix+"wordwordfre.txt",text.toString());//Multiple words frequency
		return result;
	}
	
	
	/**
	 * @author Tim
	 * Generate final result according to PMap,PPxxMap,NPxxMap. Result is sorted.
	 * @return  LinkedHashMap of Ranking.
	 * @throws Exception
	 */
	public static LinkedHashMap<String,Double> genSO_PMIMap(HashMap<String,Double> PMap,HashMap<String,Double> PPxxMap,HashMap<String,Double> NPxxMap) throws Exception
	{
		HashMap<String,Double> result=new HashMap<String,Double>();
		ProgressBar pb=new ProgressBar();
		pb.init();
		int total=wordmap.size();
		for(int n=0;n<wordmap.size();n++)
		{
			
			String a=wordmap.get(n);
			pb.processbarshow(n,total);
			double psum=0;
			double nsum=0;
			for(int i=0;i<Pwords.length;i++)
			{
				psum+=SingleSOA(Pwords[i],a,PMap,PPxxMap);
			}
			for(int i=0;i<Nwords.length;i++)
			{
				nsum+=SingleSOA(Nwords[i],a,PMap,NPxxMap);
			}
			result.put(a, psum-nsum);
		}
		
		//Sort
		LinkedHashMap<String, Double> sortedMap=(LinkedHashMap<String, Double>)sortMapByValue(result);
		
		StringBuffer text=new StringBuffer();
		Iterator iter = sortedMap.entrySet().iterator();
		while (iter.hasNext()) {
		Map.Entry entry = (Map.Entry) iter.next();
		String key = (String)entry.getKey();
		double val = (double)entry.getValue();
		text.append(key+" "+val+"\r\n");
		}
		write2File(ROOTPATH+"SOPMI.txt",text.toString());//Multiple words frequency
		return sortedMap;
	}
	
	public static double SingleSOA(String seed,String word,HashMap<String,Double> PMap,HashMap<String,Double> xxMap)
	{
		String com=seed+"&"+word;
		//System.out.println(com+xxMap.containsKey(com)+PMap.containsKey(seed)+PMap.containsKey(word));
		if(xxMap.containsKey(com)&&PMap.containsKey(seed))
		{
			double value=xxMap.get(com)/(PMap.get(seed)*PMap.get(word));
			double result=log(value,2);
			return result;
		}
		else
			return 0;
		
	}
	
	public static Map<String, Double> sortMapByValue(Map<String, Double> oriMap) {
		Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
		if (oriMap != null && !oriMap.isEmpty()) {
			List<Map.Entry<String, Double>> entryList = new ArrayList<Map.Entry<String, Double>>(oriMap.entrySet());
			Collections.sort(entryList,
					new Comparator<Map.Entry<String, Double>>() {
						public int compare(Entry<String, Double> entry1,
								Entry<String, Double> entry2) {
							double value1 = 0, value2 = 0;
							try {
								value1 = entry1.getValue();
								value2 = entry2.getValue();
							} catch (NumberFormatException e) {
								value1 = 0;
								value2 = 0;
							}
							if(value1 < value2){
								return -1;
							}else if(value1 == value2){
								return 0;
							}else{
								return 1;
							}
						}
					});
			Iterator<Map.Entry<String, Double>> iter = entryList.iterator();
			Map.Entry<String, Double> tmpEntry = null;
			while (iter.hasNext()) {
				tmpEntry = iter.next();
				sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
			}
		}
		return sortedMap;
	}
	
	public static Map<String, Integer> sortMapByValue1(Map<String, Integer> oriMap) {
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		if (oriMap != null && !oriMap.isEmpty()) {
			List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(oriMap.entrySet());
			Collections.sort(entryList,
					new Comparator<Map.Entry<String, Integer>>() {
						public int compare(Entry<String, Integer> entry1,
								Entry<String, Integer> entry2) {
							double value1 = 0, value2 = 0;
							try {
								value1 = entry1.getValue();
								value2 = entry2.getValue();
							} catch (NumberFormatException e) {
								value1 = 0;
								value2 = 0;
							}
							if(value1 < value2){
								return -1;
							}else if(value1 == value2){
								return 0;
							}else{
								return 1;
							}
						}
					});
			Iterator<Map.Entry<String, Integer>> iter = entryList.iterator();
			Map.Entry<String, Integer> tmpEntry = null;
			while (iter.hasNext()) {
				tmpEntry = iter.next();
				sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
			}
		}
		return sortedMap;
	}
	
	public static void write2File(String path,String text) throws Exception
	{
		FileOutputStream fo=new FileOutputStream(path);
		fo.write(text.getBytes());
		fo.close();
	}
	
	public static double log(double value, double base)
	{
		return Math.log(value) / Math.log(base);
	}
	
	public static int getTotalLines(String fileName) throws IOException {
        FileReader in = new FileReader(fileName);
        LineNumberReader reader = new LineNumberReader(in);
        String strLine = reader.readLine();
        int totalLines = 0;
        while (strLine != null) {
            totalLines++;
            strLine = reader.readLine();
        }
        reader.close();
        in.close();
        return totalLines;
    }
	
	/*public static String[] Pwords={"上涨","阳线","新高","强势","上升","增长","牛市","盈利","涨幅","新增","强","涨"};
	public static String[] Nwords={"下跌","阴线","降低","下降","下滑","亏损","跌幅","减少","悲观","损失"};
	*/
	public static String[] Pwords={"龙头","利润","增长","增持","积极","盈利","推进","利益","提高","稳定","大涨","增强","激励"};
	public static String[] Nwords={"下跌","停牌","风险","下滑","亏损","跌幅","减持","涉嫌","违法","违规","严重","导致","质疑"};
	

}
