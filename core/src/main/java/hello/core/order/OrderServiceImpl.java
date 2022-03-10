package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component //이거 하나만 붙여주고 빈 등록 안해도 된다.(자동)
public class OrderServiceImpl implements OrderService {

//     memberRepository에서 회원 찾아야 하니까 MemoryMemberRepository
//     private final MemberRepository memberRepository = new MemoryMemberRepository();

     //이 다음의 것을 사용한다면 추상과 구체에 모두 의존하는 것이므로 주석처리 한다.
     //OrderServiceImpl가 직접 객체를 생성해서 변수 할당까지 하는 것은 배우가 상대 배우를 캐스팅하는 것과 같다.
//     private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

     //이건 DIP는 지켰지만, 구현 객체가 없기 때문에 nullpointexcetpion이 발생한다.
     //DIP를 지키려면 주입을 활용해야 한다.
     private final MemberRepository memberRepository;
     private final DiscountPolicy discountPolicy;

     //필드 주입의 경우 필드 변수에 @Autowired를 붙이면된다. 권장하지 않는다.
//     @Autowired private MemberRepository memberRepository;
//     @Autowired private DiscountPolicy discountPolicy;

     //생성자
     @Autowired //만약 생성자가 하나만 있다면 @Autowired를 생략해도 된다.
     public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
          System.out.println("OrderServiceImpl");
          //OrderServiceImpl을 오직 추상화에만 의존하게 하여 DIP를 지키고 생성자를 통해서 객체 주입을 시켜주는 방법을 사용한다.
          this.memberRepository = memberRepository;
          this.discountPolicy = discountPolicy;
     }

     //수정자
//     @Autowired
//     public void setMemberRepository(MemberRepository memberRepository) {
//          System.out.println("memberRepository = " + memberRepository);
//          this.memberRepository = memberRepository;
//     }
//
//     @Autowired
//     public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//          System.out.println("discountPolicy = " + discountPolicy);
//          this.discountPolicy = discountPolicy;
//     }

     @Override
     public Order createOrder(Long memberId, String itemName, int itemPrice) {
//          일단 멤버 찾고
          Member member = memberRepository.findById(memberId);
//          찾은 멤버로 할인 정책에 따라 할인 가격 찾기
          int discountPrice = discountPolicy.discount(member, itemPrice);
//          OrderService입장에서는 할인에 대해서는 모르겠으니 discountPolicy한테 역할을 떠넘기는 것
//          아주 좋은 단일 체계 원칙, 만일 할인에 고칠게 있으면 할인 쪽만 고치면 되기 때문이다.

//          생성된 주문을 반환
          return new Order(memberId, itemName, itemPrice, discountPrice);
     }

     //테스트 용도
     public MemberRepository getMemberRepository() {
          return memberRepository;
     }
}
