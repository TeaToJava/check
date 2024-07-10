package ru.clevertec.check.model;

public class ProductForCheck {
	private Product product;
	private int quantity;
	private double discount;
	private double totalSum;

	public ProductForCheck() {
	}

	public ProductForCheck(Product product, int quantity, double discount, double totalSum) {
		this.product = product;
		this.quantity = quantity;
		this.discount = discount;
		this.totalSum = totalSum;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(double totalSum) {
		this.totalSum = totalSum;
	}
}
