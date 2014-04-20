package br.com.androidos.listafacil.model;

import java.io.Serializable;

public class Item implements Serializable{
	private static final long serialVersionUID = 4909272574820844746L;
	private Long _id;
	private String name;
	private Integer quantity;
	private Double unitPrice;
	private Double totalValue;
	
	public Item(){
		
	}
	
	public Long getId() {
		return _id;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public Double getUnitPrice() {
		return unitPrice;
	}
	
	public Double getTotalValue() {
		return totalValue;
	}
	
	public void setId(Long id) {
		this._id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public void setTotalValue() {
		this.totalValue = unitPrice * quantity;
	}

}