package com.format.app.java;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.format.app.util.URLUtils;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class CrawlerModule extends WebCrawler {
	/** Frontier Module */
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String urls = url.getURL();
		return urls.startsWith("https://news.naver.com/main/ranking/read.nhn");
	}
	
	/** Agent Module */
	@Override
	public void visit(Page page){
		File file = new File("D:\\crawler4j.txt");
    	String newLine = System.getProperty("line.separator");
		
		try {
			/** 1. get HTML Document */
			HtmlParseData data = (HtmlParseData) page.getParseData();
			Document doc = Jsoup.parse(data.getHtml());
			
			/** 2. get NEWS TOP5 list */
			Elements news = doc.select(".rankingnews_box");
			 
			/** 3. get NEWS TOP5 company & title */
			for(Element n : news) {
				String companyName = n.select(".rankingnews_name").text();
				Elements rankList = n.select("li");
				
				for(Element r : rankList) {
					String rankNumber = r.select(".list_ranking_num").text();
		    		String rankContents = r.select(".list_content a").text();
		    		String rankUrl = r.select(".list_content a").attr("href");
		    		String aid = URLUtils.getParameterValue(rankUrl, "aid");
		    		
		    		StringBuilder titleRow = new StringBuilder();
		    		
		    		titleRow.append(aid).append(" ")
			    			.append(companyName).append(" ")
			    		    .append(rankNumber).append(" ")
			    		    .append(rankContents).append(" ");
		    		      
		    		
					FileUtils.write(file, titleRow + newLine , "UTF-8", true );
				}
			}
			
			/** 4. get NEWS TOP5 contents */
			String contents = doc.select("#articleBodyContents").text();
			String contentsAid = URLUtils.getParameterValue(page.getWebURL().getURL(),"aid");
			
			StringBuilder contentsRow = new StringBuilder();
			
			contentsRow.append(contentsAid).append(" ")
					   .append(contents).append(" ");
			
			FileUtils.write(file, contentsRow + newLine, "UTF-8", true );
			
		} catch(Exception e) {
			
		}
		
	}
}
