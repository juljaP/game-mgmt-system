package julja.gms.Handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import julja.gms.domain.Game;
import julja.util.Prompt;

public class GameDetailCommand implements Command {

  ObjectInputStream in;
  ObjectOutputStream out;
  Prompt prompt;

  public GameDetailCommand(ObjectInputStream in, ObjectOutputStream out, Prompt prompt) {
    this.in = in;
    this.out = out;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    int index = indexOfLesson(prompt.inputInt("게임 품번? : "));
    Game g = this.gameList.get(index);
    if (index == -1) {
      System.out.println("해당하는 게임이 존재하지 않습니다.");
      return;
    }
    System.out.printf("게임명 : %s\n", g.getGameName());
    System.out.printf("제작사 : %s\n", g.getGameProduction());
    System.out.printf("발매일 : %s\n", g.getGameDate());
    System.out.printf("플랫폼 : %s\n", g.getGamePlatform());
    System.out.printf("장르 : %s\n", g.getGameGenre());
    System.out.printf("작화 : %s\n", g.getGameIllust());
    System.out.printf("음성 : %s\n", g.getGameVoice());
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

