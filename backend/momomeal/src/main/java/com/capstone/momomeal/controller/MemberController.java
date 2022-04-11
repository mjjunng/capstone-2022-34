package com.capstone.momomeal.controller;

import com.capstone.momomeal.SpringConfig;
import com.capstone.momomeal.domain.Members;
import com.capstone.momomeal.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
public class MemberController {

    //ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
    private final MemberService memberService;//= applicationContext.getBean("memberService",MemberService.class);

    //생성자 주입
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    //회원가입 권유 화면
    @RequestMapping(value = "/members/new", method = RequestMethod.GET)
    public String createForm(){
        return "members/SignUpForm";
    }

    //회원가입 창 띄우기
    @RequestMapping(value = "/members/signup", method = RequestMethod.GET)
    public String loginForm(){return "members/createAccountForm";}

    //회원가입하기 -> 바로 메인화면
    @RequestMapping(value = "/members/createAccount",method = RequestMethod.POST)
    public String create(HttpServletRequest request){
        Members memberDTO = new Members();

        String userId = request.getParameter("userId");
        String pwd = request.getParameter("pwd");
        String userName = request.getParameter("userName");

        memberDTO.setUserid(userId);memberDTO.setPwd(pwd);memberDTO.setUsername(userName);

        log.info("controller UserId = {}, UserPwd = {}, UserName = {}",
                memberDTO.getUserid(), memberDTO.getPwd(),memberDTO.getUsername());

        memberService.join(memberDTO);
        return "redirect:/home";
    }

    @RequestMapping(value = "/members", method = RequestMethod.GET)
    public String saveForm(Model model){

        List<Members> listdata = memberService.findAll();

        for(Members data : listdata){
            log.info("member name = {} , nickname = {}, Password = {}",data.getUsername()
            , data.getNickname(), data.getPwd());
        }

        model.addAttribute("listdata",listdata);

        return "members/memberList";
    }
}
