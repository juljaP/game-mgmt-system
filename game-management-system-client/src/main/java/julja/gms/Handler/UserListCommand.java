package julja.gms.Handler;

import java.util.Iterator;
import java.util.List;
import julja.gms.domain.User;

public class UserListCommand implements Command {

  List<User> userList;

  public UserListCommand(List<User> list) {
    userList = list;
  }

  @Override
  public void execute() {
    Iterator<User> iterator = userList.iterator();
    while (iterator.hasNext()) {
      User u = iterator.next();
      System.out.printf("[%d] %s | %s | %s\n", u.getUserNum(), u.getUserEmail(), u.getUserName(),
          u.getUserResisteredDate());
    }
  }

}
