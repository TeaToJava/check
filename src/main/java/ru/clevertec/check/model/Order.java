package ru.clevertec.check.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
	private List<ProductForCheck> products;
	private DiscountCard discountCard;
	private double balanceDebitCard;
	private LocalDateTime dateTime;
	private double totalPrice;
	private double totalDiscount;
	private double totalWithDiscount;

	private Order(OrderBuilder orderBuilder) {
		this.products = orderBuilder.getProducts();
		this.discountCard = orderBuilder.getDiscountCard();
		this.dateTime = orderBuilder.getDateTime();
		this.totalPrice = orderBuilder.getTotalPrice();
		this.totalDiscount = orderBuilder.getTotalDiscount();
		this.totalWithDiscount = orderBuilder.getTotalWithDiscount();
	}

	public DiscountCard getDiscountCard() {
		return discountCard;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public double getTotalDiscount() {
		return totalDiscount;
	}

	public double getTotalWithDiscount() {
		return totalWithDiscount;
	}

	public void setDiscountCard(DiscountCard discountCard) {
		this.discountCard = discountCard;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setTotalDiscount(double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public void setTotalWithDiscount(double totalWithDiscount) {
		this.totalWithDiscount = totalWithDiscount;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public void setProducts(List<ProductForCheck> products) {
		this.products = products;
	}

	public List<ProductForCheck> getProducts() {
		return products;
	}

	public static OrderBuilder getBuilder() {
		return new OrderBuilder();
	}

	public static class OrderBuilder {
		private List<ProductForCheck> products = new ArrayList<>();
		private DiscountCard discountCard;
		private double balanceDebitCard;
		private LocalDateTime dateTime;
		private double totalPrice;
		private double totalDiscount;
		private double totalWithDiscount;

		public Order build() {
			return new Order(this);
		}

		public List<ProductForCheck> getProducts() {
			return products;
		}

		public DiscountCard getDiscountCard() {
			return discountCard;
		}

		public LocalDateTime getDateTime() {
			return LocalDateTime.now();
		}

		public double getTotalPrice() {
			return totalPrice;
		}

		public double getTotalDiscount() {
			return totalDiscount;
		}

		public double getTotalWithDiscount() {
			return totalWithDiscount;
		}

		public void setProducts(List<ProductForCheck> products) {
			this.products = products;
		}

		public OrderBuilder setDiscountCard(DiscountCard discountCard) {
			this.discountCard = discountCard;
			return this;
		}

		public OrderBuilder setBalanceDebitCard(double balanceDebitCard) {
			this.balanceDebitCard = balanceDebitCard;
			return this;
		}

		public OrderBuilder setDateTime(LocalDateTime dateTime) {
			this.dateTime = dateTime;
			return this;
		}

		public OrderBuilder setTotalPrice(double totalPrice) {
			this.totalPrice = totalPrice;
			return this;
		}

		public OrderBuilder setTotalDiscount(double totalDiscount) {
			this.totalDiscount = totalDiscount;
			return this;
		}

		public OrderBuilder setTotalWithDiscount(double totalWithDiscount) {
			this.totalWithDiscount = totalWithDiscount;
			return this;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Order order = (Order) o;
		return Double.compare(balanceDebitCard, order.balanceDebitCard) == 0
				&& Double.compare(totalPrice, order.totalPrice) == 0
				&& Double.compare(totalDiscount, order.totalDiscount) == 0
				&& Double.compare(totalWithDiscount, order.totalWithDiscount) == 0
				&& Objects.equals(products, order.products) && Objects.equals(discountCard, order.discountCard)
				&& Objects.equals(dateTime, order.dateTime);
	}

	@Override
	public int hashCode() {
		return Objects.hash(products, discountCard, balanceDebitCard, dateTime, totalPrice, totalDiscount,
				totalWithDiscount);
	}
}