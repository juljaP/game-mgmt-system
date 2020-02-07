package julja.gms;

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
import java.util.Map;
import julja.gms.domain.Board;
import julja.gms.domain.Game;
import julja.gms.domain.User;

public class DataLoaderListener implements julja.context.ApplicationContextListener {

  private List<Game> gameList = new ArrayList<>();
  private List<Board> boardList = new ArrayList<>();
  private List<User> userList = new ArrayList<>();

  @Override
  public void contextInitailized(Map<String, Object> context) {
    loadGameData();
    loadUserData();
    loadBoardData();
    context.put("gameList", gameList);
    context.put("boardList", boardList);
    context.put("userList", userList);
    System.out.println("데이터를 불러왔습니다.");
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    saveGameData();
    saveUserData();
    saveBoardData();
    System.out.println("데이터를 저장하였습니다.");
  }

  @SuppressWarnings("unchecked")
  private void loadGameData() {
    File file = new File("./game.ser");
    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      gameList = (List<Game>) in.readObject();
      System.out.printf("%d개의 게임 데이터, ", gameList.size());
    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생" + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  private void loadUserData() {

    File file = new File("./user.ser");
    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      userList = (List<User>) in.readObject();
      System.out.printf("%d개의 유저 데이터, ", userList.size());
    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생" + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  private void loadBoardData() {
    File file = new File("./board.ser");
    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      boardList = (List<Board>) in.readObject();
      System.out.printf("%d개의 게시글 데이터가 존재합니다.\n", boardList.size());
    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생" + e.getMessage());
    }
  }

  private void saveGameData() {
    File file = new File("./game.ser");
    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(gameList);
      System.out.printf("%d개의 게임 데이터, ", gameList.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류가 발생하였습니다 : " + e.getMessage());
    }
  }

  private void saveUserData() {
    File file = new File("./user.ser");
    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(userList);
      System.out.printf("%d개의 유저 데이터, ", userList.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류가 발생하였습니다 : " + e.getMessage());
    }
  }

  private void saveBoardData() {
    File file = new File("./board.ser");
    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(boardList);
      System.out.printf("%d개의 게시글 데이터가 존재합니다.\n", boardList.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류가 발생하였습니다 : " + e.getMessage());
    }
  }

}
