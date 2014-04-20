package br.com.androidos.listafacil.builder;

import br.com.androidos.listafacil.model.Item;

public class ItemBuilder {
	
	protected Item item;
	
	public ItemBuilder() {
		this.item = new Item();
	}
	
	public static ItemBuilder anItem() {
		return new ItemBuilder();
	}
	
	public Item build() {
		return this.item;
	}
	
	public ItemBuilder withId(final Long id) {
		this.item.setId(id);
		return this;
	}
	
	public ItemBuilder withName(final String name) {
		this.item.setName(name);
		return this;
	}
	
	public ItemBuilder withQuantity(final Integer quantity) {
		this.item.setQuantity(quantity);
		return this;
	}
	
	public ItemBuilder withUnitPrice(final Double unitPrice) {
		this.item.setUnitPrice(unitPrice);
		return this;
	}
	
	public ItemBuilder withTotalValue() {
		this.item.setTotalValue();
		return this;
	}

}
