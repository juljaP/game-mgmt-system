package julja.gms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import julja.gms.domain.Game;
import julja.gms.service.GameService;
import julja.util.Prompt;
import julja.util.RequestMapping;

@Component
public class GameUpdateServlet {

  GameService gameService;

  public GameUpdateServlet(GameService gameService) {
    this.gameService = gameService;
  }

  @RequestMapping("/game/update")
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "번호? ");

    Game old = gameService.findByNo(no);

    if (old == null) {
      out.println("해당 번호의 게임이 존재하지 않습니다.");
      return;
    }

    Game game = new Game();
    game.setNo(no);
    game.setGameName(Prompt.getString(in, out, String.format("게임명(%s) : ", old.getGameName()),
        old.getGameName()));
    game.setGameProduction(Prompt.getString(in, out,
        String.format("제작사(%s) : ", old.getGameProduction()), old.getGameProduction()));
    game.setGameDate(Prompt.getDate(in, out, String.format("발매일(%s) : ", old.getGameDate()),
        old.getGameDate().toString()));
    game.setGamePlatform(Prompt.getString(in, out,
        String.format("플랫폼(%s) : ", old.getGamePlatform()), old.getGamePlatform()));
    game.setGameGenre(Prompt.getString(in, out, String.format("장르(%s) : ", old.getGameGenre()),
        old.getGameGenre()));
    game.setGameIllust(Prompt.getString(in, out, String.format("작화(%s) : ", old.getGameIllust()),
        old.getGameIllust()));
    game.setGameVoice(Prompt.getString(in, out, String.format("음성(%s) : ", old.getGameVoice()),
        old.getGameVoice()));
    game.setPhoto(
        Prompt.getString(in, out, String.format("사진(%s) : ", old.getPhoto()), old.getPhoto()));

    if (gameService.update(game) > 0) {
      out.println("게임 정보를 변경하였습니다.");
    } else {
      out.println("데이터 저장 실패.");
    }
  }

}
