package julja.gms.handler;

import java.sql.Date;
import julja.gms.dao.proxy.BoardDaoProxy;
import julja.gms.domain.Board;
import julja.util.Prompt;

public class BoardAddCommand implements Command {

  Prompt prompt;
  BoardDaoProxy boardDao;

  public BoardAddCommand(BoardDaoProxy boardDao, Prompt prompt) {
    this.boardDao = boardDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    Board b = new Board();
    b.setNo(prompt.inputInt("번호? "));
    b.setBbsName(prompt.inputString("제목 : "));
    b.setBbsText(prompt.inputString("내용 : "));
    b.setToday(new Date(System.currentTimeMillis()));
    b.setBbsHits(0);
    System.out.println();

    try {
      boardDao.insert(b);
      System.out.println("저장하였습니다");
    } catch (Exception e) {
      System.out.println("데이터 저장 실패!");
    }
  }
}
