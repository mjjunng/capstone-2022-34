//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.capstone.momomeal.service;

import com.capstone.momomeal.domain.Members;
import com.capstone.momomeal.repository.MemberRepository;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class testMemberintegrationService {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    public testMemberintegrationService() {
    }

    @Test
    void createAccount() {
        Members member1 = new Members();
        Members member2 = new Members();
        member1.setEmail("springTest3");
        member1.setPwd("springpwd3");
        member1.setRealName("springName3");
        member2.setEmail("sprintTest2");
        member2.setPwd("springpwd2");
        member2.setRealName("springName2");
        String name = this.memberService.join(member1);
        this.memberService.join(member2);
        System.out.println(name);
    }

    @Test
    void checkList() {
        List<Members> listdata = this.memberService.findAll();
        Iterator var2 = listdata.iterator();

        while(var2.hasNext()) {
            Members data = (Members)var2.next();
            System.out.println(data.toString());
        }

    }

    @Test
    public void check_duplicate_member() {
        Members member1 = new Members();
        member1.setEmail("test1");
        member1.setPwd("testpwd2");
        member1.setRealName("Kang");
        Members member2 = new Members();
        member2.setEmail("test1");
        member2.setPwd("testpwd2");
        member2.setRealName("Kang");
        this.memberService.join(member1);

        try {
            this.memberService.join(member2);
        } catch (IllegalStateException var4) {
            var4.printStackTrace();
            return;
        }

        Assertions.fail("중복 확인 못함");
    }

    @Test
    public void check_login_success() {
        Members members1 = new Members();
        members1.setEmail("test1");
        members1.setPwd("testpwd1");
        members1.setRealName("testnick1");
        this.memberService.join(members1);
        Optional result = null;

        try {
            result = this.memberService.Login("test1", "testpwd1");
        } catch (IllegalStateException var4) {
            var4.printStackTrace();
            Assertions.fail("아이디 와 비번이 동시에 같은 데이터값을 찾지 못했습니다");
        }

        System.out.println(result.toString());
    }

    @Test
    public void check_login_fail() {
        Members members1 = new Members();
        members1.setEmail("test1");
        members1.setPwd("testpwd2");
        members1.setRealName("testnickname1");
        this.memberService.join(members1);
        Optional result = null;

        try {
            result = this.memberService.Login("test1", "testpwd1");
        } catch (IllegalStateException var4) {
            var4.printStackTrace();
            return;
        }

        if (!result.isEmpty()) {
            Assertions.fail("아이디 와 비번이 동시에 같은 데이터값을 찾지 못해서 성공했습니다");
        }
    }
}
