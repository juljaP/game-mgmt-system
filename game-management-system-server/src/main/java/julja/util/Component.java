package julja.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// 클래스 이름을 지정할 때 붙일 애노테이션 정의

@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
  String value() default "";
}

