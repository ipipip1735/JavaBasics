import java.sql.*;

import static java.lang.Class.forName;

/**
 * Created by Administrator on 2019/8/21 17:44.
 */
public class SQLTrail {

    public static void main(String[] args) {
        SQLTrail sqlTrail = new SQLTrail();

        Connection conn = sqlTrail.getConnection();
//        sqlTrail.query(conn);
//        sqlTrail.update(conn);
        sqlTrail.batchUpdate(conn);
    }

    private void batchUpdate(Connection conn) {

        Statement stmt = null;
        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            stmt.addBatch("INSERT INTO mine.mine_person（person_name, person_age, person_sex）VALUES ('bob', 32, true);");


            int [] updateCounts = stmt.executeBatch();
            conn.commit();

            for (int i = 0; i < updateCounts.length; i++) {
                System.out.println(updateCounts[i] + ", ");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void update(Connection conn) {

        String dbName = "mine";
        String table = "mine_person";
        String query = "SELECT * FROM " + dbName + "." + table;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet uprs = stmt.executeQuery(query);

            while (uprs.next()) {
                float f = uprs.getFloat("person_age");
                uprs.updateFloat("person_age", f + 10);
                uprs.updateRow();
            }

        } catch (SQLException e) {
//            JDBCTutorialUtilities.printSQLException(e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void query(Connection conn) {

        String dbName = "mine";
        String table = "mine_person";
        String query = "SELECT * FROM " + dbName + "." + table;
        System.out.println(query);
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("personID \t" +
                    "person_age \t" +
                    "person_name \t" +
                    "person_sex \t");
            while (rs.next()) {
                int personID = rs.getInt("person_id");
                int personAge = rs.getInt("person_age");
                String personName = rs.getString("person_name");
                boolean personSex = rs.getBoolean("person_sex");
                System.out.println(personID + "\t" +
                        personAge + "\t" +
                        personName + "\t" +
                        personSex + "\t");
            }
        } catch (SQLException e) {
//            JDBCTutorialUtilities.printSQLException(e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public Connection getConnection() {

        Connection conn = null;

        try {
            //方法一
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mine?user=root&password=123123&serverTimezone=UTC");


            //方法二
//            Properties connectionProps = new Properties();
//            connectionProps.put("user", "root");
//            connectionProps.put("password", "123123");
//            connectionProps.put("&serverTimezone", "UTC");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mine", connectionProps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
