package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

     @Test
     void statefulServiceSingleton() {
          ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
          StatefulService statefulService1 = ac.getBean(StatefulService.class);
          StatefulService statefulService2 = ac.getBean(StatefulService.class);

          //ThreadA: A사용자 10000원 주문
//          statefulService1.order("userA", 10000);
          int userAPrice = statefulService1.order("userA", 10000);

          //ThreadB: B사용자 20000원 주문
//          statefulService1.order("userA", 20000);
          int userBPrice = statefulService1.order("userB", 20000);

          //ThreadA: 사용자A 주문 금액 조회
//          int price = statefulService1.getPrice();
//          System.out.println("price = " + price);
          //A사용자가 조회를 하는데 10000원이 아닌 20000원이 출력된다.
          //이를 막기 위해서는 무상태로 설계해야 한다.
          System.out.println("userAPrice = " + userAPrice);


          Assertions.assertThat(userAPrice).isEqualTo(10000);
     }

     static class TestConfig {

          @Bean
          public StatefulService statefulService() {
               return new StatefulService();
          }
     }


}