package julja.gms.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class PhotoBoard implements Serializable {

  private static final long serialVersionUID = -7700345198322575423L;

  int no, hits;
  String title;
  Date creadtedDate;
  List<PhotoFile> files;
  Game game;


  public List<PhotoFile> getFiles() {
    return files;
  }

  public void setFiles(List<PhotoFile> files) {
    this.files = files;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public int getHits() {
    return hits;
  }

  public void setHits(int hits) {
    this.hits = hits;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getCreadtedDate() {
    return creadtedDate;
  }

  public void setCreadtedDate(Date creadtedDate) {
    this.creadtedDate = creadtedDate;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  @Override
  public String toString() {
    return "PhotoBoard [no=" + no + ", hits=" + hits + ", title=" + title + ", creadtedDate="
        + creadtedDate + ", game=" + game + "]";
  }

}
