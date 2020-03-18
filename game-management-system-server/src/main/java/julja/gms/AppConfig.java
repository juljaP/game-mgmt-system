package julja.gms;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = "julja.gms")

public class AppConfig {


  public AppConfig() {
    System.out.println("AppConfig 객체 생성!");
  }

}
