package com.format.app.java;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Crawler4JCrawler {
	public static void main(String[] args) throws Exception {
		
		String startURL = "https://news.naver.com/main/ranking/popularDay.nhn?mid=etc&sid1=111"; /** 크롤링 시작 URL */
		int agentCount = 3;			/** 병렬 실행할 Agent 수 */
		int agentMaxDepth = 1;  	/** Agent가 탐색할 하이퍼링크 Depth  */
		
		/** Crawler 설정 파일 경로 */
		CrawlConfig config = new CrawlConfig();
		config.setMaxDepthOfCrawling(agentMaxDepth);
		config.setCrawlStorageFolder("D:\\cralwer");
		
		/** Robots.txt 규약 설정 */
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		robotstxtConfig.setEnabled(false);
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		
		/** 크롤링 시작 */
		CrawlController crawlController = new CrawlController(config, pageFetcher, robotstxtServer);
		crawlController.addSeed(startURL);
		crawlController.start(CrawlerModule.class, agentCount);
	}
}
