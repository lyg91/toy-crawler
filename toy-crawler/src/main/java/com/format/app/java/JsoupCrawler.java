package com.format.app.java;

import java.io.File;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * @name JsoupCrawler
 * @author Format
 *
 */
public class JsoupCrawler 
{
    public static void main(String[] args) throws Exception {
    	
    	File file = new File("D:\\crawler.txt");
    	String newLine = System.getProperty("line.separator");
    	
    	/** 1. HTTP Connection */
        String mainURL = "https://news.naver.com";
        String subURL = "/main/ranking/popularDay.nhn?mid=etc&sid1=111";
        Document doc = Jsoup.connect(mainURL + subURL).get();
        
        /** 2. get NEWS TOP5 list */
        Elements news = doc.select(".rankingnews_box");
        
        for(Element n : news) {
            /** 3. get NEWS company name */
        	String companyName = n.select(".rankingnews_name").text();
        	Elements top5 = n.select(".rankingnews_list li");
        	
        	FileUtils.writeStringToFile(file, companyName + newLine, "UTF-8", true);
        	
        	/** 4. get NEWS TOP5 contents */
        	for(Element t : top5) {
	    		String rankNumber = t.select(".list_ranking_num").text();
	    		String rankContents = t.select(".list_content a").text();
	    		String nextUrl = t.select(".list_content a").attr("href");
	    		
	    		if(!StringUtils.isBlank(rankNumber) && !StringUtils.isBlank(rankContents)) {
	    			FileUtils.write(file, rankNumber + " " + rankContents + newLine, "UTF-8", true );
	    			
	    			/** 5. Next Contents link */
	    			Document nextDoc = Jsoup.connect(mainURL + nextUrl).get();
	    			String nextContents = nextDoc.select("#articleBodyContents").text();
	    			FileUtils.write(file, nextContents + newLine, "UTF-8", true );
	    		}
        	}
        	
        	FileUtils.writeStringToFile(file, newLine, "UTF-8", true);
        }
        
        System.out.println("done");
    }
}
