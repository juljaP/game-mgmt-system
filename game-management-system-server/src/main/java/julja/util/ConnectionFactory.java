package julja.util;

import java.sql.Connection;
import java.sql.DriverManager;
import julja.sql.ConnectionProxy;

public class ConnectionFactory {

  String jdbcUrl;
  String userName;
  String password;
  ThreadLocal<Connection> connectionLocal = new ThreadLocal<Connection>();

  public ConnectionFactory(String jdbcUrl, String userName, String password) {
    this.jdbcUrl = jdbcUrl;
    this.userName = userName;
    this.password = password;
  }

  public Connection getConnection() throws Exception {
    Connection con = connectionLocal.get();
    if (con != null) {
      return con;
    }
    con = new ConnectionProxy(DriverManager.getConnection(jdbcUrl, userName, password));
    connectionLocal.set(con);
    return con;
  }

  public Connection removeConnection() {
    Connection con = connectionLocal.get();
    if (con != null) {
      connectionLocal.remove();
      System.out.println("Connection 삭제!");
    }
    return con;
  }

}
