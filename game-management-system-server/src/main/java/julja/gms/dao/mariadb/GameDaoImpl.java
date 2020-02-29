package julja.gms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import julja.gms.dao.GameDao;
import julja.gms.domain.Game;
import julja.sql.DataSource;

public class GameDaoImpl implements GameDao {

  DataSource conFactory;

  public GameDaoImpl(DataSource conFactory) {
    this.conFactory = conFactory;
  }

  @Override
  public int insert(Game game) throws Exception {

    try (Connection con = conFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "INSERT INTO gms_game(titl, rdt, pdt, pf, genre, photo, illust, vo) VALUES(?, ?, ?, ?, ?, ?, ?, ?)")) {

      stmt.setString(1, game.getGameName());
      stmt.setDate(2, game.getGameDate());
      stmt.setString(3, game.getGameProduction());
      stmt.setString(4, game.getGamePlatform());
      stmt.setString(5, game.getGameGenre());
      stmt.setString(6, game.getPhoto());
      stmt.setString(7, game.getGameIllust());
      stmt.setString(8, game.getGameVoice());
      int result = stmt.executeUpdate();

      return result;
    }
  }

  @Override
  public List<Game> findAll() throws Exception {

    try (Connection con = conFactory.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("SELECT game_id, titl, rdt, pf, genre FROM gms_game")) {
      ResultSet rs = stmt.executeQuery();
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

    try (Connection con = conFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM gms_game WHERE game_id=?")) {

      stmt.setInt(1, no);
      ResultSet rs = stmt.executeQuery();

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

    try (Connection con = conFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "UPDATE gms_game SET titl=?, pdt=?, rdt=?, pf=?, genre=?, illust=?, vo=?, photo=? WHRER game_id=?")) {
      stmt.setString(1, game.getGameName());
      stmt.setString(2, game.getGameProduction());
      stmt.setDate(3, game.getGameDate());
      stmt.setString(4, game.getGamePlatform());
      stmt.setString(5, game.getGameGenre());
      stmt.setString(6, game.getGameIllust());
      stmt.setString(7, game.getGameVoice());
      stmt.setString(8, game.getPhoto());
      stmt.setInt(9, game.getNo());

      int result = stmt.executeUpdate();

      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (Connection con = conFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM gms_game WHERE game_id=?")) {

      stmt.setInt(1, no);
      int result = stmt.executeUpdate();

      return result;
    }
  }

}
