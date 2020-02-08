package julja.gms.domain;

import java.io.Serializable;
import java.sql.Date;

public class Game implements Serializable {

  private static final long serialVersionUID = 4108093492957016867L;
  private int no;
  private String gameName, gameProduction, gamePlatform, gameGenre, gameIllust, gameVoice;
  private Date gameDate;

  public static Game valueOf(String csv) {
    String[] data = csv.split(", ");
    Game game = new Game();
    game.setNo(Integer.parseInt(data[0]));
    game.setGameName(data[1]);
    game.setGameProduction(data[2]);
    game.setGameDate(Date.valueOf(data[3]));
    game.setGamePlatform(data[4]);
    game.setGameGenre(data[5]);
    game.setGameIllust(data[6]);
    game.setGameVoice(data[7]);
    return game;
  }

  public String toCsvString() {
    return String.format("%d, %s, %s, %s, %s, %s, %s, %s", this.getNo(), this.getGameName(),
        this.getGameProduction(), this.getGameDate(), this.getGamePlatform(), this.getGameGenre(),
        this.getGameIllust(), this.getGameVoice());
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getGameName() {
    return gameName;
  }

  public void setGameName(String gameName) {
    this.gameName = gameName;
  }

  public String getGameProduction() {
    return gameProduction;
  }

  public void setGameProduction(String gameProduction) {
    this.gameProduction = gameProduction;
  }

  public String getGamePlatform() {
    return gamePlatform;
  }

  public void setGamePlatform(String gamePlatform) {
    this.gamePlatform = gamePlatform;
  }

  public String getGameGenre() {
    return gameGenre;
  }

  public void setGameGenre(String gameGenre) {
    this.gameGenre = gameGenre;
  }

  public String getGameIllust() {
    return gameIllust;
  }

  public void setGameIllust(String gameIllust) {
    this.gameIllust = gameIllust;
  }

  public String getGameVoice() {
    return gameVoice;
  }

  public void setGameVoice(String gameVoice) {
    this.gameVoice = gameVoice;
  }

  public Date getGameDate() {
    return gameDate;
  }

  public void setGameDate(Date gameDate) {
    this.gameDate = gameDate;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((gameDate == null) ? 0 : gameDate.hashCode());
    result = prime * result + ((gameGenre == null) ? 0 : gameGenre.hashCode());
    result = prime * result + ((gameIllust == null) ? 0 : gameIllust.hashCode());
    result = prime * result + ((gameName == null) ? 0 : gameName.hashCode());
    result = prime * result + no;
    result = prime * result + ((gamePlatform == null) ? 0 : gamePlatform.hashCode());
    result = prime * result + ((gameProduction == null) ? 0 : gameProduction.hashCode());
    result = prime * result + ((gameVoice == null) ? 0 : gameVoice.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Game other = (Game) obj;
    if (gameDate == null) {
      if (other.gameDate != null)
        return false;
    } else if (!gameDate.equals(other.gameDate))
      return false;
    if (gameGenre == null) {
      if (other.gameGenre != null)
        return false;
    } else if (!gameGenre.equals(other.gameGenre))
      return false;
    if (gameIllust == null) {
      if (other.gameIllust != null)
        return false;
    } else if (!gameIllust.equals(other.gameIllust))
      return false;
    if (gameName == null) {
      if (other.gameName != null)
        return false;
    } else if (!gameName.equals(other.gameName))
      return false;
    if (no != other.no)
      return false;
    if (gamePlatform == null) {
      if (other.gamePlatform != null)
        return false;
    } else if (!gamePlatform.equals(other.gamePlatform))
      return false;
    if (gameProduction == null) {
      if (other.gameProduction != null)
        return false;
    } else if (!gameProduction.equals(other.gameProduction))
      return false;
    if (gameVoice == null) {
      if (other.gameVoice != null)
        return false;
    } else if (!gameVoice.equals(other.gameVoice))
      return false;
    return true;
  }

}
