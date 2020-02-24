package julja.gms.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import julja.gms.dao.UserDao;
import julja.gms.domain.User;

public class UserDaoImpl implements UserDao {

  Connection con;

  public UserDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public int insert(User user) throws Exception {

    try (Statement stmt = con.createStatement()) {

      con.setAutoCommit(true);

      int result = stmt.executeUpdate("INSERT INTO gms_user(email, pw, name) VALUES('"
          + user.getUserEmail() + "', '" + user.getUserPW() + "', '" + user.getUserName() + "')");

      return result;
    }
  }

  @Override
  public List<User> findAll() throws Exception {

    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT user_id, email, name, rdt FROM gms_user")) {

      ArrayList<User> list = new ArrayList<>();

      while (rs.next()) {
        User user = new User();
        user.setNo(rs.getInt("user_id"));
        user.setUserEmail(rs.getString("email"));
        user.setUserName(rs.getString("name"));
        user.setUserResisteredDate(rs.getDate("rdt"));
        list.add(user);
      }

      return list;
    }
  }

  @Override
  public User findByNo(int no) throws Exception {

    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM gms_user WHERE user_id=" + no)) {

      User user = new User();
      while (rs.next()) {
        user.setNo(rs.getInt("user_id"));
        user.setUserEmail(rs.getString("email"));
        user.setUserPW(rs.getString("pw"));
        user.setUserResisteredDate(rs.getDate("rdt"));
        user.setUserName(rs.getString("name"));
      }

      return user;
    }
  }

  @Override
  public List<User> findByKeyword(String keyword) throws Exception {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "SELECT user_id, email, name, rdt FROM gms_user WHERE user_id LIKE '%" + keyword
                + "%' OR email LIKE '%" + keyword + "%' OR name LIKE '%" + keyword + "%'")) {

      ArrayList<User> list = new ArrayList<>();

      while (rs.next()) {
        User user = new User();
        user.setNo(rs.getInt("user_id"));
        user.setUserEmail(rs.getString("email"));
        user.setUserName(rs.getString("name"));
        user.setUserResisteredDate(rs.getDate("rdt"));
        list.add(user);
      }

      return list;
    }
  }

  @Override
  public int update(User user) throws Exception {

    try (Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate(
          "UPDATE gms_user SET email='" + user.getUserEmail() + "', pw='" + user.getUserPW()
              + "', name='" + user.getUserName() + "' WHERE user_id =" + user.getNo());

      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {

    try (Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("DELETE FROM gms_user WHERE user_id=" + no);

      return result;
    }
  }

}
