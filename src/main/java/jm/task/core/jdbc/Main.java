package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Dmitryi", "Donskoi", ((byte) 29));
        userDaoJDBC.saveUser("Sergyi", "Radonezhskyi", ((byte) 66));
        userDaoJDBC.saveUser("Alexander", "Peresvet", ((byte) 30));
        userDaoJDBC.saveUser("Andrei", "Oslyabya", ((byte) 40));

        System.out.println(userDaoJDBC.getAllUsers().toString());
        userDaoJDBC.cleanUsersTable();
        System.out.println(userDaoJDBC.getAllUsers().toString());
        userDaoJDBC.dropUsersTable();
        System.out.println(userDaoJDBC.getAllUsers().toString());
    }
}
