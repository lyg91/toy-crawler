package com.format.app.java;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupCrawler 
{
    public static void main(String[] args) throws Exception {
    	/** 1. HTTP URL Connection */
        String url = "https://www.naver.co.kr";
        Document doc = Jsoup.connect(url).get();
        
        System.out.println(doc.toString());
    }
}
