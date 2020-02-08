package julja.gms.domain;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable {

  private static final long serialVersionUID = -8578616307920400868L;
  private int no;
  private String userEmail, userPW, userName;
  private Date userResisteredDate;

  public static User valueOf(String csv) {
    String[] data = csv.split(", ");
    User user = new User();
    user.setNo(Integer.parseInt(data[0]));
    user.setUserEmail(data[1]);
    user.setUserPW(data[2]);
    user.setUserName(data[3]);
    user.setUserResisteredDate(Date.valueOf(data[4]));
    return user;
  }

  public String toCsvString() {
    return String.format("%d, %s, %s, %s, %s", this.getNo(), this.getUserEmail(), this.getUserPW(),
        this.getUserName(), this.getUserResisteredDate());
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getUserPW() {
    return userPW;
  }

  public void setUserPW(String userPW) {
    this.userPW = userPW;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Date getUserResisteredDate() {
    return userResisteredDate;
  }

  public void setUserResisteredDate(Date userResisteredDate) {
    this.userResisteredDate = userResisteredDate;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
    result = prime * result + ((userName == null) ? 0 : userName.hashCode());
    result = prime * result + no;
    result = prime * result + ((userPW == null) ? 0 : userPW.hashCode());
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
    User other = (User) obj;
    if (userEmail == null) {
      if (other.userEmail != null)
        return false;
    } else if (!userEmail.equals(other.userEmail))
      return false;
    if (userName == null) {
      if (other.userName != null)
        return false;
    } else if (!userName.equals(other.userName))
      return false;
    if (no != other.no)
      return false;
    if (userPW == null) {
      if (other.userPW != null)
        return false;
    } else if (!userPW.equals(other.userPW))
      return false;
    return true;
  }

}
