package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import ru.netology.databaseentities.CreditRequestEntity;
import ru.netology.databaseentities.OrderEntity;
import ru.netology.databaseentities.PaymentEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQL {
    private static final String url = System.getProperty("db.url");
    private static final String user = System.getProperty("db.user");
    private static final String password = System.getProperty("db.password");
    private static Connection connection;


    @SneakyThrows
    public static void dropDataBase() {
        var runner = new QueryRunner();
        var order = "DELETE FROM order_entity";
        var payment = "DELETE FROM payment_entity";
        var creditRequest = "DELETE FROM credit_request_entity";

        var connection = DriverManager.getConnection(url, user, password);
        {
            runner.update(connection, order);
            runner.update(connection, payment);
            runner.update(connection, creditRequest);
        }
    }

    @SneakyThrows
    public static PaymentEntity getCardStatusForPayment() {
        var statusQuery = "SELECT * FROM payment_entity";
        var runner = new QueryRunner();
        var connection = DriverManager.getConnection(url, user, password);
        {
            return runner.query(connection, statusQuery, new BeanHandler<>(PaymentEntity.class));
            //return cardStatus.getStatus();
        }
    }

    @SneakyThrows
    public static CreditRequestEntity getCardStatusForCreditRequest() {
        var statusQuery = "SELECT * FROM credit_request_entity";
        var runner = new QueryRunner();
        var connection = DriverManager.getConnection(url, user, password);
        {
            // var cardStatus =
            return runner.query(connection, statusQuery, new BeanHandler<>(CreditRequestEntity.class));
            //return cardStatus.getStatus();
        }
    }

    @SneakyThrows
    public static OrderEntity getPaymentId() {
        var idQueryForCardPay = "SELECT * FROM order_entity";
        var runner = new QueryRunner();
        var connection = DriverManager.getConnection(url, user, password);
        {
            return runner.query(connection, idQueryForCardPay, new BeanHandler<>(OrderEntity.class));

        }
    }
}