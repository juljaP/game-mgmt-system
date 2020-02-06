package julja.gms.Handler;

import java.util.List;
import julja.gms.domain.Game;
import julja.util.Prompt;

public class GameDeleteCommand implements Command {

  Prompt prompt;
  List<Game> gameList;

  public GameDeleteCommand(Prompt prompt, List<Game> list) {
    this.prompt = prompt;
    gameList = list;
  }

  @Override
  public void execute() {
    int index = indexOfLesson(prompt.inputInt("게임 품번? : "));
    if (index == -1) {
      System.out.println("해당하는 게임이 존재하지 않습니다.");
      return;
    }
    gameList.remove(index);
    System.out.println("게임 정보를 삭제했습니다.");
  }

  private int indexOfLesson(int num) {
    for (int i = 0; i < this.gameList.size(); i++) {
      if (this.gameList.get(i).getGameNum() == num) {
        return i;
      }
    }
    return -1;
  }

}

