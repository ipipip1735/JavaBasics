import java.sql.*;
import java.util.Map;
import java.util.Random;

import static java.lang.Class.forName;

/**
 * Created by Administrator on 2019/8/21 17:44.
 */
public class SQLTrail {

    public static void main(String[] args) {
        SQLTrail sqlTrail = new SQLTrail();

        Connection conn = sqlTrail.getConnection();
//        sqlTrail.query(conn);//查询
//        sqlTrail.queryupdate(conn);//查询时更新
//        sqlTrail.queryInsert(conn);//查询时插入

//        sqlTrail.batchDDL(conn);//批量更新


        sqlTrail.preparedDDL(conn);//使用预处理声明对象
//        sqlTrail.batchPrepareDDL(conn);//批量预处理操作

    }

    private void preparedDDL(Connection conn) {


        PreparedStatement pstmt = null;
        try {
            conn.setAutoCommit(false);
            String sql = "UPDATE mine.mine_person SET person_name = ?, person_age = person_age+? WHERE person_id = ?;";
            System.out.println(sql);
            pstmt = conn.prepareStatement(sql);

            Random random = new Random();
            pstmt.setString(1, "mary" + random.nextInt(120));
            pstmt.setInt(2, random.nextInt(10));
            pstmt.setInt(3, 5);
            int r = pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
//            JDBCTutorialUtilities.printSQLException(e);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void queryInsert(Connection conn) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet uprs = stmt.executeQuery("SELECT * FROM mine.mine_person");


            Random random = new Random();
            String name = "bob" + random.nextInt(120);
            int age = random.nextInt(120);

            uprs.moveToInsertRow();
            System.out.println(uprs.getString("person_name"));


            uprs.updateString("person_name", "tom" + random.nextInt(120));
            uprs.updateInt("person_age", random.nextInt(120));
            uprs.updateBoolean("person_sex", true);
            uprs.insertRow();
            System.out.println(uprs.getString("person_name"));

            uprs.updateString("person_name", "tom" + random.nextInt(120));
            uprs.updateInt("person_age", random.nextInt(120));
            uprs.updateBoolean("person_sex", true);
            uprs.insertRow();

            uprs.beforeFirst();
            uprs.next();
            System.out.println(uprs.getString("person_name"));


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

    private void batchPrepareDDL(Connection conn) {
        PreparedStatement pstmt = null;
        try {
            conn.setAutoCommit(false);

            String sql = "INSERT INTO mine.mine_person(person_name, person_age, person_sex) VALUES(?, ?, ?);";
            pstmt = conn.prepareStatement(sql);

            Random random = new Random();
            String name = "bob" + random.nextInt(120);
            int age = random.nextInt(120);

            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setBoolean(3, true);
            pstmt.addBatch();

            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setBoolean(3, true);
            pstmt.addBatch();


            int[] updateCounts = pstmt.executeBatch();
            conn.commit();

            for (int i = 0; i < updateCounts.length; i++) {
                System.out.print(updateCounts[i] + ", ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void batchDDL(Connection conn) {

        Statement stmt = null;
        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            Random random = new Random();
            String name = "bob" + random.nextInt(120);
            int age = random.nextInt(120);

            String sql = "INSERT INTO mine.mine_person(person_name, person_age, person_sex) VALUES('" + name + "', " + age + ", true);";
            System.out.println(sql);
            stmt.addBatch(sql);

            sql = "UPDATE mine.mine_person SET person_age= person_age +1 WHERE person_id > 10;";
            System.out.println(sql);
            stmt.addBatch(sql);


            int[] updateCounts = stmt.executeBatch();
            conn.commit();

            for (int i = 0; i < updateCounts.length; i++) {
                System.out.print(updateCounts[i] + ", ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void queryupdate(Connection conn) {

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
