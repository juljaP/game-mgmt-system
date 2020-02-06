package julja.gms.Handler;

import java.sql.Date;
import java.util.List;
import julja.gms.domain.Board;
import julja.util.Prompt;

public class BoardAddCommand implements Command {

  Prompt prompt;
  List<Board> boardList;

  public BoardAddCommand(Prompt prompt, List<Board> list) {
    this.prompt = prompt;
    boardList = list;
  }

  @Override
  public void execute() {
    Board b = new Board();
    b.setBbsNum(boardList.size() + 1);
    b.setBbsName(prompt.inputString("제목 : "));
    b.setBbsText(prompt.inputString("내용 : "));
    b.setToday(new Date(System.currentTimeMillis()));
    b.setBbsHits(0);
    boardList.add(b);
    System.out.println("저장하였습니다.");
  }

}
