package julja.gms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import julja.gms.dao.PhotoBoardDao;
import julja.gms.domain.Game;
import julja.gms.domain.PhotoBoard;
import julja.sql.DataSource;

public class PhotoBoardDaoImpl implements PhotoBoardDao {

  DataSource conFactory;

  public PhotoBoardDaoImpl(DataSource conFactory) {
    this.conFactory = conFactory;
  }

  @Override
  public int insert(PhotoBoard photoBoard) throws Exception {

    try (Connection con = conFactory.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("INSERT INTO gms_photo(titl, game_id) VALUES (?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS)) {
      stmt.setString(1, photoBoard.getTitle());
      stmt.setInt(2, photoBoard.getGame().getNo());

      int result = stmt.executeUpdate();

      try (ResultSet generatedKeySet = stmt.getGeneratedKeys()) {
        generatedKeySet.next();
        photoBoard.setNo(generatedKeySet.getInt(1));
      }

      return result;
    }
  }

  @Override
  public List<PhotoBoard> findAllByNo(int no) throws Exception {

    try (Connection con = conFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM gms_photo WHERE game_id=?")) {

      stmt.setInt(1, no);
      ResultSet rs = stmt.executeQuery();

      ArrayList<PhotoBoard> list = new ArrayList<>();

      while (rs.next()) {
        PhotoBoard photoBoard = new PhotoBoard();
        photoBoard.setNo(rs.getInt("photo_id"));
        photoBoard.setTitle(rs.getString("titl"));
        photoBoard.setCreadtedDate(rs.getDate("cdt"));
        photoBoard.setHits(rs.getInt("hits"));
        list.add(photoBoard);
      }
      return list;
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public PhotoBoard findByNo(int no) throws Exception {

    try (Connection con = conFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "SELECT p.photo_id, p.titl, p.cdt, p.hits, g.game_id, g.titl FROM gms_photo p JOIN gms_game g ON p.game_id = g.game_id WHERE p.photo_id=?")) {

      stmt.setInt(1, no);
      ResultSet rs = stmt.executeQuery();

      Game game = new Game();

      if (rs.next()) {
        PhotoBoard photoBoard = new PhotoBoard();
        game.setNo(rs.getInt("g.game_id"));
        game.setGameName(rs.getString("g.titl"));
        photoBoard.setNo(rs.getInt("p.photo_id"));
        photoBoard.setTitle(rs.getString("p.titl"));
        photoBoard.setCreadtedDate(rs.getDate("p.cdt"));
        photoBoard.setHits(rs.getInt("p.hits"));
        photoBoard.setGame(game);
        return photoBoard;
      } else {
        return null;
      }
    }
  }

  @Override
  public PhotoBoard findByGameNo(int no) throws Exception {

    try (Connection con = conFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "SELECT p.photo_id, p.titl, p.cdt, p.hits, g.game_id, g.titl FROM gms_photo p JOIN gms_game g ON p.game_id = g.game_id WHERE p.game_id=?")) {
      stmt.setInt(1, no);
      ResultSet rs = stmt.executeQuery();

      Game game = new Game();

      if (rs.next()) {
        PhotoBoard photoBoard = new PhotoBoard();
        game.setNo(rs.getInt("g.game_id"));
        game.setGameName(rs.getString("g.titl"));
        photoBoard.setNo(rs.getInt("p.photo_id"));
        photoBoard.setTitle(rs.getString("p.titl"));
        photoBoard.setCreadtedDate(rs.getDate("p.cdt"));
        photoBoard.setHits(rs.getInt("p.hits"));
        photoBoard.setGame(game);
        return photoBoard;
      } else {
        return null;
      }
    }
  }

  @Override
  public int update(PhotoBoard photoBoard) throws Exception {

    try (Connection con = conFactory.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("UPDATE gms_photo SET titl=? WHERE photo_id=?")) {
      stmt.setString(1, photoBoard.getTitle());
      stmt.setInt(2, photoBoard.getNo());
      int result = stmt.executeUpdate();

      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {

    try (Connection con = conFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM gms_photo WHERE photo_id=?")) {
      stmt.setInt(1, no);
      int result = stmt.executeUpdate();

      return result;
    }
  }

}
