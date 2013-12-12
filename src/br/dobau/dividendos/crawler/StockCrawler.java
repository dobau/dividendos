package br.dobau.dividendos.crawler;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.dobau.dividendos.JsoupHelper;
import br.dobau.dividendos.entity.Stock;

public class StockCrawler implements Crawler<Stock> {
	
	private static final Logger logger = LoggerFactory.getLogger(StockCrawler.class);

	private static final String URL_PATTERN = "http://exame.abril.com.br/mercados/cotacoes-bovespa/acoes?letter={0}";
	
	@Override
	public List<Stock> grab() {
		List<Stock> stockList = new ArrayList<Stock>();
		
		for (int i = (int) 'A'; i <= (int) 'Z';i++) {
			try {
				String url = MessageFormat.format(URL_PATTERN, (char) i);
				Document site = JsoupHelper.get(url);
				
				Elements tr = site.select("#stocks tbody tr");
				for (int iTr = 0; iTr < tr.size(); iTr++) {
					Elements td = tr.get(iTr).select("td");
					
					String symbol = td.get(0).select("a").text();
					String description = td.get(0).select("span").text();
					
					if (!symbol.trim().isEmpty()) {
						Stock stock = new Stock();
						stock.setSymbol(symbol);
						stock.setDescription(description);
						
						stockList.add(stock);
					}
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		
		return stockList;
	}
	
	protected Stock grabOnePage() {
		return null;
	}
	
}
