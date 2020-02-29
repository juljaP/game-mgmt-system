package julja.gms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import julja.gms.dao.PhotoFileDao;
import julja.gms.domain.PhotoFile;
import julja.sql.DataSource;

public class PhotoFileDaoImpl implements PhotoFileDao {

  DataSource conFactory;

  public PhotoFileDaoImpl(DataSource conFactory) {
    this.conFactory = conFactory;
  }

  @Override
  public int insert(PhotoFile photoFile) throws Exception {
    try (Connection con = conFactory.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("INSERT INTO gms_photo_file(photo_id, file_path) VALUES (?, ?)")) {
      stmt.setInt(1, photoFile.getBoardNo());
      stmt.setString(2, photoFile.getFilepath());
      int result = stmt.executeUpdate();

      return result;
    }
  }

  @Override
  public List<PhotoFile> findAll(int boardNo) throws Exception {
    try (Connection con = conFactory.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("SELECT * FROM gms_photo_file WHERE photo_id=?")) {

      stmt.setInt(1, boardNo);
      ResultSet rs = stmt.executeQuery();

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
    try (Connection con = conFactory.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("DELETE FROM gms_photo_file WHERE photo_id=?")) {
      stmt.setInt(1, boardNo);
      int result = stmt.executeUpdate();

      return result;
    }
  }
}
