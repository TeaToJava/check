package ru.clevertec.check.dao.impl;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.postgresql.jdbc3.Jdbc3PoolingDataSource;
import ru.clevertec.check.dao.DiscountCardDao;
import ru.clevertec.check.db.DataSource;
import ru.clevertec.check.model.DiscountCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DiscountCardDaoImpl implements DiscountCardDao {

    private static final String ID = "id";
    private static final String NUMBER = "number";
    private static final String AMOUNT = "amount";
    private static final String GET_DISCOUNT_CARD = "SELECT * FROM public.discount_card WHERE number=?";
    private  List<DiscountCard> cardsList = new ArrayList<>();

    public DiscountCardDaoImpl() {
    }

    @Override
    public DiscountCard getCardByNumber(int cardNumber) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(GET_DISCOUNT_CARD)) {
             pst.setObject(1, cardNumber);
             try (ResultSet rs = pst.executeQuery()) {
                DiscountCard discountCard = new DiscountCard(cardNumber);
                while (rs.next()) {
                    discountCard.setId(Long.parseLong(rs.getString(ID)));
                    discountCard.setCardNumber(Integer.parseInt(rs.getString(NUMBER)));
                    discountCard.setDiscountAmount(Integer.parseInt(rs.getString(AMOUNT)));
                }
                return discountCard;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DiscountCard(cardNumber);
    }

 }
