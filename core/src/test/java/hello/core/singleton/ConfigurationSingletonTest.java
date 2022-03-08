package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

     @Test
     void configurationTest() {
          ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

          MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
          OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
          MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

          MemberRepository memberRepository1 = memberService.getMemberRepository();
          MemberRepository memberRepository2 = orderService.getMemberRepository();

          System.out.println("memberService -> memberRepository1 = " + memberRepository1);
          System.out.println("orderService -> memberRepository2 = " + memberRepository2);
          System.out.println("memberRepository = " + memberRepository);

          assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
          assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
     }

     @Test
     void configurationDeep() {
          ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
          AppConfig bean = ac.getBean(AppConfig.class);

          System.out.println("bean = " + bean.getClass());
          //bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$b73996c7 뒤에 이상한 문자열이 class명에 붙었다.(CGLIB이 적용)
          //AppConfig에서 @Configuration을 주석처리하면 hello.core.AppConfig가 출력된다.(CGLIB 미적용)
          //하지만 이렇게 되면 싱글톤이 깨져버린다.
     }
}
