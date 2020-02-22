package julja.gms.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import julja.gms.dao.BoardDao;
import julja.gms.domain.Board;

public class BoardDaoImpl implements BoardDao {

  Connection con;

  public BoardDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public int insert(Board board) throws Exception {

    try (Statement stmt = con.createStatement()) {

      con.setAutoCommit(true);

      int result = stmt.executeUpdate("INSERT INTO gms_board(titl, conts) VALUES ('"
          + board.getBbsName() + "', '" + board.getBbsText() + "')");

      return result;
    }
  }

  @Override
  public List<Board> findAll() throws Exception {


    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT board_id, titl, cdt, hits FROM gms_board")) {

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

    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM gms_board WHERE board_id=" + no)) {

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

    try (Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("UPDATE gms_board SET titl='" + board.getBbsName()
          + "', conts='" + board.getBbsText() + "' WHERE board_id=" + board.getNo());

      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {

    try (Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("DELETE FROM gms_board WHERE board_id=" + no);

      return result;
    }
  }

}
