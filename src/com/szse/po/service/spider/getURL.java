package com.szse.po.service.spider;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.CookieSpecs;
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
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class getURL {

	public static String url="http://guba.eastmoney.com/default_???.html";
	public static String changeURLIndex(String url,int index)
	{
		return url.replace("???", index+"");
	}
	public static int getPage=40;
	
	
	public static void main(String[] args) throws Exception{
		
		System.out.println(getURLList());
		
	}
	
	public static boolean SZStockFilter(String code)
	{
		Pattern p = Pattern.compile("\\d{6}");
    	Matcher m = p.matcher(code);
    	if (m.find()) 
    	{
    		if(!code.startsWith("60"))
    		{
    			return true;
    		}
    	}
		return false;
	}
	
	public static ArrayList<String> getURLList()
	{
		while(true)
		{
			
				RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();  
				CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();  
				ArrayList<String> ResultLinks=new ArrayList<String>();
				for(int i=1;i<=getPage;)
				{
					try{
						int index=i;
						//System.out.println(index);
						HttpGet httpGet = new HttpGet(changeURLIndex(url,index)); 
						HttpResponse response = httpclient.execute(httpGet);  
						HttpEntity entity = response.getEntity();
						String htmls=null;
						if (entity != null) { 
						    htmls=EntityUtils.toString(entity).replace("\t", " ");
						}
						Parser parser=Parser.createParser(htmls, "utf-8");
					    
			    		AndFilter linkFilter=new AndFilter(new TagNameFilter("a"),
			                    new HasAttributeFilter("class","note"));
			            NodeList nodes = parser.extractAllNodesThatMatch(linkFilter);
			            //System.out.println("数据条数："+nodes.size());
			            for(int j=0;j<nodes.size();j++)
			            {
			            	Node aNode=nodes.elementAt(j);
			            	LinkTag l=(LinkTag)aNode;
			            	//System.out.print("{\"http://guba.eastmoney.com/"+l.getAttribute("href")+"\",\""+html2Str(aNode.toHtml().replace(" <br />", "\",\"")).trim()+"\",\"");
			                String link="http://guba.eastmoney.com"+l.getAttribute("href");
			                String code=l.getAttribute("href").split(",")[1];
			                
			            	if(!ResultLinks.contains(link)&&SZStockFilter(code))
			            	{
			            		String title=l.getAttribute("title");
			            		ResultLinks.add(link+"####"+title);
			            		//System.out.println(l.toHtml());
			            	}
			            	
			            }
			            i++;
			            
					}
					catch(Exception ee)
					{
						ee.printStackTrace();
					}
					       
				}
				return ResultLinks;
				
			
		}
	}
	
	
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
