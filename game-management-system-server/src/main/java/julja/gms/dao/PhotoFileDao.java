package julja.gms.dao;

import java.util.List;
import julja.gms.domain.PhotoFile;

public interface PhotoFileDao {

  int insert(PhotoFile photoFile) throws Exception;

  List<PhotoFile> findAll(int boardNo) throws Exception;

  int deleteAll(int boardNo) throws Exception;

}
