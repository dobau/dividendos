package br.dobau.dividendos.entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Entidade que representa o papel da ação
 * 
 * @author rafaelalves
 * 
 */
@Entity("stocks")
public class Stock {

	@Id
	private String symbol;
	private String description;
	
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
