package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/mydbtest");
        properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.connection.username", "root");
        properties.setProperty("hibernate.connection.password", "Root12345678");
        properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");


        sessionFactory = new Configuration().addAnnotatedClass(User.class).addProperties(properties).buildSessionFactory();
        return sessionFactory;
    }

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "rootroot");
        } catch (SQLException e) {
            System.out.println("Нет соединения !");
        }
        return connection;
    }
}



