package com.capstone.momomeal.controller;

import com.capstone.momomeal.SpringConfig;
import com.capstone.momomeal.domain.Members;
import com.capstone.momomeal.domain.UserVO;
import com.capstone.momomeal.repository.MemoryUserRepository;
import com.capstone.momomeal.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
public class ProfileController {

    // applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
    private final MemberService memberService;//= applicationContext.getBean("memberService",MemberService.class);
    private final MemoryUserRepository UserService;// = applicationContext.getBean("memoryUserService", MemoryUserRepository.class);

    //생성자 주입
    @Autowired
    public ProfileController(MemberService memberService, MemoryUserRepository userService){
        this.memberService = memberService;
        this.UserService = userService;
    }

    //메인 프로필
    @RequestMapping("/profile/home")
    public String newForm(Model model){
        UserVO user = UserService.getUser();
        log.info("user = {}",user.toString());
        Optional<Members> member = memberService.findOne(user.getUserId());
        log.info("member = {}",member.toString());
        model.addAttribute("member",member);
        return "profile/homeprofile";
    }

    //프로필 편집
    @RequestMapping("/profile/edit")
    public String userProfile(Model model){

        return "editprofile";
    }

}
