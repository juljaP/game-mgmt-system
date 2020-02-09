package julja.gms.handler;

import julja.gms.dao.proxy.BoardDaoProxy;
import julja.gms.domain.Board;
import julja.util.Prompt;

public class BoardDetailCommand implements Command {

  Prompt prompt;
  BoardDaoProxy boardDao;

  public BoardDetailCommand(BoardDaoProxy boardDao, Prompt prompt) {
    this.boardDao = boardDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("게시글 번호? ");
      Board b = boardDao.findByNo(no);
      System.out.println("제목 : " + b.getBbsName());
      System.out.println("내용 : " + b.getBbsText());
      System.out.println("조회수 : " + b.getBbsHits());
    } catch (Exception e) {
      System.out.println("조회 실패!");
    }
  }
}
