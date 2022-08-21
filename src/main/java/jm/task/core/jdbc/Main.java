package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;


public class Main {


    public static void main(String[] args) {

        UserDao userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();

        userDao.saveUser("Егор", "Быков", (byte) 20);
        userDao.saveUser("Василий", "Узумаки", (byte) 25);
        userDao.saveUser("God", "Usopp", (byte) 22);
        userDao.saveUser("Nico", "Robin", (byte) 30);
        System.out.println(userDao.getAllUsers());
        userDao.removeUserById(1);
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}



