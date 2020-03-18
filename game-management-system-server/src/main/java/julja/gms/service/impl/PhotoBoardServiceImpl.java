package julja.gms.service.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import julja.gms.dao.PhotoBoardDao;
import julja.gms.dao.PhotoFileDao;
import julja.gms.domain.PhotoBoard;
import julja.gms.service.PhotoBoardService;
import julja.sql.PlatformTransactionManager;
import julja.sql.TransactionTemplate;

@Component
public class PhotoBoardServiceImpl implements PhotoBoardService {

  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;
  TransactionTemplate transactionTemplate;

  public PhotoBoardServiceImpl(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao,
      PlatformTransactionManager txManager) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
    this.transactionTemplate = new TransactionTemplate(txManager);
  }

  @Override
  public void insert(PhotoBoard photoBoard) throws Exception {
    transactionTemplate.execute(() -> {
      if (photoBoardDao.insert(photoBoard) == 0) {
        throw new Exception("사진 게시글 등록에 실패했습니다.");
      }
      photoFileDao.insert(photoBoard);
      return null;
    });
  }

  @Override
  public List<PhotoBoard> findAllByNo(int no) throws Exception {
    return photoBoardDao.findAllByNo(no);
  }

  @Override
  public PhotoBoard findByNo(int no) throws Exception {
    return photoBoardDao.findByNo(no);
  }

  @Override
  public void update(PhotoBoard photoBoard) throws Exception {

    transactionTemplate.execute(() -> {
      if (photoBoardDao.update(photoBoard) == 0) {
        throw new Exception("사진 게시글 변경에 실패했습니다.");
      }

      if (photoBoard.getFiles() != null) {
        // 첨부파일을 변경한다면,
        photoFileDao.deleteAll(photoBoard.getNo());
        photoFileDao.insert(photoBoard);
      }
      return null;
    });
  }

  @Override
  public void delete(int no) throws Exception {

    transactionTemplate.execute(() -> {
      photoFileDao.deleteAll(no);

      if (photoBoardDao.delete(no) == 0) {
        throw new Exception("해당 번호의 사진 게시글이 없습니다.");
      }
      return null;
    });
  }

}
