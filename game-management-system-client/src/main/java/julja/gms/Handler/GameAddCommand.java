package julja.gms.Handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import julja.gms.domain.Game;
import julja.util.Prompt;

public class GameAddCommand implements Command {

  ObjectInputStream in;
  ObjectOutputStream out;
  Prompt prompt;

  public GameAddCommand(ObjectInputStream in, ObjectOutputStream out, Prompt prompt) {
    this.in = in;
    this.out = out;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    Game g = new Game();
    g.setNo(prompt.inputInt("품번 : "));
    g.setGameName(prompt.inputString("게임명 : "));
    g.setGameProduction(prompt.inputString("제작사 : "));
    g.setGameDate(prompt.inputDate("발매일 : "));
    g.setGamePlatform(prompt.inputString("플랫폼 : "));
    g.setGameGenre(prompt.inputString("장르 : "));
    g.setGameIllust(prompt.inputString("작화 : "));
    g.setGameVoice(prompt.inputString("음성 : "));
    System.out.println();

    try {
      out.writeUTF("/game/add");
      out.writeObject(g);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      System.out.println("저장하였습니다.");

    } catch (Exception e) {
      System.out.println("통신 오류 발생.");
    }

  }
}

