package julja.gms.Handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import julja.util.Prompt;

public class GameDeleteCommand implements Command {

  ObjectInputStream in;
  ObjectOutputStream out;
  Prompt prompt;

  public GameDeleteCommand(ObjectInputStream in, ObjectOutputStream out, Prompt prompt) {
    this.in = in;
    this.out = out;
    this.prompt = prompt;
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
      if (this.gameList.get(i).getNo() == num) {
        return i;
      }
    }
    return -1;
  }

}

