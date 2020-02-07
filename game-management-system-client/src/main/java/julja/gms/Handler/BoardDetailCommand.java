package julja.gms.Handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import julja.gms.domain.Board;
import julja.util.Prompt;

public class BoardDetailCommand implements Command {

  ObjectInputStream in;
  ObjectOutputStream out;
  Prompt prompt;

  public BoardDetailCommand(ObjectInputStream in, ObjectOutputStream out, Prompt prompt) {
    this.in = in;
    this.out = out;
    this.prompt = prompt;
  }


  @Override
  public void execute() {
    int index = indexOfUser(prompt.inputInt("게시글 번호 ? "));
    if (index == -1) {
      System.out.println("유효한 게시물 번호가 아닙니다.");
    } else {
      Board b = this.boardList.get(index);
      System.out.println("제목 : " + b.getBbsName());
      System.out.println("내용 : " + b.getBbsText());
      System.out.println("조회수 : " + b.getBbsHits());
    }
  }

  private int indexOfUser(int num) {
    for (int i = 0; i < this.boardList.size(); i++) {
      if (this.boardList.get(i).getNo() == num) {
        return i;
      }
    }
    return -1;
  }

}
