package com.capstone.momomeal.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@SpringBootTest
class MemberRepositoryTest {
    @Autowired MemberRepository memberRepository;

//    @Test
//    @Transactional
//    public void testMember() {
//        Member member = new Member();
//        Long savedId = memberRepository.save(member);Member findMember = memberRepository.find(savedId);
//        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//
//        Assertions.assertThat(findMember).isEqualTo(member); //JPA 엔티티 동일성 보장
//       }

}