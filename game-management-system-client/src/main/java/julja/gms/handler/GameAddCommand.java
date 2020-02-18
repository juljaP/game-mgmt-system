package julja.gms.handler;

import julja.gms.dao.GameDao;
import julja.gms.domain.Game;
import julja.util.Prompt;

public class GameAddCommand implements Command {

  Prompt prompt;
  GameDao gameDao;

  public GameAddCommand(GameDao gameDao, Prompt prompt) {
    this.gameDao = gameDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    Game g = new Game();
    // g.setNo(prompt.inputInt("품번 : "));
    g.setGameName(prompt.inputString("게임명 : "));
    g.setGameProduction(prompt.inputString("제작사 : "));
    g.setGameDate(prompt.inputDate("발매일 : "));
    g.setGamePlatform(prompt.inputString("플랫폼 : "));
    g.setGameGenre(prompt.inputString("장르 : "));
    g.setPhoto(prompt.inputString("사진 : "));
    g.setGameIllust(prompt.inputString("작화 : "));
    g.setGameVoice(prompt.inputString("음성 : "));
    System.out.println();

    try {
      gameDao.insert(g);
      System.out.println("저장하였습니다.");

    } catch (Exception e) {
      System.out.println("통신 오류 발생.");
    }

  }
}

