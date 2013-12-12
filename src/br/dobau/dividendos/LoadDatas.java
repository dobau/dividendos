package br.dobau.dividendos;

import java.util.List;

import org.mongodb.morphia.Datastore;

import br.dobau.dividendos.crawler.StockCrawler;
import br.dobau.dividendos.crawler.YieldCrawler;
import br.dobau.dividendos.entity.Stock;
import br.dobau.dividendos.entity.Yield;

public class LoadDatas {
	
	private Datastore ds;
	
	public LoadDatas() {
		ds = DatastoreFactory.getInstance();
	}

	public void loadStocks() {
		StockCrawler stockCrawler = new StockCrawler();
		List<Stock> stockList =  stockCrawler.grab();
		
		for (Stock stock : stockList) {
			ds.save(stock);
		}
	}
	
	public void loadYields() {
		List<Stock> stockList = ds.find(Stock.class).asList();
		
		YieldCrawler yieldCrawler = new YieldCrawler(stockList);
		List<Yield> yieldList =  yieldCrawler.grab();
		
		for (Yield yield : yieldList) {
			ds.save(yield);
		}
	}
	
	public void loadAll() {
		loadStocks();
		loadYields();
	}
	
}
