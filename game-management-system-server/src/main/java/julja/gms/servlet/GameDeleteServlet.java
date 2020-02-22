package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import julja.gms.dao.GameDao;

public class GameDeleteServlet implements Servlet {

  GameDao gameDao;

  public GameDeleteServlet(GameDao gameDao) {
    this.gameDao = gameDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    out.println("번호? \n!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    if (gameDao.delete(no) > 0) {
      out.println("해당 품번의 게임을 삭제하였습니다.");
    } else {
      out.println("해당 품번의 게임이 없습니다.");
    }
  }

}
