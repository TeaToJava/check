package ru.clevertec.check.service.impl;

import ru.clevertec.check.dao.DiscountCardDao;
import ru.clevertec.check.dao.ProductDao;
import ru.clevertec.check.exceptions.BadRequestException;
import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.model.Order;
import ru.clevertec.check.model.Product;
import ru.clevertec.check.model.ProductForCheck;
import ru.clevertec.check.service.OrderService;
import ru.clevertec.check.utils.DoubleRounderUtil;
import ru.clevertec.check.utils.Validator;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    private static final int WHOLESALE_DISCOUNT = 10;
    private static final int PRODUCT_QUANTITY_FOR_WHOLESALE_DISCOUNT = 5;

    private static String regex = "\\d+";
    private DiscountCardDao discountCardDao;
    private ProductDao productDao;

    public OrderServiceImpl(DiscountCardDao discountCardDao, ProductDao productDao){
        this.discountCardDao = discountCardDao;
        this.productDao = productDao;
    }

    public Order createOrder(Map<String, String> params) throws BadRequestException{
        Validator.validateInput(params);
        Order order = Order.getBuilder().build();
        DiscountCard discountCard = getCardFromParameters(params);
        order.setDiscountCard(discountCard);
        List<ProductForCheck> products = getProductsForCheck(getProductFromParams(params), discountCard);
        order.setProducts(products);
        double price = DoubleRounderUtil.roundDouble(countPrice(products));
        order.setTotalPrice(price);
        double totalWithDiscount = DoubleRounderUtil.roundDouble(price * (100-discountCard.getDiscountAmount())/100);
        order.setTotalDiscount(DoubleRounderUtil.roundDouble(price-totalWithDiscount));
        order.setTotalWithDiscount(totalWithDiscount);
        return order;
    }

    private double countPrice(List<ProductForCheck> products) {
        return products.stream().map(p-> p.getTotalSum()).reduce(0.0, Double::sum);
    }

    private Map<String, String> getProductFromParams(Map<String, String> params) {
        return params.entrySet().stream()
                .filter(e-> e.getKey().matches(regex))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private DiscountCard getCardFromParameters(Map<String, String> params){
        String cardIdString = params.get("discountCard");
        int cardId = Integer.parseInt(cardIdString);
        return discountCardDao.getCardByNumber(cardId);
    }

    private List<ProductForCheck> getProductsForCheck(Map<String, String> products, DiscountCard discountCard) throws BadRequestException{
        List<ProductForCheck> productOrder = new ArrayList<>();
        for(String key : products.keySet()){
            Product product = productDao.getProductById(Integer.parseInt(key));
            if(product == null){
                throw new BadRequestException();
            }
            int quantity = Integer.parseInt(products.get(key));
            if(quantity > product.getQuantityInStock()){
                throw new BadRequestException();
            }
            ProductForCheck productForCheck =  new ProductForCheck();
            productForCheck.setProduct(product);
            productForCheck.setQuantity(quantity);
            double totalSum  = DoubleRounderUtil.roundDouble(product.getPrice() * quantity);
            productForCheck.setTotalSum(totalSum);
            double discountForProduct = discountForProduct(discountCard, product, quantity);
            productForCheck.setDiscount(DoubleRounderUtil.roundDouble(totalSum -totalSum * (100-discountForProduct)/100));
            productOrder.add(productForCheck);
        }
        return productOrder;
    }

    private int discountForProduct(DiscountCard discountCard, Product product, int quantity){
        if(product.isWholesaleProduct() && quantity>=PRODUCT_QUANTITY_FOR_WHOLESALE_DISCOUNT){
            return WHOLESALE_DISCOUNT;
        }else{
            return discountCard.getDiscountAmount();
        }
    }
}
