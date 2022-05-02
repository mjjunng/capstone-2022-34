package com.capstone.momomeal.api;

import com.capstone.momomeal.domain.MemberForm;
import com.capstone.momomeal.domain.Members;
import com.capstone.momomeal.repository.MemoryUserRepository;
import com.capstone.momomeal.service.MemberService;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginFormApiController {
    private final MemberService memberService;
    private final MemoryUserRepository memoryUserRepository;

    @PostMapping({"/creatAccount"})
    public String CreateAccount(@RequestParam("email") String email, @RequestParam("pwd") String pwd, @RequestParam("Rname") String Rname) {
        MemberForm memberForm = new MemberForm();
        memberForm.setUserEmail(email);
        memberForm.setPwd(pwd);
        memberForm.setUserName(Rname);
        Members member = new Members();
        member.setEmail(email);
        member.setPwd(pwd);
        member.setRealName(Rname);

        try {
            this.memberService.join(member);
            return "";
        } catch (IllegalStateException var7) {
            throw new IllegalStateException("중복아이디 존재");
        }
    }

    @ResponseBody
    @PostMapping({"/login"})
    public String login(@PathVariable("email") String email, @PathVariable("pwd") String pwd) {
        Members member = new Members();
        member.setEmail(email);
        member.setPwd(pwd);
        Optional<Members> members = this.memberService.Login(email, pwd);
        return members.equals((Object)null) ? "/fail" : "";
    }
}
