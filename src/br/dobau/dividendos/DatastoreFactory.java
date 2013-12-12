package br.dobau.dividendos;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import br.dobau.dividendos.entity.Stock;
import br.dobau.dividendos.entity.Yield;

import com.mongodb.MongoClient;

public class DatastoreFactory {
	
	private static Datastore datasore;
	
	public static Datastore getInstance() {
		if (datasore == null) {
			Morphia morphia = new Morphia();
			morphia.map(Stock.class);
			morphia.map(Yield.class);
			
			try {
				datasore = morphia.createDatastore(new MongoClient(), "stockmarket");
			} catch(Exception e) {
				throw new Error(e);
			}
		}
		
		return datasore;
	}

}
