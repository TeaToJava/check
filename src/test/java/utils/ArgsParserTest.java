package utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.clevertec.check.utils.ArgsParser;

import java.util.Map;

public class ArgsParserTest {
    @Test
    void validateParamParser() {
        Map<String, String> params = Map.of("discountCard", "1111", "2", "3","balanceDebitCard", "100", "saveToFile", "./result.csv");
        Map<String, String> parsedParams = ArgsParser
                .parseArguments(new String[]{"discountCard=1111", "2-1", "2-2", "balanceDebitCard=100", "saveToFile=./result.csv"});
        Assertions.assertEquals(params, parsedParams);
    }
}
