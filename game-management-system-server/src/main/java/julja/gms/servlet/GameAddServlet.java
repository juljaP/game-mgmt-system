package julja.gms.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;
import julja.gms.dao.GameDao;
import julja.gms.domain.Game;

public class GameAddServlet implements Servlet {

  GameDao gameDao;

  public GameAddServlet(GameDao gameDao) {
    this.gameDao = gameDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    Game game = new Game();
    out.println("게임명 : \n!{}!");
    game.setGameName(in.nextLine());
    out.println("제작사 : \n!{}!");
    game.setGameProduction(in.nextLine());
    out.println("발매일 : \n!{}!");
    game.setGameDate(Date.valueOf(in.nextLine()));
    out.println("플랫폼 : \n!{}!");
    game.setGamePlatform(in.nextLine());
    out.println("장르 : \n!{}!");
    game.setGameGenre(in.nextLine());
    out.println("사진 : \n!{}!");
    game.setPhoto(in.nextLine());
    out.println("작화 : \n!{}!");
    game.setGameIllust(in.nextLine());
    out.println("음성 : \n!{}!");
    game.setGameVoice(in.nextLine());

    if (gameDao.insert(game) > 0) {
      out.println("게임 정보를 입력하였습니다.");
    } else {
      out.println("게임 정보 입력을 실패했습니다.");
    }
  }

}
