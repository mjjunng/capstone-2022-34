//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.capstone.momomeal.service;

import com.capstone.momomeal.domain.MemberReview;
import com.capstone.momomeal.domain.Members;
import com.capstone.momomeal.repository.MemberRepository;
import com.capstone.momomeal.repository.MemoryUserRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class CheckModifyMemberService {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberReviewService memberReviewService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemoryUserRepository memoryUserRepository;

    public CheckModifyMemberService() {
    }

    @Test
//    @Rollback(value = false)
    void createAccount() {
        Members member1 = new Members();
        Members member2 = new Members();
        Members member3 = new Members();
        member1.setEmail("test1@email.com");
        member1.setPwd("123123");
        member1.setRealName("Kim");
        member2.setEmail("member2Id");
        member2.setPwd("123123");
        member2.setRealName("kang");
        member3.setEmail("easy");
        member3.setPwd("qwer");
        member3.setRealName("Unknown");
        this.memberService.join(member1);
        this.memberService.join(member2);
        this.memberService.join(member3);
    }

    @Test
    public void test_duplicate() {
        Members member = new Members();
        member.setEmail("test1@email.com");
        member.setPwd("123123");
        member.setRealName("Kim");
        Integer length = this.memberRepository.findAll().size();
        System.out.println("length = " + length);

        try {
            this.memberService.join(member);
        } catch (IllegalStateException var4) {
            var4.printStackTrace();
            return;
        }

        Assertions.fail("중복 존재");
    }

    @Test
    public void Test_Login() {
        Members member = new Members();
        member.setEmail("test1@email.com");
        member.setPwd("123123");
        member.setRealName("Kim");
        Optional<Members> result = this.memberService.Login(member.getEmail(), member.getPwd());
        if (result == null) {
            Assertions.fail("로그인 실패");
        }

    }

    @Test
    public void get_Review_Result() {
        Optional<Members> members = this.memberService.Login("test1@email.com", "123123");
        Members member = (Members)members.get();
        MemberReview memberReview1 = new MemberReview();
        memberReview1.setMember(member);
        memberReview1.Bad();
        memberReview1.setReview_text("맛있어요");
        MemberReview memberReview2 = new MemberReview();
        memberReview2.setMember(member);
        memberReview2.Like();
        memberReview2.setReview_text("맛없어요");
        this.memberReviewService.addReview(memberReview1);
        this.memberReviewService.addReview(memberReview2);
        List<MemberReview> result = this.memberReviewService.getReviewList(member.getUser_id());
        System.out.println("result size : " + result.size());
    }

    @Test
    @Rollback(value = false)
    public void update_User_Info(){
        Long userId = Long.valueOf(1);
        String email = "change@email.com";
        String realName = "Yeon";
        Boolean check = memberService.updateUser(userId, email, realName);
        if(check == true){
            return;
        }else{
            Assertions.fail("업데이트 실패");
        }
    }
}
