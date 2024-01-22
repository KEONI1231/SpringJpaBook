package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional //테스트 케이스에 transactional 이 있으면 기본적으로 테스트가 끝나면 데이터를 지움.
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;
    @Test
    @Transactional
    @Rollback(false) //롤백 : 테스트가 끝나면 테스트진행하면서 생긴 데이터를 지우는데, 해당 값을 false로 하면 지우지 않는다.
    public void testMember() throws Exception {
        //given
        Member member = new Member();
        member.setName("memberA");

        //when
        Long saveId = memberService.join(member);
        Member findMember = memberRepository.findOne(saveId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
    }
}
