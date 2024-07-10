package ru.clevertec.check.dao;

import ru.clevertec.check.model.DiscountCard;

import java.util.List;

public interface DiscountCardDao {
    List<DiscountCard> getCardList();
    void setCardsList(List<DiscountCard> cardsList);
    DiscountCard getCardById(int cardId);
}