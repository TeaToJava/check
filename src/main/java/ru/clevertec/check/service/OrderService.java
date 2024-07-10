package ru.clevertec.check.service;

import ru.clevertec.check.exceptions.BadRequestException;
import ru.clevertec.check.model.Order;

import java.util.Map;

public interface OrderService {
    Order createOrder(Map<String, String> params) throws BadRequestException;
}
