
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.capstone.momomeal.controller;

import com.capstone.momomeal.domain.MemberForm;
import com.capstone.momomeal.domain.MemberReview;
import com.capstone.momomeal.domain.Members;
import com.capstone.momomeal.repository.MemoryUserRepository;
import com.capstone.momomeal.service.MemberReviewService;
import com.capstone.momomeal.service.MemberService;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    private final MemberService memberService;
    private final MemoryUserRepository memoryUserService;
    private final MemberReviewService memberReviewService;

    @RequestMapping({"/home"})
    public String homeLogout(Model model) {
        log.info("checkLogin = " + this.memoryUserService.checkLogin());
        model.addAttribute("logcheck", this.memoryUserService.checkLogin());
        return "home";
    }

    @RequestMapping({"/home/toLogin"})
    public String toLogin(HttpServletRequest request, HttpServletResponse response, Model model) {
        String userid = request.getParameter("id");
        String pwd = request.getParameter("pwd");
        log.info("userid = {}, pwd = {}", userid, pwd);
        Optional<Members> result = this.memberService.Login(userid, pwd);
        log.info("result =", result.toString());
        if (result != null) {
            this.memoryUserService.loginSuccess(((Members)result.get()).getUser_id(), ((Members)result.get()).getEmail(), ((Members)result.get()).getPwd(), ((Members)result.get()).getRealName());
            List<MemberReview> reviewList = this.memberReviewService.getReviewList(((Members)result.get()).getUser_id());
            MemberForm user = this.memoryUserService.getUser();
            model.addAttribute("member", result);
            model.addAttribute("review", reviewList);
            log.info("homeController MemberForm = {}", user.toString());
            return "profile/failprofile";
        } else {
            return "profile/failprofile";
        }
    }

    public HomeController(final MemberService memberService, final MemoryUserRepository memoryUserService, final MemberReviewService memberReviewService) {
        this.memberService = memberService;
        this.memoryUserService = memoryUserService;
        this.memberReviewService = memberReviewService;
    }

}
