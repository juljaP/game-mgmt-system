package julja.gms.Handler;

import java.util.List;
import julja.gms.domain.Game;
import julja.util.Prompt;

public class GameUpdateCommand implements Command {

  Prompt prompt;
  List<Game> gameList;

  public GameUpdateCommand(Prompt prompt, List<Game> list) {
    this.prompt = prompt;
    gameList = list;
  }

  @Override
  public void execute() {

    int index = indexOfLesson(prompt.inputInt("게임 품번? : "));
    if (index == -1) {
      System.out.println("해당하는 게임이 존재하지 않습니다.");
      return;
    }
    Game oldGame = this.gameList.get(index);
    Game newGame = new Game();
    newGame.setGameNum(oldGame.getGameNum());
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
    } else {
      gameList.set(index, newGame);
      System.out.println("게임 정보를 변경했습니다.");
    }
  }

  private int indexOfLesson(int num) {
    for (int i = 0; i < this.gameList.size(); i++) {
      if (this.gameList.get(i).getGameNum() == num) {
        return i;
      }
    }
    return -1;
  }

}

