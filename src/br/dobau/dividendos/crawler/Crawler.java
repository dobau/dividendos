package br.dobau.dividendos.crawler;

import java.util.List;

public interface Crawler<T> {

	public List<T> grab();
	
}
