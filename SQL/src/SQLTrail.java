import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static java.lang.Class.forName;

/**
 * Created by Administrator on 2019/8/21 17:44.
 */
public class SQLTrail {

    static String dbms = "mysql";
    static String DRIVERCLASSNAME = "com.mysql.cj.jdbc.Driver";
    static String URL = "jdbc:mysql://localhost:3306";
    static String DATABASE = "mine";
    static String USERNAME = "root";
    static String PASSWORD = "123123";


    public static void main(String[] args) {
        SQLTrail sqlTrail = new SQLTrail();

        try {
            Connection conn = sqlTrail.getConnection();
            System.out.println(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Connection getConnection() throws SQLException {

        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", USERNAME);
        connectionProps.put("password", PASSWORD);
        conn = DriverManager.getConnection(URL, connectionProps);
        System.out.println("Connected to database");
        return conn;
    }
}
