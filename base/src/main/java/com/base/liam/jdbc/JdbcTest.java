package com.base.liam.jdbc;

import com.base.liam.NewJsonUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by chaochun.ccc on 2017-08-16.
 */
public class JdbcTest {

  // wolong.master.00=jdbc:mysql://11.251.180.214:3304/wolong_0000?useUnicode=true&rewriteBatchedStatements=true&characterEncoding=utf-8&zeroDateTimeBehavior=round&autoReconnect=true

  public static final String LOCAL_DB_URL =
      "jdbc:mysql://127.0.0.1:3306/wolong_extend?useUnicode=true&rewriteBatchedStatements=true&characterEncoding=utf-8&zeroDateTimeBehavior=round&autoReconnect=true&user=root&password=root";
  public static final String DB_URL =
      "jdbc:mysql://11.251.207.148:3307/wolong_extend?useUnicode=true&rewriteBatchedStatements=true&characterEncoding=utf-8&zeroDateTimeBehavior=round&autoReconnect=true&user=admin&password=3tdnk2Z1Ak";

  public static final String TEST_SQL =
      "insert into white_list(userid, func_id, functionality) values (?, ?, ?)";

  public static final String DB_PWD = "root";

  public static final String DB_DRIVER = "com.mysql.jdbc.Driver";

  public static void main(String[] args) {

    int randomInt = ThreadLocalRandom.current().nextInt(20, 10000);

    Connection conn = null;
    PreparedStatement preparedStatement = null;
    try {
      Class.forName(DB_DRIVER);
      conn = DriverManager.getConnection(DB_URL);
      conn.setAutoCommit(false);

      preparedStatement = conn.prepareStatement(TEST_SQL);
      for (int i = 0; i < 10; i++) {
        preparedStatement.setLong(1, i + randomInt);
        preparedStatement.setInt(2, i + randomInt);
        preparedStatement.setString(3, "test" + i);
        preparedStatement.addBatch();
      }

      int[] result = preparedStatement.executeBatch();
      System.out.println(NewJsonUtils.toJson(result));
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
        if (conn != null) {
          conn.commit();
          conn.close();
        }
      } catch (SQLException e) {
        // ignore
      }
    }

  }

}
