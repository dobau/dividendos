package br.dobau.dividendos;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsoupHelper {

	private static final Logger logger = LoggerFactory.getLogger(JsoupHelper.class);
	
	public static Document get(String url) throws IOException {
		logger.info("Accessing {}", url);
		
        Connection con = HttpConnection.connect(url);
        con.timeout(0);
        return con.get();
	}
	
}
