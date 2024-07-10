package ru.clevertec.check.dao;

import ru.clevertec.check.model.DiscountCard;

public interface DiscountCardDao {
	DiscountCard getCardByNumber(int cardNumber);
}
