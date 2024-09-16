package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionMySQL {
    private static String URL = "jdbc:mysql://localhost:3306/ltwebst2";
    private static String USERNAME =  "root";
    private static String PASSWORD = "puppyduylun365";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String con;

    public static Connection getDatabaseConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }



    //Test chương trình. Kích phải chuột chọn run as->java application


    public static void main(String[] args) {
        try {
            new DBConnectionMySQL();
            System.out.println(DBConnectionMySQL.getDatabaseConnection());
         }catch(Exception e) {
            e.printStackTrace();
        }


    }
}
