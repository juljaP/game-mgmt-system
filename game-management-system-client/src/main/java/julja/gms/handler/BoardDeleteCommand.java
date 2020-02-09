package julja.gms.handler;

import julja.gms.dao.proxy.BoardDaoProxy;
import julja.util.Prompt;

public class BoardDeleteCommand implements Command {

  Prompt prompt;
  BoardDaoProxy boardDao;

  public BoardDeleteCommand(BoardDaoProxy boardDao, Prompt prompt) {
    this.boardDao = boardDao;
    this.prompt = prompt;
  }


  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("게시글 번호? ");
      boardDao.delete(no);
      System.out.println("게시글을 삭제했습니다.");
    } catch (Exception e) {
      System.out.println("삭제 실패!");
    }
  }
}
