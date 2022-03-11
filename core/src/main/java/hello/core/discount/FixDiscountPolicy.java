package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//정액 할인 제도
@Component //FixDiscountPolicy까지 Component를 적용해서 자동 주입 시키면 빈이 두개(fix, rate)가 되므로 NoUniqueBeanDefinitionException 발생한다.
//@Qualifier("fixDiscountPolicy") //@Primary를 사용하면 이게 주가 되므로 스프링 빈을 사용할 때 이게 우선 선택된다.
public class FixDiscountPolicy implements DiscountPolicy {

     @Override
     public int discount(Member member, int price) {
          if (member.getGrade() == Grade.VIP) {
               //1000원 할인
               return 1000;
          } else {
               return 0;
          }
     }
}
