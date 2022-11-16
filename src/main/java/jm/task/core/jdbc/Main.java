package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl dv = new UserServiceImpl();
        dv.createUsersTable();
        dv.saveUser("io", "Popo", (byte)14);
        dv.saveUser("lo", "kopo", (byte)15);
        dv.saveUser("ko", "Sopo", (byte)16);
        dv.saveUser("mo", "Vopo", (byte)17);
        dv.getAllUsers();
        dv.removeUserById(2);
        dv.getAllUsers();
        dv.cleanUsersTable();
        dv.dropUsersTable();

    }
}
