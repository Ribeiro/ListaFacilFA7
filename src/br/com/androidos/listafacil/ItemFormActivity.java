package br.com.androidos.listafacil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.com.androidos.listafacil.helper.ItemFormHelper;
import br.com.androidos.listafacil.infraestructure.ItemDAO;
import br.com.androidos.listafacil.model.Item;

public class ItemFormActivity extends Activity {

	
	private ItemFormHelper itemFormHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_form);
		
		Intent intent = getIntent();
		final Item itemToUpdate = (Item) intent.getSerializableExtra("selectedItem");
		Button button = (Button) findViewById(R.id.btnSaveItem);
		itemFormHelper = new ItemFormHelper(this);
		
		if (itemToUpdate != null) {
			button.setText("Atualizar");
			itemFormHelper.addToForm(itemToUpdate);
		}
		
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Item item = itemFormHelper.getItem();
				ItemDAO itemDAO = new ItemDAO(ItemFormActivity.this);
				
				if (itemToUpdate != null) {
					item.setId(itemToUpdate.getId());
				}
				
				itemDAO.save(item);
				itemDAO.close();
				
				finish();
				
			}
			
		});
		
		
	}

}