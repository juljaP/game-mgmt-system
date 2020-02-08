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
    try {
      int no = prompt.inputInt("게임 품번? ");
      out.writeUTF("/game/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();

      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }
      Game g = (Game) in.readObject();
      System.out.printf("게임명 : %s\n", g.getGameName());
      System.out.printf("제작사 : %s\n", g.getGameProduction());
      System.out.printf("발매일 : %s\n", g.getGameDate());
      System.out.printf("플랫폼 : %s\n", g.getGamePlatform());
      System.out.printf("장르 : %s\n", g.getGameGenre());
      System.out.printf("작화 : %s\n", g.getGameIllust());
      System.out.printf("음성 : %s\n", g.getGameVoice());
    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생");
    }
  }
}
