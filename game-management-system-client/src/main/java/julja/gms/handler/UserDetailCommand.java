package julja.gms.handler;

import julja.gms.dao.UserDao;
import julja.gms.domain.User;
import julja.util.Prompt;

public class UserDetailCommand implements Command {

  Prompt prompt;
  UserDao userDao;

  public UserDetailCommand(UserDao userDao, Prompt prompt) {
    this.userDao = userDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("게시글 번호? ");
      User u = userDao.findByNo(no);
      System.out.printf("이메일 : %s\n", u.getUserEmail());
      System.out.printf("비밀번호 : %s\n", u.getUserPW());
      System.out.printf("회원명 : %s\n", u.getUserName());
      System.out.printf("가입일 : %s\n", u.getUserResisteredDate());
    } catch (Exception e) {
      System.out.println("조회 실패!");
    }
  }

}
