package julja.gms.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;
import julja.gms.dao.GameDao;
import julja.gms.domain.Game;

public class GameUpdateServlet implements Servlet {

  GameDao gameDao;

  public GameUpdateServlet(GameDao gameDao) {
    this.gameDao = gameDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    out.println("번호? \n!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    Game old = gameDao.findByNo(no);

    if (old == null) {
      out.println("해당 번호의 게임이 존재하지 않습니다.");
      return;
    }

    Game game = new Game();
    game.setNo(no);
    out.println(String.format("게임명(%s) : \n!{}!", old.getGameName()));
    game.setGameName(in.nextLine());
    out.println(String.format("제작사(%s) : \n!{}!", old.getGameProduction()));
    game.setGameProduction(in.nextLine());
    out.println(String.format("발매일(%s) : \n!{}!", old.getGameDate()));
    game.setGameDate(Date.valueOf(in.nextLine()));
    out.println(String.format("플랫폼(%s) : \n!{}!", old.getGamePlatform()));
    game.setGamePlatform(in.nextLine());
    out.println(String.format("장르(%s) : \n!{}!", old.getGameGenre()));
    game.setGameGenre(in.nextLine());
    out.println(String.format("작화(%s) : \n!{}!", old.getGameIllust()));
    game.setGameIllust(in.nextLine());
    out.println(String.format("음성(%s) : \n!{}!", old.getGameVoice()));
    game.setGameVoice(in.nextLine());
    out.println(String.format("사진(%s) : \n!{}!", old.getPhoto()));
    game.setPhoto(in.nextLine());

    if (gameDao.update(game) > 0) {
      out.println("게임 정보를 변경하였습니다.");
    } else {
      out.println("데이터 저장 실패.");
    }
  }

}
