package br.com.androidos.listafacil;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;
import br.com.androidos.listafacil.adapter.ItemAdapter;
import br.com.androidos.listafacil.infraestructure.ItemDAO;
import br.com.androidos.listafacil.model.Item;

public class ItemListingActivity extends Activity {

	private List<Item> itensListing;

	private ItemDAO itemDAO;

	private ListView listView;

	private Item currentItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_listing);
		listView = (ListView) findViewById(R.id.itensListing);
		registerForContextMenu(listView);
		itemDAO = new ItemDAO(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.item_listing, menu);
		return super.onCreateOptionsMenu(menu);		
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		listItems();
	}
	
	private void listItems() {
		itensListing = itemDAO.getAllItens();
		ItemAdapter adapter = new ItemAdapter(this, itensListing);
		listView.setAdapter(adapter);
		addOnItemClickListenerTo(listView);
		addOnItemLongClickListenerTo(listView);
	}
	
	private void addOnItemClickListenerTo(ListView listView){
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position,
					long id) {
				
				currentItem = (Item) adapterView.getItemAtPosition(position);
				Intent intent = new Intent(ItemListingActivity.this, ItemFormActivity.class);
				intent.putExtra("selectedItem", currentItem);
				startActivity(intent);
				
			}
			
		});
		
	}
	
	private void addOnItemLongClickListenerTo(ListView listView){
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				
				currentItem = (Item) adapterView.getItemAtPosition(position);
				
				return false;
			}
			
		});
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int clickedItem = item.getItemId();
		
		switch (clickedItem) {
		case R.id.newItem:
			startActivity(new Intent(this, ItemFormActivity.class)); 
			break;

		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		MenuItem delete = menu.add("Excluir");
		delete.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(ItemListingActivity.this);
		        alertDialog.setTitle("Confirmação de Exclusão ...");
		        alertDialog.setMessage("Deseja realmente excluir o produto ?");
		        alertDialog.setIcon(R.drawable.delete_icon);
		 
		        alertDialog.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog,int which) {
		            	itemDAO.delete(currentItem);
						itemDAO.close();
						listItems();
		 
		            }
		        });
		 
		        alertDialog.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int which) {
		            dialog.cancel();
		            
		            }
		        });
		 
		        alertDialog.show();
				
				return false;
			}
			
		});
		
		super.onCreateContextMenu(menu, v, menuInfo);
		
	}

}