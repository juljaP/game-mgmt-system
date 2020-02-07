package julja.gms.domain;

import java.io.Serializable;
import java.sql.Date;

public class Board implements Serializable {

  private static final long serialVersionUID = 6151682943645316748L;
  private String bbsName, bbsText;
  private Date today;
  private int no, bbsHits;

  public static Board valueOf(String csv) {
    String[] data = csv.split(", ");
    Board board = new Board();
    board.setNo(Integer.parseInt(data[0]));
    board.setBbsName(data[1]);
    board.setBbsText(data[2]);
    board.setToday(Date.valueOf(data[3]));
    board.setBbsHits(Integer.parseInt(data[4]));
    return board;
  }

  public String toCsvString() {
    return String.format("%d, %s, %s, %s, %d", this.getNo(), this.getBbsName(), this.getBbsText(),
        this.getToday(), this.getBbsHits());
  }

  public String getBbsName() {
    return bbsName;
  }

  public void setBbsName(String bbsName) {
    this.bbsName = bbsName;
  }

  public String getBbsText() {
    return bbsText;
  }

  public void setBbsText(String bbsText) {
    this.bbsText = bbsText;
  }

  public Date getToday() {
    return today;
  }

  public void setToday(Date today) {
    this.today = today;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public int getBbsHits() {
    return bbsHits;
  }

  public void setBbsHits(int bbsHits) {
    this.bbsHits = bbsHits;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + bbsHits;
    result = prime * result + ((bbsName == null) ? 0 : bbsName.hashCode());
    result = prime * result + no;
    result = prime * result + ((bbsText == null) ? 0 : bbsText.hashCode());
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
    Board other = (Board) obj;
    if (bbsHits != other.bbsHits)
      return false;
    if (bbsName == null) {
      if (other.bbsName != null)
        return false;
    } else if (!bbsName.equals(other.bbsName))
      return false;
    if (no != other.no)
      return false;
    if (bbsText == null) {
      if (other.bbsText != null)
        return false;
    } else if (!bbsText.equals(other.bbsText))
      return false;
    return true;
  }

}
