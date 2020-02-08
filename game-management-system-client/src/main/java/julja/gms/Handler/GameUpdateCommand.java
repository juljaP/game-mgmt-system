package julja.gms.Handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import julja.gms.domain.Game;
import julja.util.Prompt;

public class GameUpdateCommand implements Command {

  ObjectInputStream in;
  ObjectOutputStream out;
  Prompt prompt;

  public GameUpdateCommand(ObjectInputStream in, ObjectOutputStream out, Prompt prompt) {
    this.in = in;
    this.out = out;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {
      int no = prompt.inputInt("게임 품번? : ");

      out.writeUTF("/game/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();

      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      Game oldGame = (Game) in.readObject();
      Game newGame = new Game();
      newGame.setNo(oldGame.getNo());
      newGame.setGameName(prompt.inputString(String.format("게임명(%s) ? ", oldGame.getGameName()),
          oldGame.getGameName()));

      newGame.setGameProduction(prompt.inputString(
          String.format("제작사(%s) ? ", oldGame.getGameProduction()), oldGame.getGameProduction()));
      newGame.setGameDate(prompt.inputDate(String.format("발매일(%s) ? ", oldGame.getGameDate()),
          oldGame.getGameDate()));
      newGame.setGamePlatform(prompt.inputString(
          String.format("플랫폼(%s) ? ", oldGame.getGamePlatform()), oldGame.getGamePlatform()));
      newGame.setGameGenre(prompt.inputString(String.format("장르(%s) ? ", oldGame.getGameGenre()),
          oldGame.getGameGenre()));
      newGame.setGameIllust(prompt.inputString(String.format("작화(%s) ? ", oldGame.getGameIllust()),
          oldGame.getGameIllust()));
      newGame.setGameVoice(prompt.inputString(String.format("음성(%s) ? ", oldGame.getGameVoice()),
          oldGame.getGameVoice()));

      if (oldGame.equals(newGame)) {
        System.out.println("게임 정보 변경을 취소하였습니다.");
      }
      out.writeUTF("/game/update");
      out.writeObject(newGame);
      out.flush();

      response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }
      System.out.println("게임 정보를 변경했습니다.");
    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}

