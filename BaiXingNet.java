package com.jd.wife;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.oro.text.regex.PatternMatcherInput;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;

/**
 * 获取百姓网的帖子电话
 * @author kaizhang
 *
 */
public class BaiXingNet {
	public static String URL_HEADER = "http://shanghai.baixing.com/yinger/?page=";
	public static void main(String[] args) throws IOException, Exception {
		List<String> aa = new ArrayList<String>();
		DefaultHttpClient client = new DefaultHttpClient();
		for (int i = 13; i <= 14; i++) {
			List<String> urls = getTieUrls(client,URL_HEADER+i);
			for(String u : urls) {
				//aa = getMobile(aa, client, "http://beijing.baixing.com/yinger/a280144697.html");
				aa = getMobile(aa, client, u);
			}
			System.out.println("第"+i+"页分析完成...");
		}
		System.out.println(aa.toString()+"\t"+aa.size());
	}
	
	
	private static List<String> getMobile(List<String> res,DefaultHttpClient client, String url) throws Exception {
		HttpGet getMethod = new HttpGet(url);
		HttpResponse response = client.execute(getMethod);
		String entity = EntityUtils.toString(response.getEntity());
		String aa = getNum("<span id='num' class='pull-left'>(.*?)</span>", entity);
		String bb = getNum1("<a data-contact='(.*?)' href='###' class='show-contact button'>", entity);
		res.add(aa+bb);
		return res;
	}


	public static List<String> getTieUrls(DefaultHttpClient client, String url) throws IOException, Exception {
		HttpGet getMethod = new HttpGet(url);
		HttpResponse response = client.execute(getMethod);
		String entity = EntityUtils.toString(response.getEntity());
		//getTargetUrl(entity);
		return simpleContains1("<a href='(.*?)' target='_blank' class='media-cap'>",entity);
	}
	
	public static String getNum1(String pat,String content) throws Exception { 
		List<String> contextList = new ArrayList<String>();
		org.apache.oro.text.regex.Pattern pattern = new Perl5Compiler().compile(pat); 
		Perl5Matcher matcher = new Perl5Matcher(); 
		PatternMatcherInput matcherInput = new PatternMatcherInput(content); 
		while (matcher.contains(matcherInput, pattern)) { 
			org.apache.oro.text.regex.MatchResult result = matcher.getMatch(); 
			String a = result.toString();
			return a.split("<a data-contact='")[1].split("'")[0];
		} 
		return null;
		} 
	public static String getNum(String pat,String content) throws Exception { 
		List<String> contextList = new ArrayList<String>();
		org.apache.oro.text.regex.Pattern pattern = new Perl5Compiler().compile(pat); 
		Perl5Matcher matcher = new Perl5Matcher(); 
		PatternMatcherInput matcherInput = new PatternMatcherInput(content); 
		while (matcher.contains(matcherInput, pattern)) { 
			org.apache.oro.text.regex.MatchResult result = matcher.getMatch(); 
			String a = result.toString();
			return a.split(">")[1].split("<")[0].substring(0, 7);
		} 
		return null;
		} 
	
	public static List<String> simpleContains1(String pat,String content) throws Exception { 
		List<String> contextList = new ArrayList<String>();
		org.apache.oro.text.regex.Pattern pattern = new Perl5Compiler().compile(pat); 
		Perl5Matcher matcher = new Perl5Matcher(); 
		PatternMatcherInput matcherInput = new PatternMatcherInput(content); 
		while (matcher.contains(matcherInput, pattern)) { 
			org.apache.oro.text.regex.MatchResult result = matcher.getMatch(); 
			String a = result.toString();
			try {
				contextList.add(a.split("' target='_blank'")[0].split("<a href='")[1]);
			} catch (Exception e) {
				continue;
			}
		
		} 
		return contextList;
		} 
	
    public static void getTargetUrl(String html) {
        List<String> resultList = new ArrayList<String>();
        //System.out.println(html);
       // Pattern p = Pattern.compile("<a target=\"_blank\" href=\".*\">");
        Pattern p = Pattern.compile("<a href='.*' target='_blank' class='media-cap'>");
        Matcher m = p.matcher(html );//开始编译
        while (m.find()) {
           String[] ss = m.group(0).split("target='_blank'");
           for (String sss : ss) {
        	   System.out.println(sss);
			
		}
        }
    }
	
	
	
}
