package ru.clevertec.check.dao.impl;

import ru.clevertec.check.dao.ProductDao;
import ru.clevertec.check.db.DataSource;
import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private static final String ID = "id";
    private static final String DESCRIPTION = "description";
    private static final String PRICE = "price";
    private static final String QUANTITY_IN_STOCK = "quantity_in_stock";
    private static final String WHOLESALE_PRODUCT = "wholesale_product";
    private static final String GET_PRODUCT = "SELECT * FROM public.product WHERE id=?";
    private static final String GET_ALL_PRODUCTS = "SELECT * FROM public.product WHERE id=?";
    private static final String UPDATE_PRODUCT = "UPDATE public.product SET quantity_in_stock = ? WHERE id = ?";

    private List<Product> products = new ArrayList<>();
    private int maxProductId;
    public ProductDaoImpl() {
    }


    public Product getProductById(int productId) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(GET_PRODUCT)) {
            pst.setObject(1, productId);
            try (ResultSet rs = pst.executeQuery()) {
                Product product = new Product();
                while (rs.next()) {
                    product.setId(Long.parseLong(rs.getString(ID)));
                    product.setDescription(rs.getString(DESCRIPTION));
                    product.setPrice(Double.parseDouble(rs.getString(PRICE)));
                    product.setQuantityInStock(Integer.parseInt(rs.getString(QUANTITY_IN_STOCK)));
                    product.setWholesaleProduct(Boolean.parseBoolean(rs.getString(WHOLESALE_PRODUCT)));
                }
                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateProduct(Product product) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_PRODUCT);) {
             pst.setObject(1, product.getQuantityInStock());
             pst.setObject(2, product.getId());
             pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
