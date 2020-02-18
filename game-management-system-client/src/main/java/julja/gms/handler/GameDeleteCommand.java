package julja.gms.handler;

import julja.gms.dao.GameDao;
import julja.util.Prompt;

public class GameDeleteCommand implements Command {

  Prompt prompt;
  GameDao gameDao;

  public GameDeleteCommand(GameDao gameDao, Prompt prompt) {
    this.gameDao = gameDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("게임 품번? ");
      if (gameDao.delete(no) > 0) {
        System.out.println("게임을 삭제했습니다.");
      } else {
        System.out.println("해당하는 게임 번호가 없습니다.");
      }
    } catch (Exception e) {
      System.out.println("삭제 실패!");
    }
  }

}
