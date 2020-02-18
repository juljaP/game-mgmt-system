package julja.gms.handler;

import java.util.List;
import julja.gms.dao.BoardDao;
import julja.gms.domain.Board;

public class BoardListCommand implements Command {

  BoardDao boardDao;

  public BoardListCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute() {

    try {

      List<Board> boards = boardDao.findAll();

      for (Board b : boards) {
        System.out.printf("[%d] %s | %s | %d \n", b.getNo(), b.getBbsName(), b.getToday(),
            b.getBbsHits());
      }

    } catch (Exception e) {
      System.out.println("통신 오류 발생!");
    }
  }
}
