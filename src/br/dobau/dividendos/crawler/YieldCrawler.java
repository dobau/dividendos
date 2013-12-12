package br.dobau.dividendos.crawler;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.dobau.dividendos.JsoupHelper;
import br.dobau.dividendos.entity.Stock;
import br.dobau.dividendos.entity.Yield;

public class YieldCrawler implements Crawler<Yield> {
	private static final Logger logger = LoggerFactory.getLogger(StockCrawler.class);
	
	private static final String URL_PATTERN = "http://www.fundamentus.com.br/proventos.php?papel={0}&tipo=2";
	
	private static DateFormat DATE_FORMAT = new SimpleDateFormat("DD/MM/YYYY");
	private static DecimalFormat VALUE_FORMAT = new DecimalFormat("#.0000");
	
	private List<Stock> stockList;
	
	public YieldCrawler(List<Stock> stockList) {
		this.stockList = stockList;
	}

	@Override
	public List<Yield> grab() {
		List<Yield> yieldList = new ArrayList<Yield>();
		
		for (Stock stock : stockList) {
			Yield yield = grabOnePage(stock);
			if (yield != null) {
				yieldList.add(yield);
			}
		}
		
		return yieldList;
	}

	protected Yield grabOnePage(Stock stock) {
		String url = MessageFormat.format(URL_PATTERN, stock.getSymbol());
		
		try {
			Document site = JsoupHelper.get(url);
			
			Elements trs = site.select("#resultado tbody tr");

			Yield yield = new Yield();
			
			for (int iTr = 0; iTr < trs.size(); iTr++) {
				Elements tds = trs.get(iTr).select("td");
			
				String date = tds.get(0).text();
				String value = tds.get(1).text();
				String type = tds.get(2).text();
				String qtdStock = tds.get(3).text();
				
				yield.setStock(stock);
				
				if (date != null) {
					yield.setDate(DATE_FORMAT.parse(date));
				}
				
				if (value != null) {
					yield.setValue(new BigDecimal(VALUE_FORMAT.parse(value).toString()));
				}
				
				yield.setType(type);
				
				if (qtdStock != null) {
					yield.setQtdStock(new Integer(qtdStock));
				}
			}
		
			return yield;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return null;
	}
	
}
