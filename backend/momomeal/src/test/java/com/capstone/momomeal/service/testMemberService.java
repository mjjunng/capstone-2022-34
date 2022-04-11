package com.capstone.momomeal.service;

import com.capstone.momomeal.domain.Members;
import com.capstone.momomeal.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class testMemberService {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){ // Dependency Injection = DI
        memberRepository  = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입(){ //회원 가입
        //given
        Members memberDTO = new Members();
        memberDTO.setUsername("hello");
        //spring으로 두면 아래 테스트케이스하고 중첩되기에
        //AfterEach로 테스트 실행할때마다 memberRepository의 List타입 store을 초기화 한다
        memberDTO.setAge(20);
        memberDTO.setNickname("demo");
        memberDTO.setHomeaddress("목동로 163");
        memberDTO.setPwd("1234");
        memberDTO.setPhonenumber("xxx-xxxx-xxxx");
        memberDTO.setUserrate(5.0F);


        //when
        String name = memberService.join(memberDTO);
        System.out.println(name);

        //then
        Members one = memberService.findOne(name).get();
        //assertThat(one.getUserName()).isEqualTo(memberDTO.getUserName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Members member1 = new Members();
        member1.setUsername("spring");

        //MemberDTO member2 = new MemberDTO();
        //member2.setUserName("spring");

        Members member3 = new Members();
        member3.setUsername("kang");

        Members member4 = new Members();
        member4.setUsername("kang");

        memberService.join(member1);
        memberService.join(member3);
        //assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        try {
            memberService.join(member4);
            fail("예외 발생해야 합니다");
        }catch(IllegalStateException e){
        }
    }
}
