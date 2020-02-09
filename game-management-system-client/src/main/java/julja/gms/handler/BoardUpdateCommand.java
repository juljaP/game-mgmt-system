package julja.gms.handler;

import java.sql.Date;
import julja.gms.dao.proxy.BoardDaoProxy;
import julja.gms.domain.Board;
import julja.util.Prompt;

public class BoardUpdateCommand implements Command {

  Prompt prompt;
  BoardDaoProxy boardDao;

  public BoardUpdateCommand(BoardDaoProxy boardDao, Prompt prompt) {
    this.boardDao = boardDao;
    this.prompt = prompt;
  }


  @Override
  public void execute() {

    try {
      int no = prompt.inputInt("게시글 번호? ");

      Board oldBoard = null;
      try {
        oldBoard = boardDao.findByNo(no);
      } catch (Exception e) {
        System.out.println("해당 번호의 게시글이 없습니다.");
        return;
      }

      Board newBoard = new Board();
      newBoard.setNo(oldBoard.getNo());
      System.out.println("제목 : " + oldBoard.getBbsName());
      newBoard.setBbsName(oldBoard.getBbsName());
      newBoard.setBbsText(prompt.inputString(String.format("내용(%s)? ", oldBoard.getBbsText()),
          oldBoard.getBbsText()));
      newBoard.setBbsHits(oldBoard.getBbsHits());
      newBoard.setToday(new Date(System.currentTimeMillis()));

      if (oldBoard.equals(newBoard)) {
        System.out.println("게시글 변경을 취소했습니다.");
        return;
      }
      boardDao.update(newBoard);
      System.out.println("게시글을 변경했습니다.");

    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}
