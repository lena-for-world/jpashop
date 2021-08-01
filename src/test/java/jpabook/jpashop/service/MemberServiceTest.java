package jpabook.jpashop.service;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest // springboot를 띄운 상태로 테스트 하려면 꼭 필요
@Transactional //
public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        Member member = new Member();
        member.setName("kim");
        Long saveId = memberService.join(member);
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");
        memberService.join(member1);
        memberService.join(member2);
        fail("예외가 발생해야 한다."); // 여기까지 코드가 오면 안되는데, 오면 오류를 발생시키자
    }
}
