package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//AppConfig는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다.
//public class AppConfig {
//     public MemberService memberService() {
//          return new MemberServiceImpl(new MemoryMemberRepository()); //MemoryMemberRepository객체를 생성해서 이에 대한 참조값을 MemberServiceImpl에 대입해준다.(생성자 주입)
//          //MemberServiceImpl은 추상화에만 의존하고 AppConfig를 통해서 객체를 주입해준다.(생성자 주입)
//     }
//
//     public OrderService orderService() {
//          return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
//     }
//}

//AppConfig를 보면 역할과 구현 클래스가 한눈에 들어오게 바뀐 것을 확인할 수 있다.
@Configuration
public class AppConfig {

     @Bean
     public MemberService memberService() {
          System.out.println("call AppConfig.memberService");
          return new MemberServiceImpl(memberRepository());
     }

     @Bean
     public MemberRepository memberRepository() {
          System.out.println("call AppConfig.memberRepository");
          return new MemoryMemberRepository(); //MemoryMemberRepository를 다른 구현체로 변경할 때 한 부분만 변경하면 된다.
     }

     @Bean
     public OrderService orderService() {
          System.out.println("call AppConfig.orderService");
          return new OrderServiceImpl(memberRepository(), discountPolicy());
     }

     @Bean
     public DiscountPolicy discountPolicy() {
//          return new FixDiscountPolicy();
          return new RateDiscountPolicy();
          //할인 정책을 변경하기 위해서는 AppConfig에서만 코드를 변경하면 된다.
          //사용 영역의 코드는 전혀 건드릴 필요 없이 구성 영역의 코드만 바꾸면 원하는 결과를 얻을 수 있다.
     }

}
