package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextBasicFindTest {
     AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

     @Test
     @DisplayName("빈 이름으로 조회")
     void findBeanByName() {
          MemberService memberService = ac.getBean("memberService", MemberService.class);
//          System.out.println("memberService = " + memberService);
//          System.out.println("memberService.getClass() = " + memberService.getClass());
          assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
     }

     @Test
     @DisplayName("이름 없이 타입으로 조회")
     void findBeanByType() {
          MemberService memberService = ac.getBean(MemberService.class);
          assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
     }

     @Test
     @DisplayName("구체 타입으로 조회")
     void findBeanByName2() {
          MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class); //스프링 컨테이너에 등록이 구체가 등록되어 있다면 조회가 된다.
          assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
     }

     @Test
     @DisplayName("빈 이름으로 조회 실패")
     void findBynameX() {
//          MemberService xxxxx = ac.getBean("xxxxx", MemberService.class);

          //람다식을 실행했을 때 예외가 터져야 test 성공
          assertThrows(NoSuchBeanDefinitionException.class,
                  () -> ac.getBean("xxxxx", MemberService.class)
          );
     }
}
