package julja.gms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import julja.gms.dao.BoardDao;
import julja.gms.domain.Board;
import julja.sql.DataSource;

public class BoardDaoImpl implements BoardDao {

  DataSource conFactory;

  public BoardDaoImpl(DataSource conFactory) {
    this.conFactory = conFactory;
  }

  @Override
  public int insert(Board board) throws Exception {

    try (Connection con = conFactory.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("INSERT INTO gms_board(titl, conts) VALUES (?, ?)")) {
      stmt.setString(1, board.getBbsName());
      stmt.setString(2, board.getBbsText());
      int result = stmt.executeUpdate();

      return result;
    }
  }

  @Override
  public List<Board> findAll() throws Exception {


    try (Connection con = conFactory.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("SELECT board_id, titl, cdt, hits FROM gms_board");
        ResultSet rs = stmt.executeQuery()) {

      ArrayList<Board> list = new ArrayList<>();

      while (rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("board_id"));
        board.setBbsName(rs.getString("titl"));
        board.setToday(rs.getDate("cdt"));
        board.setBbsHits(rs.getInt("hits"));
        list.add(board);
      }
      return list;
    }
  }

  @Override
  public Board findByNo(int no) throws Exception {

    try (Connection con = conFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM gms_board WHERE board_id=?")) {
      stmt.setInt(1, no);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("board_id"));
        board.setBbsName(rs.getString("titl"));
        board.setBbsText(rs.getString("conts"));
        board.setToday(rs.getDate("cdt"));
        board.setBbsHits(rs.getInt("hits"));
        return board;
      } else {
        return null;
      }
    }
  }

  @Override
  public int update(Board board) throws Exception {

    try (Connection con = conFactory.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("UPDATE gms_board SET titl=?, conts=? WHERE board_id=?")) {

      stmt.setString(1, board.getBbsName());
      stmt.setString(2, board.getBbsText());
      stmt.setInt(3, board.getNo());
      int result = stmt.executeUpdate();

      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {

    try (Connection con = conFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM gms_board WHERE board_id=?")) {
      stmt.setInt(1, no);

      int result = stmt.executeUpdate();

      return result;
    }
  }

}
