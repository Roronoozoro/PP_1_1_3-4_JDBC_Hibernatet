package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }
    public void createUsersTable() {
        try  {
            Util.getConnection().setAutoCommit(false);
            Statement statement = Util.getConnection().createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(60), last_name VARCHAR(60), age INT)");
            Util.getConnection().commit();
            statement.close();
            Util.getConnection().close();
        } catch (SQLException e) {
            try {
                Util.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.getStackTrace();
        }
        System.out.println("Таблица создана.");
    }

    public void dropUsersTable() {
        try  {
            Util.getConnection().setAutoCommit(false);
            Statement statement = Util.getConnection().createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            Util.getConnection().commit();
            statement.close();
            Util.getConnection().close();
        } catch (SQLException e) {
            try {
                Util.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.getStackTrace();
        }
        System.out.println("Таблица удалена.");
    }

    public void saveUser(String name, String lastName, byte age) {
        try  {
            Util.getConnection().setAutoCommit(false);
            PreparedStatement pstm = Util.getConnection().prepareStatement("INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)");
            pstm.setString(1, name);
            pstm.setString(2, lastName);
            pstm.setByte(3, age);
            pstm.executeUpdate();
            Util.getConnection().commit();
            pstm.close();
            Util.getConnection().close();
        } catch (SQLException e) {
            try {
                Util.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.getStackTrace();
        }
        System.out.println("Пользователь c именем " + name + " " + lastName + " был добавлен.");
    }

    public void removeUserById(long id) {
        try  {
            Util.getConnection().setAutoCommit(false);
            PreparedStatement pstm = Util.getConnection().prepareStatement("DELETE FROM users WHERE id = ?");
            pstm.setLong(1, id);
            pstm.executeUpdate();
            Util.getConnection().commit();
            pstm.close();
            Util.getConnection().close();
        } catch (SQLException e) {
            try {
                Util.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.getStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try  {
            Util.getConnection().setAutoCommit(false);
            ResultSet resultSet = Util.getConnection().createStatement().executeQuery("SELECT * FROM users");
            while(resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("last_name"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
                Util.getConnection().commit();
                resultSet.close();
                Util.getConnection().close();
            }
        } catch (SQLException e) {
            try {
                Util.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.getStackTrace();
        }
        System.out.println("Список пользователей готов:");
        return users;
    }



    public void cleanUsersTable() {
        try  {
            Statement statement = Util.getConnection().createStatement();
            Util.getConnection().setAutoCommit(false);
            statement.executeUpdate("TRUNCATE TABLE users");
            Util.getConnection().commit();
            statement.close();
            Util.getConnection().close();
        } catch (SQLException e) {
            try {
                Util.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.getStackTrace();
        }
    }
}