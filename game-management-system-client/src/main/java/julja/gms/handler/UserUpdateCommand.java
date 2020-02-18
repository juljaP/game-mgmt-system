package julja.gms.handler;

import julja.gms.dao.UserDao;
import julja.gms.domain.User;
import julja.util.Prompt;

public class UserUpdateCommand implements Command {

  Prompt prompt;
  UserDao userDao;

  public UserUpdateCommand(UserDao userDao, Prompt prompt) {
    this.userDao = userDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("유저 번호? ");

      User oldUser = userDao.findByNo(no);
      User newUser = new User();
      newUser.setNo(oldUser.getNo());
      newUser.setUserEmail(prompt.inputString(String.format("이메일(%s) : ", oldUser.getUserEmail()),
          oldUser.getUserEmail()));
      newUser.setUserPW(prompt.inputString(String.format("비밀번호(%s) : ", oldUser.getUserPW()),
          oldUser.getUserPW()));
      newUser.setUserName(prompt.inputString(String.format("회원명(%s) : ", oldUser.getUserName()),
          oldUser.getUserName()));
      newUser.setUserResisteredDate(oldUser.getUserResisteredDate());

      if (oldUser.equals(newUser)) {
        System.out.println("유저 변경을 취소했습니다.");
        return;
      }

      userDao.update(newUser);
      System.out.println("회원 정보를 수정하였습니다.");
    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}
