package julja.gms.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import julja.gms.dao.PhotoBoardDao;
import julja.gms.domain.Game;
import julja.gms.domain.PhotoBoard;

public class PhotoBoardDaoImpl implements PhotoBoardDao {

  Connection con;

  public PhotoBoardDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public int insert(PhotoBoard photoBoard) throws Exception {

    try (Statement stmt = con.createStatement()) {

      con.setAutoCommit(true);

      int result = stmt.executeUpdate("INSERT INTO gms_photo(titl, game_id) VALUES ('"
          + photoBoard.getTitle() + "', '" + photoBoard.getGame().getNo() + "')");

      return result;
    } catch (Exception e) {
      return -1;
    }
  }

  @Override
  public List<PhotoBoard> findAllByNo(int no) throws Exception {

    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM gms_photo WHERE game_id=" + no)) {

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

    try (Statement stmt = con.createStatement();
        ResultSet rs =
            stmt.executeQuery("SELECT p.photo_id, p.titl, p.cdt, p.hits, g.game_id, g.titl"
                + " FROM gms_photo p JOIN gms_game g ON p.game_id = g.game_id"
                + " WHERE p.photo_id=" + no)) {

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

    try (Statement stmt = con.createStatement();
        ResultSet rs =
            stmt.executeQuery("SELECT p.photo_id, p.titl, p.cdt, p.hits, g.game_id, g.titl"
                + " FROM gms_photo p JOIN gms_game g ON p.game_id = g.game_id WHERE p.game_id="
                + no)) {

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

    try (Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("UPDATE gms_photo SET titl='" + photoBoard.getTitle()
          + "' WHERE photo_id=" + photoBoard.getNo());

      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {

    try (Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("DELETE FROM gms_photo WHERE photo_id=" + no);

      return result;
    }
  }

}
