package julja.gms.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import julja.gms.dao.GameDao;
import julja.gms.domain.Game;

public class GameDaoImpl implements GameDao {

  @Override
  public int insert(Game game) throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");

    try (
        Connection con =
            DriverManager.getConnection("jdbc:mariadb://localhost:3306/gmsdb", "study", "1111");
        Statement stmt = con.createStatement()) {

      con.setAutoCommit(true);

      int result = stmt.executeUpdate(
          "INSERT INTO gms_game(titl, rdt, pdt, pf, genre, photo, illust, vo) VALUES('"
              + game.getGameName() + "', '" + game.getGameDate() + "', '" + game.getGameProduction()
              + "', '" + game.getGamePlatform() + "', '" + game.getGameGenre() + "', '"
              + game.getPhoto() + "', '" + game.getGameIllust() + "', '" + game.getGameVoice()
              + "')");

      return result;
    }
  }

  @Override
  public List<Game> findAll() throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");

    try (
        Connection con =
            DriverManager.getConnection("jdbc:mariadb://localhost:3306/gmsdb", "study", "1111");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT game_id, titl, rdt, pf, genre FROM gms_game")) {

      ArrayList<Game> list = new ArrayList<>();

      while (rs.next()) {
        Game game = new Game();
        game.setNo(rs.getInt("game_id"));
        game.setGameName(rs.getString("titl"));
        game.setGameDate(rs.getDate("rdt"));
        game.setGamePlatform(rs.getString("pf"));
        game.setGameGenre(rs.getString("genre"));
        list.add(game);
      }

      return list;
    }
  }

  @Override
  public Game findByNo(int no) throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");

    try (
        Connection con =
            DriverManager.getConnection("jdbc:mariadb://localhost:3306/gmsdb", "study", "1111");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM gms_game WHERE game_id=" + no)) {

      Game game = new Game();
      while (rs.next()) {
        game.setNo(rs.getInt("game_id"));
        game.setGameName(rs.getString("titl"));
        game.setGameProduction(rs.getString("pdt"));
        game.setGameDate(rs.getDate("rdt"));
        game.setGamePlatform(rs.getString("pf"));
        game.setGameGenre(rs.getString("genre"));
        game.setGameIllust(rs.getString("illust"));
        game.setGameVoice(rs.getString("vo"));
        game.setPhoto(rs.getString("photo"));
      }

      return game;
    }
  }

  @Override
  public int update(Game game) throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");

    try (
        Connection con =
            DriverManager.getConnection("jdbc:mariadb://localhost:3306/gmsdb", "study", "1111");
        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("UPDATE gms_game SET titl='" + game.getGameName() + "', pdt='"
          + game.getGameProduction() + "', rdt='" + game.getGameDate() + "', pf='"
          + game.getGamePlatform() + "', genre='" + game.getGameGenre() + "', illust='"
          + game.getGameIllust() + "', vo='" + game.getGameVoice() + "', photo='" + game.getPhoto()
          + "' WHERE game_id=" + game.getNo());

      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");

    try (
        Connection con =
            DriverManager.getConnection("jdbc:mariadb://localhost:3306/gmsdb", "study", "1111");
        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("DELETE FROM gms_game WHERE game_id=" + no);

      return result;
    }
  }

}
