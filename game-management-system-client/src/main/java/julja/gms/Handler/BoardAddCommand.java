package julja.gms.Handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import julja.gms.domain.Board;
import julja.util.Prompt;

public class BoardAddCommand implements Command {

  ObjectInputStream in;
  ObjectOutputStream out;
  Prompt prompt;

  public BoardAddCommand(ObjectInputStream in, ObjectOutputStream out, Prompt prompt) {
    this.in = in;
    this.out = out;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    Board b = new Board();
    b.setNo(boardList.size() + 1);
    b.setBbsName(prompt.inputString("제목 : "));
    b.setBbsText(prompt.inputString("내용 : "));
    b.setToday(new Date(System.currentTimeMillis()));
    b.setBbsHits(0);
    boardList.add(b);
    System.out.println("저장하였습니다.");
  }

}
