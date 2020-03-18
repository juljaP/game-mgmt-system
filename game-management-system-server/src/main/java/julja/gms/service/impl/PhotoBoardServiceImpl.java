package julja.gms.service.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import julja.gms.dao.PhotoBoardDao;
import julja.gms.dao.PhotoFileDao;
import julja.gms.domain.PhotoBoard;
import julja.gms.service.PhotoBoardService;

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
    if (photoBoardDao.insert(photoBoard) == 0) {
      throw new Exception("사진 게시글 등록에 실패했습니다.");
    }
    photoFileDao.insert(photoBoard);
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
    if (photoBoardDao.update(photoBoard) == 0) {
      throw new Exception("사진 게시글 변경에 실패했습니다.");
    }

    if (photoBoard.getFiles() != null) {
      photoFileDao.deleteAll(photoBoard.getNo());
      photoFileDao.insert(photoBoard);
    }
  }

  @Override
  public void delete(int no) throws Exception {
    photoFileDao.deleteAll(no);

    if (photoBoardDao.delete(no) == 0) {
      throw new Exception("해당 번호의 사진 게시글이 없습니다.");
    }
  }

}
