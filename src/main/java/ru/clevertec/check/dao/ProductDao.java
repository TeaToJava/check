package ru.clevertec.check.dao;

import ru.clevertec.check.model.Product;

public interface ProductDao {
	Product getProductById(int productId);

	void updateProduct(Product product);
}
