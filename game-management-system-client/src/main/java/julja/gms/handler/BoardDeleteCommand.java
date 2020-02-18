package julja.gms.handler;

import julja.gms.dao.BoardDao;
import julja.util.Prompt;

public class BoardDeleteCommand implements Command {

  Prompt prompt;
  BoardDao boardDao;

  public BoardDeleteCommand(BoardDao boardDao, Prompt prompt) {
    this.boardDao = boardDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("게시글 번호? ");
      if (boardDao.delete(no) > 0) {
        System.out.println("게시글을 삭제했습니다.");
      } else {
        System.out.println("해당 번호의 게시글이 존재하지 않습니다. ");
      }
    } catch (Exception e) {
      System.out.println("삭제 실패!");
    }
  }
}
