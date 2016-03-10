package com.szse.sentiment.NLPIR.code;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.szse.sentiment.NLPIR.utils.*;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class NlpirDemo {

	private List<String> stopWords = new ArrayList<String>();
	private Object paragraphProcessMutex = new Object();
	// 定义接口CLibrary，继承自com.sun.jna.Library
	public interface CLibrary extends Library {
		// 定义并初始化接口的静态变量
		CLibrary Instance = (CLibrary) Native.loadLibrary(
				"D:\\MyEclipse2015WorkSpace\\JnaTest_NLPIR\\ICTCLAS2015\\lib\\win32\\NLPIR", CLibrary.class);
		
		public int NLPIR_Init(String sDataPath, int encoding,
				String sLicenceCode);
				
		public String NLPIR_ParagraphProcess(String sSrc, int bPOSTagged);

		public String NLPIR_GetKeyWords(String sLine, int nMaxKeyLimit,
				boolean bWeightOut);
		public String NLPIR_GetFileKeyWords(String sLine, int nMaxKeyLimit,
				boolean bWeightOut);
		public int NLPIR_AddUserWord(String sWord);//add by qp 2008.11.10
		public int NLPIR_DelUsrWord(String sWord);//add by qp 2008.11.10
		public String NLPIR_GetLastErrorMsg();
		public void NLPIR_Exit();
	}

	public static String transString(String aidString, String ori_encoding,
			String new_encoding) {
		try {
			return new String(aidString.getBytes(ori_encoding), new_encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		String argu = "D:\\MyEclipse2015WorkSpace\\JnaTest_NLPIR\\ICTCLAS2015\\";
		// String system_charset = "GBK";//GBK----0
		NlpirDemo nd=new NlpirDemo(argu);
		
		InputStreamReader isr = new InputStreamReader(new FileInputStream("D:\\MyEclipse2015WorkSpace\\JnaTest_NLPIR\\trsdata20160118.txt"), "GBK");
		BufferedReader br=new BufferedReader(isr);
		ArrayList<String> arr=new ArrayList<String>();
		while(br.ready())
		{
			String line=br.readLine();
			//System.out.println(line+"\r\n");
			line=line.replaceAll("<[^>]+>", "");
			if(line.trim().equals("NULL"))
			{
				continue;
			}
			else
			{
				ArrayList<String> list=nd.parseWordReturnList(line);
				StringBuffer sb=new StringBuffer();
				for(int i=0;i<list.size();i++)
				{
					sb.append(list.get(i));
					if(i<list.size()-1)
					{
						sb.append(" ");
					}
				}
				arr.add(new String(sb));
			}
		}
		BufferedWriter bw=new BufferedWriter(new FileWriter("D:\\MyEclipse2015WorkSpace\\JnaTest_NLPIR\\trssplitresult20160118.txt"));
		bw.write(arr.size()+"\r\n");
		for(int i=0;i<arr.size();i++)
		{
			bw.write(arr.get(i)+"\r\n");
			//System.out.println(arr.get(i)+"\r\n");
		}
		br.close();bw.close();
		/*//String sInput = "更多更及时的专业评论,[,]请看<股金在线>  上一页  第 [1] [2] [3] [4] 页 (共4页)  下一页";
		ArrayList<String> list=nd.parseWordReturnList(sInput);
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<list.size();i++)
		{
			sb.append(list.get(i));
			if(i<list.size()-1)
			{
				sb.append(" ");
			}
		}
		System.out.println(new String(sb));*/
		
		nd.exit();

	}
	public NlpirDemo(String filePrefix){
		try {
			String argu = filePrefix;
			// 初始化
			String system_charset = "UTF-8";
			int charset_type = 1;
			
			int init_flag = CLibrary.Instance.NLPIR_Init(argu, charset_type, "0");
			String nativeBytes = null;

			if (0 == init_flag) {
				nativeBytes = CLibrary.Instance.NLPIR_GetLastErrorMsg();
				System.err.println("初始化失败！fail reason is "+nativeBytes);
				return;
			}
			// 设置词性标注集(0 计算所二级标注集，1 计算所一级标注集，2 北大二级标注集，3 北大一级标注集)
			
			// 导入用户字典
			int nCount = 0;
			System.out.println("fileP1 "+filePrefix);
			String usrdir = filePrefix + "userdict.txt"; // 用户字典路径
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(usrdir),"utf-8"));
			while(reader.ready())
			{
				String line=reader.readLine();
				if(!line.trim().equals(""))
				{
					CLibrary.Instance.NLPIR_AddUserWord(line);
					nCount++;
				}
				else{
					continue;
				}
				
			}
			reader.close();
			System.out.println("Userdict Count: "+nCount);
			nCount = 0;

			// 初始化停用词表
			File txtFile = new File(filePrefix + "stopWord.txt");
			FileInputStream in = new FileInputStream(txtFile);
			BufferedReader bw = new BufferedReader(new InputStreamReader(in, "utf-8"));
			

			while (bw.ready()) {
				
				String line = bw.readLine();
				stopWords.add(line);
				nCount++;
			}
			System.out.println("Stopwords Count: "+nCount);
			System.out.println(stopWords);
			bw.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public ArrayList<String> parseWordReturnList(String text){
		ArrayList<String> resultAL = new ArrayList<String>();
		// 导入用户字典后再分词，采取互斥量防止重入导致crash；同步量最小粒度为本实例的核心分词调用
		String nativeBytes = null;
		synchronized (paragraphProcessMutex) {
			nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(
					text, 2);
		}
		String str = nativeBytes;
		//System.out.println(nativeBytes);
		String[] finalResult = str.split(" ");
		String key = "";
		Pattern pattern = Pattern.compile("\\d{6}");//Pattern.compile("[0-9.\\-+E%点亿]*");
		for (int i = 0; i < finalResult.length; i++) {
			key = finalResult[i].trim();
			// 停用词
			if (stopWords.contains(key.split("/")[0]) || pattern.matcher(key).matches()) {
				continue;
			}
			else{
				resultAL.add(key.split("/")[0]);
			}
			
		}
		
		return resultAL;
	}

	public void exit(){
		CLibrary.Instance.NLPIR_Exit();
	}
}
