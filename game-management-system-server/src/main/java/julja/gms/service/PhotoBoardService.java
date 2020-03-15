package julja.gms.service;

import java.util.List;
import julja.gms.domain.PhotoBoard;

public interface PhotoBoardService {

  void insert(PhotoBoard photoBoard) throws Exception;

  List<PhotoBoard> findAllByNo(int no) throws Exception;

  PhotoBoard findByNo(int no) throws Exception;

  void update(PhotoBoard photoBoard) throws Exception;

  void delete(int no) throws Exception;

}
