package hello.core.member;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component //이거 하나만 붙여주고 빈 등록 안해도 된다.
public class MemoryMemberRepository implements MemberRepository {
     //저장소니까 Map으로 구현, 원래 concurredHashMap을 써야한다.(실무)
     private static Map<Long, Member> store = new HashMap<>();

     @Override
     public void save(Member member) {
          store.put(member.getId(), member);
     }

     @Override
     public Member findById(Long memberId) {
          return store.get(memberId);
     }
}
