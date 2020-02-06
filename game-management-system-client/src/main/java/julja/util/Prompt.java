package julja.util;

import java.sql.Date;
import java.util.Scanner;

public class Prompt {
  
  Scanner input;
  
  public Prompt(Scanner input) {
    this.input = input;
  }

  public String inputString(String label) {
    System.out.print(label);
    return input.nextLine();
  }
  
  public String inputString(String label, String oldValue) {
    System.out.print(label);
    String s = input.nextLine();
    if (s.length() == 0 )
      return oldValue;
    return s;
  }
  
  public int inputInt(String label) {
    System.out.print(label);
    return Integer.parseInt(input.nextLine());
  }
  
  public int inputInt(String label, int oldValue) {
    System.out.print(label);
    String s = input.nextLine();
    if (s.length() == 0 )
      return oldValue;
    return Integer.parseInt(s);
  }
  
  public Date inputDate(String label) {
    System.out.print(label);
    return Date.valueOf(input.nextLine());
  }
  
  public Date inputDate(String label, Date oldValue) {
    System.out.print(label);
    String s = input.nextLine();
    if (s.length() == 0 )
      return oldValue;
    return Date.valueOf(s);
  }
  
}
