package julja.gms.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import julja.gms.domain.User;

public class UserObjectFileDao {


  String filename;
  List<User> list;

  public UserObjectFileDao(String filename) {
    this.filename = filename;
    list = new ArrayList<>();
    loadData();
  }

  @SuppressWarnings("unchecked")
  private void loadData() {

    File file = new File(filename);
    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      list = (List<User>) in.readObject();
      System.out.printf("%d개의 유저 데이터, ", list.size());
    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생" + e.getMessage());
    }
  }

  private void saveData() {
    File file = new File(filename);
    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(list);
      System.out.printf("%d개의 유저 데이터, ", list.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류가 발생하였습니다 : " + e.getMessage());
    }
  }

  public int insert(User user) throws Exception {

    if (indexOf(user.getNo()) > -1) {
      return 0;
    }

    list.add(user);
    saveData();
    return 1;
  }

  public List<User> findAll() throws Exception {
    return list;
  }

  public User findByNo(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return null;
    }
    return list.get(index);
  }

  public int update(User user) throws Exception {
    int index = indexOf(user.getNo());

    if (index == -1) {
      return 0;
    }

    list.set(index, user);
    saveData();
    return 1;
  }

  public int delete(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return 0;
    }

    list.remove(index);
    saveData();
    return 1;
  }

  private int indexOf(int no) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }

}
