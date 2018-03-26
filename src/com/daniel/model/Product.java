package com.daniel.model;

import java.util.Date;

public class Product {

	private int productId;
	private String productName;
	private int productPrice;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	@Override
	public String toString() {
		return "Product [productid=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ "]";
	}

	
	
}
