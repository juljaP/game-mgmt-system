package julja.gms;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import julja.gms.dao.GameDao;
import julja.gms.domain.Game;
import julja.gms.servlet.Servlet;
import julja.util.Prompt;

public class GameSearchServlet implements Servlet {

  GameDao gameDao;

  public GameSearchServlet(GameDao gameDao) {
    this.gameDao = gameDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    Map<String, Object> params = new HashMap<>();

    String keyword = Prompt.getString(in, out, "게임명? ");
    if (keyword.length() > 0) {
      params.put("gameName", keyword);
    }
    keyword = Prompt.getString(in, out, "제작사? ");
    if (keyword.length() > 0) {
      params.put("gameProduction", keyword);
    }
    keyword = Prompt.getString(in, out, "플랫폼? ");
    if (keyword.length() > 0) {
      params.put("gamePlatform", keyword);
    }
    keyword = Prompt.getString(in, out, "장르? ");
    if (keyword.length() > 0) {
      params.put("gameGenre", keyword);
    }
    keyword = Prompt.getString(in, out, "일러스트레이터? ");
    if (keyword.length() > 0) {
      params.put("gameIllust", keyword);
    }
    keyword = Prompt.getString(in, out, "성우? ");
    if (keyword.length() > 0) {
      params.put("gameVoice", keyword);
    }

    out.println("------------------------------------------------");
    out.println("[검색결과]");

    List<Game> games = gameDao.findByKeyword(params);

    for (Game g : games) {
      out.printf("[%d] %s | %s | %s | %s\n", g.getNo(), g.getGameName(), g.getGamePlatform(),
          g.getGameGenre(), g.getGameDate());
    }
  }

}