package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;


public class AutowiredTest {

     @Test
     void AutowiredOption() {
           ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class); //이렇게 하면 TestBean이 스프링빈으로 등록이 된다.
     }

     static class TestBean {

          @Autowired(required = false) //의존 관계가 없다면 메서드 자체가 호출이 되지 않는다.
          public void setNoBean1(Member noBean1) {
               System.out.println("noBean1 = " + noBean1);
          }

          @Autowired
          public void setNoBean2(@Nullable Member noBean2) { //호출은 되지만, 의존 관계가 없다면 NUll 출력
               System.out.println("noBean2 = " + noBean2);
          }

          @Autowired
          public void setNoBean3(Optional<Member> noBean3) { //호출은 되지만, 의존 관계가 없다면 Optional.empty 출력
               System.out.println("noBean3 = " + noBean3);
          }
     }

     //@Nullable, Optional은 스프링 전반에서 지원한다.

}
