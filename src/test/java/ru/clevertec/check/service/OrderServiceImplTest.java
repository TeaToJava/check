package ru.clevertec.check.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.check.dao.DiscountCardDao;
import ru.clevertec.check.dao.ProductDao;
import ru.clevertec.check.exceptions.BadRequestException;
import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.model.Order;
import ru.clevertec.check.model.Product;
import ru.clevertec.check.model.ProductForCheck;
import ru.clevertec.check.service.impl.OrderServiceImpl;
import ru.clevertec.check.utils.DoubleRounderUtil;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
    @Mock
    DiscountCardDao discountCardDao;
    @Mock
    ProductDao productDao;
    @InjectMocks
    OrderService orderService = new OrderServiceImpl(discountCardDao, productDao);

    @Test
    void createOrderTest(){
        Map<String, String> params = Map.of("discountCard", "1111", "2", "3", "balanceDebitCard", "100", "saveToFile", "./result.csv");
        DiscountCard discountCard = new DiscountCard(1, 1111, 3);
        when(discountCardDao.getCardByNumber(Integer.parseInt("1111"))).thenReturn(discountCard);

        Product product = new Product();
        product.setId(2);
        product.setPrice(2.3);
        product.setWholesaleProduct(true);
        product.setDescription("Coffee");
        product.setQuantityInStock(4);
        when(productDao.getProductById(Integer.parseInt("2"))).thenReturn(product);

        Order order = orderService.createOrder(params);
        Assertions.assertNotNull(order);
        Assertions.assertEquals(order.getDiscountCard(),discountCard);

        int productAmountFromParams = 3;
        double totalPrice = product.getPrice()*productAmountFromParams;
        Assertions.assertEquals(order.getTotalPrice(), DoubleRounderUtil.roundDouble(totalPrice));
        double totalWithDiscount = DoubleRounderUtil.roundDouble(totalPrice * (100-discountCard.getDiscountAmount())/100);;
        Assertions.assertEquals(order.getTotalWithDiscount(),totalWithDiscount);
        Assertions.assertEquals(order.getDiscountCard(),discountCard);
    }

    @Test
    void createOrderTestInvalidParams(){
        Map<String, String> params = Map.of("discountCard", "1111", "2", "3", "balanceDebitCard", "1000", "saveToFile", "./result.csv");
        DiscountCard discountCard = new DiscountCard(1, 1111, 3);
        when(discountCardDao.getCardByNumber(Integer.parseInt("1111"))).thenReturn(discountCard);

        Product product = new Product();
        product.setId(2);
        product.setPrice(2.3);
        product.setWholesaleProduct(true);
        product.setDescription("Coffee");
        product.setQuantityInStock(0);
        when(productDao.getProductById(Integer.parseInt("2"))).thenReturn(product);

        Assertions.assertThrows(BadRequestException.class, ()->orderService.createOrder(params));
    }

    @Test
    void createOrderTestWholesaleProduct(){
        Map<String, String> params = Map.of("discountCard", "1111", "2", "5", "balanceDebitCard", "1000", "saveToFile", "./result.csv");
        DiscountCard discountCard = new DiscountCard(1, 1111, 3);
        when(discountCardDao.getCardByNumber(Integer.parseInt("1111"))).thenReturn(discountCard);

        Product product = new Product(2, "Coffee",2.3,10, true);
        when(productDao.getProductById(Integer.parseInt("2"))).thenReturn(product);

        Order order = orderService.createOrder(params);
        List<ProductForCheck> products = order.getProducts();
        ProductForCheck productForCheck = products.stream()
                .filter(p-> p.getProduct().getId()==product.getId()).findAny().get();

        Assertions.assertEquals(product, productForCheck.getProduct());
        Assertions.assertEquals(order.getDiscountCard().getCardNumber(),discountCard.getCardNumber());
        Assertions.assertEquals(order.getDiscountCard().getId(),discountCard.getId());
        Assertions.assertEquals(order.getDiscountCard().getDiscountAmount(),discountCard.getDiscountAmount());

        int productsQuantity = 5;
        double discount = DoubleRounderUtil.roundDouble(product.getPrice()*productsQuantity - product.getPrice()*productsQuantity * 0.9);
        Assertions.assertEquals(discount, productForCheck.getDiscount());
        Assertions.assertEquals(productsQuantity, productForCheck.getQuantity());
    }
}
