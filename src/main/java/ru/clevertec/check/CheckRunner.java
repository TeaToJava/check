package ru.clevertec.check;

import ru.clevertec.check.dao.DiscountCardDao;
import ru.clevertec.check.dao.ProductDao;
import ru.clevertec.check.dao.impl.DiscountCardDaoImpl;
import ru.clevertec.check.dao.impl.ProductDaoImpl;
import ru.clevertec.check.exceptions.BadRequestException;
import ru.clevertec.check.exceptions.InternalServerErrorException;
import ru.clevertec.check.exceptions.NotEnoughMoneyException;
import ru.clevertec.check.model.Order;
import ru.clevertec.check.service.*;
import ru.clevertec.check.utils.ArgsConstant;
import ru.clevertec.check.utils.ArgsParser;
import ru.clevertec.check.utils.CheckWriter;
import ru.clevertec.check.utils.OrderToCheckFormatter;
import ru.clevertec.check.utils.ProductReader;
import ru.clevertec.check.utils.Validator;

import java.io.*;
import java.util.*;

public class CheckRunner {
	private static DiscountCardDao discountCardDao = new DiscountCardDaoImpl();
	private static ProductDao productDao = new ProductDaoImpl();

	public static void main(String[] args){
		try {
			Map<String, String> params = ArgsParser.parseArguments(args);
			discountCardDao.setCardsList(ProductReader.getDiscountCardsFromCsvFile());
			productDao.setProductList(ProductReader.getProductsFromCsvFile(params.get(ArgsConstant.PATH_TO_FILE)));
			OrderServiceImpl orderService = new OrderServiceImpl(discountCardDao, productDao);
			Order order = orderService.createOrder(params);
			double balance = Double.parseDouble(params.get(ArgsConstant.BALANCE_DEBIT_CARD));
			Validator.validateBalance(order.getTotalWithDiscount(), balance);
			String orderToString = OrderToCheckFormatter.checkInfo(order);
			CheckWriter.writeToConsole(orderToString);
			CheckWriter.writeToCheck(ArgsConstant.SAVE_TO_FILE, orderToString);
		} catch (BadRequestException | NotEnoughMoneyException | InternalServerErrorException e) {
			CheckWriter.writeErrorToCheck(e.getMessage());
			CheckWriter.writeToConsole(e.getMessage());
		} catch (Exception e) {
			CheckWriter.writeToConsole(e.getMessage());
		}
	}
}
