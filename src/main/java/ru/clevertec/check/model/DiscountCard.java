package ru.clevertec.check.model;

import java.util.Objects;

public class DiscountCard {

    private static final int DEFAULT_DISCOUNT = 2;
    private long id;
    private int cardNumber;
    private int discountAmount = DEFAULT_DISCOUNT;

    public DiscountCard(){

    }
    public DiscountCard(int id, int cardNumber) {
        this.id = id;
        this.cardNumber = cardNumber;
    }

    public DiscountCard(int cardNumber) {
        this.cardNumber = cardNumber;
    }
    public DiscountCard(int id, int cardNumber, int discountAmount) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.discountAmount = discountAmount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountCard that = (DiscountCard) o;
        return id == that.id && cardNumber == that.cardNumber && discountAmount == that.discountAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardNumber, discountAmount);
    }
}
