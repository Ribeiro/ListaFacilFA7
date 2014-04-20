package br.com.androidos.listafacil.helper;

import static br.com.androidos.listafacil.util.ViewUtil.*;
import android.widget.EditText;
import br.com.androidos.listafacil.ItemFormActivity;
import br.com.androidos.listafacil.R;
import br.com.androidos.listafacil.builder.ItemBuilder;
import br.com.androidos.listafacil.model.Item;

public class ItemFormHelper {

	private EditText itemName;
	private EditText itemQuantity;
	private EditText itemUnitPrice;
	private EditText itemTotalValue;

	public ItemFormHelper(ItemFormActivity itemForm) {
		itemName = (EditText) itemForm.findViewById(R.id.name);
		itemQuantity = (EditText) itemForm.findViewById(R.id.quantity);
		itemUnitPrice = (EditText) itemForm.findViewById(R.id.unitPrice);
		itemTotalValue = (EditText) itemForm.findViewById(R.id.totalValue);

	}

	public void addToForm(Item itemToUpdate) {
		itemName.setText(itemToUpdate.getName());
		itemQuantity.setText(String.valueOf(itemToUpdate.getQuantity()));
		itemUnitPrice.setText(String.valueOf(itemToUpdate.getUnitPrice()));
		itemTotalValue.setText(String.valueOf(itemToUpdate.getTotalValue()));

	}

	public Item getItem() {

		return ItemBuilder.anItem().withName(getStringFrom(itemName))
				.withQuantity(getIntegerFrom(itemQuantity))
				.withUnitPrice(getDoubleFrom(itemUnitPrice)).withTotalValue()
				.build();

	}

}
