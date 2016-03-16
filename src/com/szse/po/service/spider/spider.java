package com.szse.po.service.spider;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.HtmlPage;

import com.szse.po.dao.*;

public class spider {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<String> urls=getURL.getURLList();
		for(int i=0;i<urls.size();i++)
		{
			HashMap<String,String> r=GetDetails(urls.get(i));
			//System.out.println(r.get("time"));
			saveTextData(r);
		}
		/*Textdata td=new Textdata();
		TextdataDAO ud=new TextdataDAO();
		td=ud.findById(1);
		String a=(td.getContent());
		try {
			System.out.println(new String(a.getBytes("utf-8"),"gbk"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	
	public static void saveTextData(HashMap<String,String> data)
	{
		Textdata td=new Textdata();
		//System.out.println(data.get("content"));
		/*try {
			td.setContent(new String(data.get("content").getBytes("GBK"), "GBK"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		td.setContent(data.get("content"));
		td.setTime(data.get("time"));
		td.setUrl(data.get("url"));
		td.setTitle(data.get("title"));
		TextdataDAO ud=new TextdataDAO();
		ud.save(td);
		System.out.println(td.getTid());
		
	}

	public static String cleanJSCSS(String html)
	{
		return html.replaceAll("<script[\\s\\S]*?/script>", "").replace("<style[\\s\\S]*?/style>", "");
	}
	
	

	public static HashMap<String,String> GetDetails(String url)
	{
		String title=url.split("####")[1];
		
		url=url.split("####")[0];
		while(true)
		{
			try{
				HashMap<String,String> result=new HashMap<String,String>();
				RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();  
				CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();  
				
				HttpGet httpGet = new HttpGet(url); 
				HttpResponse response = httpclient.execute(httpGet);  
				HttpEntity entity = response.getEntity();
				
				String htmls=null;
				if (entity != null) { 
				    htmls=EntityUtils.toString(entity).replace("\t", " ");
				    //System.out.println(htmls);
				    
				     
				}
				//System.out.println("Got reply!");
				htmls=cleanJSCSS(htmls);
				Parser parser=null;
			    //HtmlPage page=new HtmlPage(parser); 
			    
			    
			    //**************************get time**********************
			    //zwfbtime
			    //
	        	parser=Parser.createParser(htmls, "utf-8");
	        	AndFilter TimeFilter=new AndFilter(new TagNameFilter("div"),
		                new HasAttributeFilter("class","zwfbtime"));
		        NodeList nodes1 = parser.extractAllNodesThatMatch(TimeFilter);
		        if(nodes1.size()>0)
		        {
		    		String row=(nodes1.elementAt(0).toHtml());
		    		String time=(html2Str(row.replace(">", "> "))).replace("\r", "");
		    		time=time.replace("\n", " ");
		    		time=HTMLFilter(time);
		    		time=time.replace("发表于 ", "").replace(" 股吧网页版", "");
		    		//\d{4}-\d{0,2}-\d{0,2}\s*(\d{0,2}:\d{0,2}:\d{0,2})?
		    		Pattern p = Pattern.compile("\\d{4}-\\d{0,2}-\\d{0,2}\\s*(\\d{0,2}:\\d{0,2}:\\d{0,2})?");
			    	Matcher m = p.matcher(time);
			    	while (m.find()) 
			    	{
			    		result.put("time",m.group());
			    		break;
			    	}
		    		
    	    		
		        }
		        
		      //**********************************get content**********************
				parser=Parser.createParser(htmls, "utf-8");
			    AndFilter SFilter=new AndFilter(new TagNameFilter("div"),
		                new HasAttributeFilter("id","zwconbody"));
		        NodeList nodes2 = parser.extractAllNodesThatMatch(SFilter);
		        if(nodes2.size()>0)
		        {
		    		String row=(nodes2.elementAt(0).toHtml());
		    		String structure=(html2Str(row.replace(">", "> "))).replace("\r", "");
		    		structure=structure.replace("\n", " ");
          	    	structure=HTMLFilter(structure);
    	    		result.put("content",structure);
		        }
		        
		        result.put("title",title);
		        result.put("url", url);
		        
			   
				httpclient.close();
		        return result;
			}
			catch(Exception ee)
			{
				System.out.println("Retrying...");
				ee.printStackTrace();
			}
		}
		
	}//...
	public static String html2Str(String html) { 
		return html.replaceAll("<[^>]+>", "");
	}
	public static String HTMLFilter(String input) {
	    if (input == null) {
	        input = "";
	        return input;
	    }
	    input = input.trim().replaceAll("&amp;", "&");
	    input = input.trim().replaceAll("&lt;", "<");
	    input = input.trim().replaceAll("&gt;", ">");
	    input = input.trim().replaceAll("    ", " ");
	    input = input.trim().replaceAll("<br>", "\n");
	    input = input.trim().replaceAll("&nbsp;", "  ");
	    input = input.trim().replaceAll("&quot;", "\"");
	    input = input.trim().replaceAll("&#39;", "'");
	    input = input.trim().replaceAll("&#92;", "\\\\");
	    input = input.trim().replaceAll("&#...;", "");
	    input = input.trim().replaceAll("&#....;", "");
	    return input;
	}
		
}
