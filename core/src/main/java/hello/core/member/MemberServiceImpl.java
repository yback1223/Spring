package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component //이거 하나만 붙여주고 빈 등록 안해도 된다.(자동)
public class MemberServiceImpl implements MemberService { //추상화에 의존

//     private final MemberRepository memberRepository = new MemoryMemberRepository(); //구체화에 의존, 배우가 상대 배우 캐스팅하는 격
     //MemberRepository interface 타입의 변수를 선언해주고 구현 객체를 할당해줘야 한다.
     //안그러면 nullpointerexception 터진다.
     //MemoryMemberRepository()로 구현 객체를 선택해줘야 한다.

     private final MemberRepository memberRepository;

     //ComponentScan으로 인해 MemberServiceImpl가 스프링 빈으로 등록이 되기 때문에, 다음의 구문을 의존관계 주입시킬 방법이 없다.
     //그래서 자동 의존 관계 주입이 필요하다. 마치 ac.getBean(MemberRepository.class)와 같다.
     @Autowired //Autowired를 붙여주면, 자동으로 MemberRepository의 타입에 맞는 것을 찾아와서 주입해준다.
     public MemberServiceImpl(MemberRepository memberRepository) {
          //MemberRepository에 뭐가 들어갈지를 생성자를 통해서 결정한다.
          this.memberRepository = memberRepository;

          //이렇게 되면 MemberServiceImpl에는 오직 MemberRepository라는 interface만 남게 된다.
          //추상화에만 의존하게 된다.
     }
     //MemberServiceImpl의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부(AppConfig)에서 결정된다.
     //MemberServiceImpl은 이제부터 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중

     @Override
     public void join(Member member) {
          //join에서 save를 호출하면 다형성에 의해서 MemberRepository의 interface가 아닌 MemoryMemberRepository의 save를 호출한다.
          memberRepository.save(member);
     }

     @Override
     public Member findMember(Long memberId) {
          return memberRepository.findById(memberId);
     }

     //테스트 용도
     public MemberRepository getMemberRepository() {
          return memberRepository;
     }
}

//추상화와 구체화 모두에 의존하고 있다. DIP 위반