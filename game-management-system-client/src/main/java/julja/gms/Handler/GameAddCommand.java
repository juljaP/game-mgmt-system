package julja.gms.Handler;

import java.util.List;
import julja.gms.domain.Game;
import julja.util.Prompt;

public class GameAddCommand implements Command {

  Prompt prompt;
  List<Game> gameList;

  public GameAddCommand(Prompt prompt, List<Game> list) {
    this.prompt = prompt;
    gameList = list;
  }

  @Override
  public void execute() {
    Game g = new Game();
    g.setGameNum(prompt.inputInt("품번 : "));
    g.setGameName(prompt.inputString("게임명 : "));
    g.setGameProduction(prompt.inputString("제작사 : "));
    g.setGameDate(prompt.inputDate("발매일 : "));
    g.setGamePlatform(prompt.inputString("플랫폼 : "));
    g.setGameGenre(prompt.inputString("장르 : "));
    g.setGameIllust(prompt.inputString("작화 : "));
    g.setGameVoice(prompt.inputString("음성 : "));
    System.out.println("저장하였습니다.");
    gameList.add(g);
  }


}

