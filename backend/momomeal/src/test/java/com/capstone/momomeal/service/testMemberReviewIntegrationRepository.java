//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.capstone.momomeal.service;

import com.capstone.momomeal.domain.MemberForm;
import com.capstone.momomeal.domain.MemberReview;
import com.capstone.momomeal.domain.Members;
import com.capstone.momomeal.repository.MemberReviewRepository;
import com.capstone.momomeal.repository.MemoryUserRepository;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class testMemberReviewIntegrationRepository {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberReviewRepository memberReviewRepository;
    @Autowired
    MemoryUserRepository memoryUserRepository;
    @Autowired
    MemberReviewService memberReviewService;

    public testMemberReviewIntegrationRepository() {
    }

    @Test
    public void addReview() {
        Members member1 = new Members();
        member1.setEmail("springTest3");
        member1.setPwd("springpwd3");
        member1.setRealName("springName3");
        MemberReview memberReview1 = new MemberReview();
        memberReview1.setMember(member1);
        memberReview1.setReview_text("맛있어요");
        MemberReview memberReview2 = new MemberReview();
        memberReview2.setMember(member1);
        memberReview2.setReview_text("맛없어요");
        Optional<Members> members = this.memberService.Login("springTest3", "springpwd3");
        System.out.println(members.toString());
        MemberForm memberForm = this.memoryUserRepository.getUser();
        if (memberForm.isLogin()) {
            this.memberReviewRepository.saveReview(memberReview1);
            this.memberReviewRepository.saveReview(memberReview2);
        }

    }

    @Test
    public void getReviewList() {
        Members member1 = new Members();
        member1.setEmail("springTest3");
        member1.setPwd("springpwd3");
        member1.setRealName("springName3");
        MemberReview memberReview1 = new MemberReview();
        memberReview1.setMember(member1);
        memberReview1.Like();
        memberReview1.setReview_text("맛있어요");
        MemberReview memberReview2 = new MemberReview();
        memberReview2.setMember(member1);
        memberReview2.Bad();
        memberReview2.setReview_text("맛없어요");
        MemberReview memberReview3 = new MemberReview();
        memberReview3.setMember(member1);
        memberReview3.Like();
        memberReview3.setReview_text("맛있어요");
        this.memberReviewService.addReview(memberReview1);
        this.memberReviewService.addReview(memberReview2);
        this.memberReviewService.addReview(memberReview3);
        List<MemberReview> reviewList = this.memberReviewService.getReviewList(member1.getUser_id());
        Iterator var6 = reviewList.iterator();

        while(var6.hasNext()) {
            MemberReview review = (MemberReview)var6.next();
            System.out.println(review.toString());
        }

    }
}
