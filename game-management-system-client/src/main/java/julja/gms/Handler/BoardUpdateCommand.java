package julja.gms.Handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import julja.gms.domain.Board;
import julja.util.Prompt;

public class BoardUpdateCommand implements Command {

  ObjectInputStream in;
  ObjectOutputStream out;
  Prompt prompt;

  public BoardUpdateCommand(ObjectInputStream in, ObjectOutputStream out, Prompt prompt) {
    this.in = in;
    this.out = out;
    this.prompt = prompt;
  }


  @Override
  public void execute() {
    int index = indexOfUser(prompt.inputInt("게시글 번호 ? "));
    if (index == -1) {
      System.out.println("유효한 게시물 번호가 아닙니다.");
      return;
    }
    Board oldBoard = this.boardList.get(index);
    Board newBoard = new Board();
    newBoard.setNo(oldBoard.getNo());
    System.out.println("제목 : " + oldBoard.getBbsName());
    newBoard.setBbsName(oldBoard.getBbsName());
    newBoard.setBbsText(prompt.inputString(String.format("내용(%s)? ", oldBoard.getBbsText()),
        oldBoard.getBbsText()));
    newBoard.setBbsHits(oldBoard.getBbsHits());
    newBoard.setToday(new Date(System.currentTimeMillis()));
    if (newBoard.equals(oldBoard)) {
      System.out.println("게시글 변경을 취소했습니다.");
    } else {
      this.boardList.set(index, newBoard);
      System.out.println("게시글을 변경했습니다.");
    }
  }

  private int indexOfUser(int no) {
    for (int i = 0; i < this.boardList.size(); i++) {
      if (this.boardList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }

}
