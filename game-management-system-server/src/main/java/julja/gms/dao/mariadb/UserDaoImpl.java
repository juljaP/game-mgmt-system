package julja.gms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import julja.gms.dao.UserDao;
import julja.gms.domain.User;
import julja.sql.DataSource;

public class UserDaoImpl implements UserDao {

  DataSource conFactory;

  public UserDaoImpl(DataSource conFactory) {
    this.conFactory = conFactory;
  }

  @Override
  public int insert(User user) throws Exception {

    try (Connection con = conFactory.getConnection();
        PreparedStatement stmt = con
            .prepareStatement("INSERT INTO gms_user(email, pw, name) VALUES(?, password(?), ?)")) {
      stmt.setString(1, user.getUserEmail());
      stmt.setString(2, user.getUserPW());
      stmt.setString(3, user.getUserName());
      int result = stmt.executeUpdate();

      return result;
    }
  }

  @Override
  public List<User> findAll() throws Exception {

    try (Connection con = conFactory.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("SELECT user_id, email, name, rdt FROM gms_user")) {
      ResultSet rs = stmt.executeQuery();

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

    try (Connection con = conFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM gms_user WHERE user_id=?")) {
      stmt.setInt(1, no);
      ResultSet rs = stmt.executeQuery();

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
    try (Connection con = conFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "SELECT user_id, email, name, rdt FROM gms_user WHERE user_id LIKE ? OR email LIKE ? OR name LIKE ?")) {
      stmt.setString(1, "%" + keyword + "%");
      stmt.setString(2, "%" + keyword + "%");
      stmt.setString(3, "%" + keyword + "%");
      ResultSet rs = stmt.executeQuery();

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

    try (Connection con = conFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "UPDATE gms_user SET email=?, pw=password(?), name=? WHERE user_id=?")) {
      stmt.setString(1, user.getUserEmail());
      stmt.setString(2, user.getUserPW());
      stmt.setString(3, user.getUserName());
      stmt.setInt(4, user.getNo());

      int result = stmt.executeUpdate();

      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {

    try (Connection con = conFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM gms_user WHERE user_id=?")) {
      stmt.setInt(1, no);
      int result = stmt.executeUpdate();

      return result;
    }
  }

  @Override
  public User findByEmailAndPassword(String email, String password) throws Exception {

    try (Connection con = conFactory.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("SELECT * FROM gms_user WHERE email=? AND pw=password(?)")) {
      stmt.setString(1, email);
      stmt.setString(2, password);
      ResultSet rs = stmt.executeQuery();

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

}
