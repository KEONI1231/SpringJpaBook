package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional //테스트 케이스에서의 transactional은 테스트가 끝나면 해당 데이터를 디비에서 지움
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");
        Member member2 = new Member();
        member2.setName("asdf");
        //when
        Long savedId = memberService.join(member);
        //Long savedId2 = memberService.join(member2);
        //then
        assertThat(memberRepository.findOne(savedId)).isEqualTo(member);
    }

    @Test
    public void 중복회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim1");

        //when
        memberService.join(member1);
        try {
            memberService.join(member2); //예외가 발생해야 한다.!!
        }catch (IllegalStateException e){
            return;
        }
        //then
        fail("예외가 발생해야 한다..");
    }

}