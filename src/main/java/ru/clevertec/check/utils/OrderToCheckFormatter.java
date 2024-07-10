package ru.clevertec.check.utils;

import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.model.Order;
import ru.clevertec.check.model.ProductForCheck;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderToCheckFormatter {
	private static final String DELIMETER = ";";

	private OrderToCheckFormatter() {

	}

	public static String checkInfo(Order order) {
		StringBuilder str = new StringBuilder();
		str.append("Date;Time");
		str.append("\n");

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss");
		LocalDateTime localDateTime = order.getDateTime();
		str.append(dateFormatter.format(localDateTime) + DELIMETER + timeFormatter.format(localDateTime));
		str.append("\n");
		str.append("\n");
		str.append("QTY;DESCRIPTION;PRICE;DISCOUNT;TOTAL");
		str.append("\n");

		List<ProductForCheck> products = order.getProducts();
		for (ProductForCheck productForCheck : products) {
			ru.clevertec.check.model.Product product = productForCheck.getProduct();
			String productAsString = "%d;%s;%.2f$;%.2f$;%.2f$%n".formatted(product.getId(), product.getDescription(),
					product.getPrice(), productForCheck.getDiscount(), productForCheck.getTotalSum());
			str.append(productAsString);
		}
		str.append("\n");
		str.append("DISCOUNT CARD; DISCOUNT PERCENTAGE");
		str.append("\n");

		DiscountCard discountCard = order.getDiscountCard();
		str.append(String.format(("%d;%d%%%n"), discountCard.getCardNumber(), discountCard.getDiscountAmount()));
		str.append("\n");
		str.append("TOTAL PRICE; TOTAL DISCOUNT; TOTAL WITH DISCOUNT");
		str.append("\n");
		str.append(String.format(("%.2f$;%.2f$;%.2f$%n"), order.getTotalPrice(), order.getTotalDiscount(),
				order.getTotalWithDiscount()));
		return str.toString();
	}
}
