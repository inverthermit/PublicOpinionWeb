package com.szse.po.sentiment.analysis.NLPIR.code;

import java.io.UnsupportedEncodingException;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class NlpirTest {

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
		String argu = "D:\\MyEclipse2015WorkSpace\\JnaTest_NLPIR\\ICTCLAS2015";
		// String system_charset = "GBK";//GBK----0
		String system_charset = "UTF-8";
		int charset_type = 1;
		
		int init_flag = CLibrary.Instance.NLPIR_Init(argu, charset_type, "0");
		String nativeBytes = null;

		if (0 == init_flag) {
			nativeBytes = CLibrary.Instance.NLPIR_GetLastErrorMsg();
			System.err.println("初始化失败！fail reason is "+nativeBytes);
			return;
		}

		String sInput = "今天大盘在晚间消息面偏向利空的带动下，两市大盘低开低走，最终以下跌19点报收，盘面上，两市个股涨跌互现，两市共40只个股涨停，没有个股跌停。  早盘我发帖分析说“板块上，昨天券商基本全线涨停，周小川鼓励储蓄入市对银行股构成利空，所以，昨天银行股回调，但晚间这条消息已经被澄清，今天建议关注银行的动态，如果券商回调，银行股不能启动，今天大盘或将出现跳水，回踩2970点附近”。结果今天券商回调，银行股强势护盘，大盘虽没有回踩2970点附近，但目前的发展态势还在我的预期之内。如能回踩到2950附近将是绝对的价值区域不可错过。  要注意今天的几个现象： 一是今天两市涨停家数远低于昨天涨停家数，主要是近期个股涨幅较大，目前都将面临回调； 二是今天大盘回调量能大幅萎缩，显示前期入场的资金没有出逃，所以，今天大盘还是正常调整； 三是今天大盘没有完全回补2970点附近的跳空缺口，显示大盘走势很强，这点主要是因为银行股强势的原因，但昨天的跳空缺口是周线缺口，没有留下来的必要，最终会被回补，迟补不如早补！ 所以，明天大盘还有回调，大盘还将回踩2970点附近，这附近大盘有望再次企稳，上攻3200点附近。  操作上，建议大家逢低加仓，随后持股待涨。";

		//String nativeBytes = null;
		try {
			nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);

			System.out.println("分词结果为： " + nativeBytes);
			
			//CLibrary.Instance.NLPIR_AddUserWord("我就呵呵了啊啊啊 n");
			CLibrary.Instance.NLPIR_AddUserWord("华玉米的产地来源 n");
			nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);
			System.out.println("增加用户词典后分词结果为： " + nativeBytes);
			
			CLibrary.Instance.NLPIR_DelUsrWord("要求美方加强对输");
			nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);
			System.out.println("删除用户词典后分词结果为： " + nativeBytes);
			
			
			int nCountKey = 0;
			String nativeByte = CLibrary.Instance.NLPIR_GetKeyWords(sInput, 10,false);

			System.out.print("关键词提取结果是：" + nativeByte);

			nativeByte = CLibrary.Instance.NLPIR_GetFileKeyWords("D:\\NLPIR\\feedback\\huawei\\5341\\5341\\产经广场\\2012\\5\\16766.txt", 10,false);

			System.out.print("关键词提取结果是：" + nativeByte);

			

			CLibrary.Instance.NLPIR_Exit();

		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

	}
}
