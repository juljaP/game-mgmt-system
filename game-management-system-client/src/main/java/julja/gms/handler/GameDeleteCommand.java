package julja.gms.handler;

import julja.gms.dao.proxy.GameDaoProxy;
import julja.util.Prompt;

public class GameDeleteCommand implements Command {

  Prompt prompt;
  GameDaoProxy gameDao;

  public GameDeleteCommand(GameDaoProxy gameDao, Prompt prompt) {
    this.gameDao = gameDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("게임 품번? ");
      gameDao.delete(no);
      System.out.println("게임을 삭제했습니다.");
    } catch (Exception e) {
      System.out.println("삭제 실패!");
    }
  }

}
