package julja.gms.handler;

import julja.gms.dao.proxy.UserDaoProxy;
import julja.util.Prompt;

public class UserDeleteCommand implements Command {

  Prompt prompt;
  UserDaoProxy userDao;

  public UserDeleteCommand(UserDaoProxy userDao, Prompt prompt) {
    this.userDao = userDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("회원 번호? ");
      userDao.delete(no);
      System.out.println("회원을 삭제했습니다.");
    } catch (Exception e) {
      System.out.println("삭제 실패!");
    }
  }

}
