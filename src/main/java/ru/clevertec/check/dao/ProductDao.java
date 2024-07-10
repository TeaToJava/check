package ru.clevertec.check.dao;

import ru.clevertec.check.model.Product;

import java.util.List;

public interface ProductDao {
    Product getProductById(int productId);
    List<Product> getProductList();
    void setProductList(List<Product> products);
}
