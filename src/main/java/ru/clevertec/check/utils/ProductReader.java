package ru.clevertec.check.utils;

import ru.clevertec.check.exceptions.BadRequestException;
import ru.clevertec.check.exceptions.InternalServerErrorException;
import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.model.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductReader {
	private static final String DISCOUNT_CARDS_LIST = "./src/main/resources/discountCards.csv";
	private static final String DELIMITER = ";";

	private ProductReader() {

	}

	public static List<Product> getProductsFromCsvFile(String fileWithProducts) throws InternalServerErrorException {
		if (fileWithProducts == null) {
			throw new BadRequestException();
		}
		List<Product> products = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileWithProducts))) {
			String line;
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] values = line.split(DELIMITER);
				products.add(new Product(Integer.valueOf(values[0]), values[1], Double.valueOf(values[2]),
						Integer.valueOf(values[3]), Boolean.valueOf(values[4])));
			}
		} catch (IOException e) {
			throw new InternalServerErrorException();
		}
		return products;
	}

	public static List<DiscountCard> getDiscountCardsFromCsvFile() throws InternalServerErrorException {
		List<DiscountCard> cards = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(DISCOUNT_CARDS_LIST))) {
			String line;
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] values = line.split(DELIMITER);
				cards.add(new DiscountCard(Integer.valueOf(values[0]), Integer.valueOf(values[1]),
						Integer.valueOf(values[2])));
			}
		} catch (IOException e) {
			throw new InternalServerErrorException();
		}
		return cards;
	}

}
