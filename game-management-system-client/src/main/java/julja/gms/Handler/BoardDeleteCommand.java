package julja.gms.Handler;

import java.util.List;
import julja.gms.domain.Board;
import julja.util.Prompt;

public class BoardDeleteCommand implements Command {

  Prompt prompt;
  List<Board> boardList;

  public BoardDeleteCommand(Prompt prompt, List<Board> list) {
    this.prompt = prompt;
    boardList = list;
  }

  @Override
  public void execute() {
    int index = indexOfUser(prompt.inputInt("게시글 번호 ? "));
    if (index == -1) {
      System.out.println("유효한 게시물 번호가 아닙니다.");
      return;
    }
    this.boardList.remove(index);
    System.out.println("게시글을 삭제했습니다.");
  }

  private int indexOfUser(int num) {
    for (int i = 0; i < this.boardList.size(); i++) {
      if (this.boardList.get(i).getBbsNum() == num) {
        return i;
      }
    }
    return -1;
  }

}
