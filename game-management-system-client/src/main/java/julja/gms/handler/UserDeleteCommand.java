package julja.gms.handler;

import julja.gms.dao.UserDao;
import julja.util.Prompt;

public class UserDeleteCommand implements Command {

  Prompt prompt;
  UserDao userDao;

  public UserDeleteCommand(UserDao userDao, Prompt prompt) {
    this.userDao = userDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("회원 번호? ");
      if (userDao.delete(no) > 0)
        System.out.println("회원을 삭제했습니다.");
      else
        System.out.println("해당 번호의 회원이 존재하지 않습니다.");
    } catch (Exception e) {
      System.out.println("삭제 실패!");
    }
  }

}
