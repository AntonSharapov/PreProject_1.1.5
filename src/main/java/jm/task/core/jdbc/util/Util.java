package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    private static final String dr_URL = "jdbc:mysql://localhost:3306/pr_bd";
    private static final String dr_Username = "root";
    private static final String dr_password = "lolypop7991_";
    private static SessionFactory sessionFactory;
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dr_URL, dr_Username, dr_password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties setig = new Properties();
                setig.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                setig.put(Environment.URL, "jdbc:mysql://localhost:3306/pr_bd");
                setig.put(Environment.USER, "root");
                setig.put(Environment.PASS, "lolypop7991_");
                setig.put(Environment.DIALECT, "org.hibernate.dialect.MySQL57Dialect");
                setig.put(Environment.SHOW_SQL, "true");
                setig.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                setig.put(Environment.HBM2DDL_AUTO, "create-drop");
                setig.put("hibernate.id.new_generator_mappings","false");

                configuration.setProperties(setig).addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        return sessionFactory;
    }
}
