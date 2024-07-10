package ru.clevertec.check.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.clevertec.check.exceptions.BadRequestException;
import ru.clevertec.check.exceptions.NotEnoughMoneyException;
import ru.clevertec.check.utils.Validator;

import java.util.Map;

public class ValidatorTest {
    @Test
    void validateBalanceTest() {
        int sum = 12;
        int balance = 1;
        Assertions.assertThrows(NotEnoughMoneyException.class, ()-> Validator.validateBalance(sum, balance));
    }

    @Test
    void validateInputTest() {
        Map<String, String> params = Map.of("balanceDebitCard", "2");
        Assertions.assertThrows(BadRequestException.class, ()-> Validator.validateInput(params));
    }
}
