package ru.clevertec.check.dao.impl;

import ru.clevertec.check.dao.DiscountCardDao;
import ru.clevertec.check.model.DiscountCard;

import java.util.ArrayList;
import java.util.List;

public class DiscountCardDaoImpl implements DiscountCardDao {
	private List<DiscountCard> cardsList = new ArrayList<>();

	@Override
	public DiscountCard getCardById(int cardId) {
		return cardsList.stream().filter(c -> c.getCardNumber() == cardId).findAny().orElse(new DiscountCard());
	}

	@Override
	public List<DiscountCard> getCardList() {
		return cardsList;
	}

	@Override
	public void setCardsList(List<DiscountCard> cardList) {
		this.cardsList = cardList;
	}

}
