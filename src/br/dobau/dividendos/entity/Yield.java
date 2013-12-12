package br.dobau.dividendos.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Reference;

/**
 * Entidade que presenta o dividendo
 * 
 * @author rafaelalves
 * 
 */
@Entity("yields")
@Index(unique=true, value="stock.symbol,date")
public class Yield {

	@Id
	private ObjectId id;

	@Reference
	private Stock stock;
	
	private Date date;
	private BigDecimal value;
	private Integer qtdStock;
	private String type;

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Integer getQtdStock() {
		return qtdStock;
	}

	public void setQtdStock(Integer qtdStock) {
		this.qtdStock = qtdStock;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
