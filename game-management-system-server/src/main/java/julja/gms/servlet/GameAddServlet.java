package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import julja.gms.dao.GameDao;
import julja.gms.domain.Game;
import julja.util.Prompt;

public class GameAddServlet implements Servlet {

  GameDao gameDao;

  public GameAddServlet(GameDao gameDao) {
    this.gameDao = gameDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    Game game = new Game();
    game.setGameName(Prompt.getString(in, out, "게임명 : "));
    game.setGameProduction(Prompt.getString(in, out, "제작사 : "));
    game.setGameDate(Prompt.getDate(in, out, "발매일 : "));
    game.setGamePlatform(Prompt.getString(in, out, "플랫폼 : "));
    game.setGameGenre(Prompt.getString(in, out, "장르 : "));
    game.setPhoto(Prompt.getString(in, out, "사진 : "));
    game.setGameIllust(Prompt.getString(in, out, "작화 : "));
    game.setGameVoice(Prompt.getString(in, out, "음성 : "));

    if (gameDao.insert(game) > 0) {
      out.println("게임 정보를 입력하였습니다.");
    } else {
      out.println("게임 정보 입력을 실패했습니다.");
    }
  }

}
