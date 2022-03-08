package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

     @Test
     @DisplayName("스프링 없는 순수한 DI 컨테이너")
     void pureContainer() {
          AppConfig appConfig = new AppConfig();

          //1. 조회: 호출할 때마다 객체를 생성
          MemberService memberService1 = appConfig.memberService();

          //2. 조회: 호출할 때마다 객체를 생성
          MemberService memberService2 = appConfig.memberService();

          //memberService1 != memberService2
          assertThat(memberService1).isNotSameAs(memberService2);

          //참조값이 다른 것을 확인
          System.out.println("memberService1 = " + memberService1);
          System.out.println("memberService2 = " + memberService2);
          //호출할 때마다 객체를 생성하면 너무 비효율적이다.
          //해당 객체가 1개만 생성되고, 공유하도록 설계해야 한다.
     }

     @Test
     @DisplayName("싱글톤 패턴을 적용한 객체 사용")
     void singletonServiceTest() {
          SingletonService singletonService1 = SingletonService.getInstance();
          SingletonService singletonService2 = SingletonService.getInstance();

          System.out.println("singletonService1 = " + singletonService1);
          System.out.println("singletonService2 = " + singletonService2);
          //싱글톤 객체의 인스턴스를 사용하므로 두 객체의 주소는 같다.

          assertThat(singletonService1).isSameAs(singletonService2);
     }

     @Test
     @DisplayName("스프링 컨테이너와 싱글톤")
     void springContainer() {
//          AppConfig appConfig = new AppConfig();
          ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
          MemberService memberService1 = ac.getBean("memberService", MemberService.class);
          MemberService memberService2 = ac.getBean("memberService", MemberService.class);

          //memberService1 = memberService2
          assertThat(memberService1).isSameAs(memberService2);

          //참조값이 같은 것을 확인
          System.out.println("memberService1 = " + memberService1);
          System.out.println("memberService2 = " + memberService2);
     }

}
