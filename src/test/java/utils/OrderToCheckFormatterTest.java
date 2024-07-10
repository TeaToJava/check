package utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.clevertec.check.exceptions.NotEnoughMoneyException;
import ru.clevertec.check.model.Order;
import ru.clevertec.check.utils.Validator;

public class OrderToCheckFormatterTest {

    @Test
    void checkInfo() {
        Order order = Order.getBuilder().build();

    }
}
