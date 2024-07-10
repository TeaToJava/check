package ru.clevertec.check;

import ru.clevertec.check.dao.DiscountCardDao;
import ru.clevertec.check.dao.ProductDao;
import ru.clevertec.check.dao.impl.DiscountCardDaoImpl;
import ru.clevertec.check.dao.impl.ProductDaoImpl;
import ru.clevertec.check.db.DataSource;
import ru.clevertec.check.exceptions.BadRequestException;
import ru.clevertec.check.exceptions.InternalServerErrorException;
import ru.clevertec.check.exceptions.NotEnoughMoneyException;
import ru.clevertec.check.model.Order;
import ru.clevertec.check.service.*;
import ru.clevertec.check.service.impl.OrderServiceImpl;
import ru.clevertec.check.utils.OrderToCheckFormatter;
import ru.clevertec.check.utils.ArgsConstant;
import ru.clevertec.check.utils.ArgsParser;
import ru.clevertec.check.utils.CheckWriter;
import ru.clevertec.check.utils.Validator;

import java.util.*;

public class CheckRunner {
    private static DiscountCardDao discountCardDao = new DiscountCardDaoImpl();
    private static ProductDao productDao = new ProductDaoImpl();
    private static OrderService orderService = new OrderServiceImpl(discountCardDao,productDao);

    public static void main(String[] args)  {
        try {
            Map<String, String> params = ArgsParser.parseArguments(args);
            Properties props = new Properties();
            props.setProperty("dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource");
            props.setProperty("dataSource.user", params.get(ArgsConstant.DATASOURCE_USERNAME));
            props.setProperty("dataSource.password", params.get(ArgsConstant.DATASOURCE_PASSWORD));
            props.setProperty("dataSource.databaseName", params.get(ArgsConstant.DATASOURCE_URL));
            DataSource.setProperties(props);

            Order order = orderService.createOrder(params);
            double balance = Double.parseDouble(params.get(ArgsConstant.BALANCE_DEBIT_CARD));
            Validator.validateBalance(order.getTotalWithDiscount(),balance);
            String orderToString = OrderToCheckFormatter.checkInfo(order);
            CheckWriter.writeToConsole(orderToString);
            CheckWriter.writeToCheck(params.get(ArgsConstant.SAVE_TO_FILE), orderToString);
        }catch(BadRequestException | NotEnoughMoneyException e){
            String message = e.getMessage();
            CheckWriter.writeErrorToCheck(message);
            CheckWriter.writeToConsole(message);
        }catch(Exception e){
            String message = new InternalServerErrorException().getMessage();
            CheckWriter.writeToConsole(message);
        }
    }
}
