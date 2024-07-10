package ru.clevertec.check.dao.impl;

import ru.clevertec.check.dao.ProductDao;
import ru.clevertec.check.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private List<Product> products = new ArrayList<>();

    public Product getProductById(int productId) {
        return products.stream()
                .filter(p->p.getId()==productId)
                .findAny().orElse(null);
    }

    @Override
    public List<Product> getProductList() {
        return products;
    }

    @Override
    public void setProductList(List<Product> products) {
        this.products = products;
    }

}
