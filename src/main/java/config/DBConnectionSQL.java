package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionSQL {
    private final String serverName = "LAPTOP-0IQD21P7";
    private final String dbName = "Test";
    private final String portNumber = "1433";
    private final String instance = ""; // MSSQLSERVER LEAVE THIS ONE EMPTY IF YOUR SQL IS A SINGLE INSTANCE
    private final String userID = "sa";
    private final String password = "123";

    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName=" + dbName + ";encrypt=true;trustServerCertificate=true";
        if (instance == null || instance.trim().isEmpty())
            url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName + ";encrypt=true;trustServerCertificate=true";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }

    // Test chương trình. Kích phải chuột chọn run as->java application
    public static void main(String[] args) {
        try {
            System.out.println(new DBConnectionSQL().getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}