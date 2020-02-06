package julja.gms.Handler;

import java.util.Iterator;
import java.util.List;
import julja.gms.domain.Board;

public class BoardListCommand implements Command {

  List<Board> boardList;

  public BoardListCommand(List<Board> list) {
    boardList = list;
  }

  @Override
  public void execute() {
    Iterator<Board> iterator = boardList.iterator();
    while (iterator.hasNext()) {
      Board b = iterator.next();
      System.out.printf("[%d] %s | %s | %d \n", b.getBbsNum(), b.getBbsName(), b.getBbsText(),
          b.getBbsHits());
    }
  }
}
