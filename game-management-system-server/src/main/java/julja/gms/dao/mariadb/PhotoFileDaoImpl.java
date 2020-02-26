package julja.gms.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import julja.gms.dao.PhotoFileDao;
import julja.gms.domain.PhotoFile;
import julja.util.ConnectionFactory;

public class PhotoFileDaoImpl implements PhotoFileDao {

  ConnectionFactory conFactory;

  public PhotoFileDaoImpl(ConnectionFactory conFactory) {
    this.conFactory = conFactory;
  }

  @Override
  public int insert(PhotoFile photoFile) throws Exception {
    try (Connection con = conFactory.getConnection(); Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("INSERT INTO gms_photo_file(photo_id, file_path) VALUES ("
          + photoFile.getBoardNo() + ", '" + photoFile.getFilepath() + "')");

      return result;
    }
  }

  @Override
  public List<PhotoFile> findAll(int boardNo) throws Exception {
    try (Connection con = conFactory.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs =
            stmt.executeQuery("SELECT * FROM gms_photo_file WHERE photo_id=" + boardNo)) {

      ArrayList<PhotoFile> list = new ArrayList<>();

      while (rs.next()) {
        PhotoFile photoFile = new PhotoFile(rs.getInt("photo_file_id"), rs.getString("file_path"),
            rs.getInt("photo_id"));
        list.add(photoFile);
      }
      return list;
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public int deleteAll(int boardNo) throws Exception {
    try (Connection con = conFactory.getConnection(); Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("DELETE FROM gms_photo_file WHERE photo_id=" + boardNo);

      return result;
    }
  }
}
