package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection con;

    public static Connection getConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/spendsphere";
            String username = "root";
            String password = "";

            con = DriverManager.getConnection(url, username, password);

            System.out.println("Database Connected!");

        } catch (Exception e) {

            e.printStackTrace();

        }

        return con;
    }
}