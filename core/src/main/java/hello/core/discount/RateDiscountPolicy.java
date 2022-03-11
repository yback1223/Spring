package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component //이거 하나만 붙여주고 빈 등록 안해도 된다.
@MainDiscountPolicy//@Qualifier("mainDiscountPolicy") 문자이기 때문에 컴파일 타임에서 오류를 잡을 수 없다.(실수로 잘못 쳤을 경우)
public class RateDiscountPolicy implements DiscountPolicy {

     private int discountPercent = 10;

     @Override
     public int discount(Member member, int price) {
          if (member.getGrade() == Grade.VIP) {
               return price * discountPercent / 100;
          } else {
               return 0;
          }
     }
}
