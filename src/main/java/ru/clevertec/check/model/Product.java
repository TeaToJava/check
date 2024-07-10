package ru.clevertec.check.model;

import java.util.Objects;

public class Product {
	private int id;
	private String description;
	private double price;
	private int quantityInStock;
	private boolean isWholesaleProduct;

	public Product() {

	}

	public Product(int id, String description, double price, int quantityInStock, boolean isWholesaleProduct) {
		this.id = id;
		this.description = description;
		this.price = price;
		this.quantityInStock = quantityInStock;
		this.isWholesaleProduct = isWholesaleProduct;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public boolean isWholesaleProduct() {
		return isWholesaleProduct;
	}

	public void setWholesaleProduct(boolean wholesaleProduct) {
		isWholesaleProduct = wholesaleProduct;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Product product = (Product) o;
		return id == product.id && Double.compare(price, product.price) == 0
				&& isWholesaleProduct == product.isWholesaleProduct && Objects.equals(description, product.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, description, price, isWholesaleProduct);
	}
}
