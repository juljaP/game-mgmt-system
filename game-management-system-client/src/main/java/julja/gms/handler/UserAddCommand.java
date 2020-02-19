package julja.gms.handler;

import java.sql.Date;
import julja.gms.dao.UserDao;
import julja.gms.domain.User;
import julja.util.Prompt;

public class UserAddCommand implements Command {

  Prompt prompt;
  UserDao userDao;

  public UserAddCommand(UserDao userDao, Prompt prompt) {
    this.userDao = userDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    User u = new User();
    // u.setNo(prompt.inputInt("번호 : "));
    u.setUserEmail(prompt.inputString("이메일 : "));
    u.setUserPW(prompt.inputString("비밀번호 : "));
    u.setUserName(prompt.inputString("회원명 : "));
    System.out.print("가입일 : ");
    // u.setUserResisteredDate(new Date(System.currentTimeMillis()));
    // System.out.println(u.getUserResisteredDate());
    System.out.println(new Date(System.currentTimeMillis()));
    System.out.println();

    try {
      userDao.insert(u);
      System.out.println("저장하였습니다.");

    } catch (Exception e) {
      System.out.println("데이터 저장 실패!");
    }
  }

}
