package com.format.app.java;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
/**
 * @name JsoupCrawler
 * @author Format
 *
 */
public class JsoupCrawler 
{
    public static void main(String[] args) throws Exception {
    	/** 1. HTTP URL Connection */
        String url = "https://www.naver.co.kr";
        Document doc = Jsoup.connect(url).get();
        
        /** 2. Print Element */
        System.out.println(doc.toString());
        System.out.println("print crawler");
        System.out.println("print scraper");
    }
}
