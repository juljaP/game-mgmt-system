package julja.gms.dao;

import java.util.List;
import julja.gms.domain.PhotoBoard;

public interface PhotoBoardDao {

  int insert(PhotoBoard photoBoard) throws Exception;

  List<PhotoBoard> findAllByNo(int no) throws Exception;

  PhotoBoard findByNo(int no) throws Exception;

  int update(PhotoBoard photoBoard) throws Exception;

  int delete(int no) throws Exception;

}
