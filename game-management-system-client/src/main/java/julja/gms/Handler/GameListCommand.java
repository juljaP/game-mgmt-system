package julja.gms.Handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import julja.gms.domain.Game;

public class GameListCommand implements Command {

  ObjectInputStream in;
  ObjectOutputStream out;

  public GameListCommand(ObjectInputStream in, ObjectOutputStream out) {
    this.in = in;
    this.out = out;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute() {

    try {
      out.writeUTF("/game/list");
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      List<Game> games = (List<Game>) in.readObject();

      for (Game g : games) {
        System.out.printf("[%d] %s | %s | %s | %s\n", g.getNo(), g.getGameName(),
            g.getGameProduction(), g.getGameDate(), g.getGameGenre());
      }
    } catch (Exception e) {
      System.out.println("통신 오류 발생.");
    }
  }
}

