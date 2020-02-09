package julja.gms.handler;

import julja.gms.dao.proxy.GameDaoProxy;
import julja.gms.domain.Game;
import julja.util.Prompt;

public class GameUpdateCommand implements Command {

  Prompt prompt;
  GameDaoProxy gameDao;

  public GameUpdateCommand(GameDaoProxy gameDao, Prompt prompt) {
    this.gameDao = gameDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {
      int no = prompt.inputInt("게임 품번? : ");

      Game oldGame = gameDao.findByNo(no);
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

      gameDao.update(newGame);
      System.out.println("게임 정보를 변경했습니다.");
    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}

