package julja.gms.handler;

import julja.gms.dao.GameDao;
import julja.gms.domain.Game;
import julja.util.Prompt;

public class GameDetailCommand implements Command {

  Prompt prompt;
  GameDao gameDao;

  public GameDetailCommand(GameDao gameDao, Prompt prompt) {
    this.gameDao = gameDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("게임 품번? ");

      Game g = gameDao.findByNo(no);
      System.out.printf("게임명 : %s\n", g.getGameName());
      System.out.printf("제작사 : %s\n", g.getGameProduction());
      System.out.printf("플랫폼 : %s\n", g.getGamePlatform());
      System.out.printf("장르 : %s\n", g.getGameGenre());
      System.out.printf("발매일 : %s\n", g.getGameDate());
      System.out.printf("작화 : %s\n", g.getGameIllust());
      System.out.printf("음성 : %s\n", g.getGameVoice());
      System.out.printf("사진 : %s\n", g.getPhoto());
    } catch (Exception e) {
      System.out.println("조회 실패!");
    }
  }
}
