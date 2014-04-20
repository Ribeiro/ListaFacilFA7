package br.com.androidos.listafacil.infraestructure;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

import java.util.ArrayList;
import java.util.List;

import nl.qbusict.cupboard.QueryResultIterable;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import br.com.androidos.listafacil.model.Item;

public class ItemDAO extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "comprafacil.db";
	private static final int DATABASE_VERSION = 1;
	
	 static {
	        cupboard().register(Item.class);
	    }

	public ItemDAO(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public List<Item> getAllItens() {
		List<Item> itens = new ArrayList<Item>();
		QueryResultIterable<Item> iterable = cupboard().withDatabase(getWritableDatabase()).query(Item.class).query();
		Log.i("ItemDAO" , "Total of Item records found => " + String.valueOf(iterable.getCursor().getCount()));
		
		for (Item item : iterable) {
			itens.add(item);
		}
		
		return itens;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		cupboard().withDatabase(db).createTables();
		
	}
	
	@Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        cupboard().withDatabase(db).upgradeTables();
    }
	
	public void save(Item item) {
		Long id = cupboard().withDatabase(getWritableDatabase()).put(item);
		Log.i("ItemDAO", "Item with name " + item.getName() + " has been saved/updated with id : " + id);


	}
	
	public void delete(Item item) {
		boolean deleted = cupboard().withDatabase(getWritableDatabase()).delete(item);
		Log.i("ItemDAO", "Item with id " + item.getId() + " was deleted? " +  deleted);
	}
	
}
