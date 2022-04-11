package com.capstone.momomeal.repository;

import com.capstone.momomeal.domain.Members;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class MemoryMemberRepositoryTest {

    MemberRepository memberRepository = new MemoryMemberRepository();

    @Test
    public void 세이브(){
        Members member = new Members();
        member.setUsername("userA");

        memberRepository.save(member);

        Members result = memberRepository.findById(member.getUsername()).get();

        Assertions.assertEquals(member,result);
        System.out.println("user name = " + member.getUsername());
    }
    @Test
    public void 중복확인(){
        Members member1 = new Members();
        member1.setUsername("testA");

        Members member2 = new Members();
        member2.setUsername("testB");

        Members member3 = new Members();
        member3.setUsername("testB");

        memberRepository.save(member1);
        memberRepository.save(member2);

        if(memberRepository.findById(member3.getUsername()) == null){
            System.out.println("중복 없음");
        }else{
            System.out.println("중복 존재");
        }
    }
}
