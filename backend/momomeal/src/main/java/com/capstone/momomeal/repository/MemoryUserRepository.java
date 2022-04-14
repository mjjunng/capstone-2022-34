package com.capstone.momomeal.repository;

import com.capstone.momomeal.domain.MemberForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryUserRepository {
    private static final Logger log = LoggerFactory.getLogger(MemoryUserRepository.class);
    static MemberForm memberForm = new MemberForm();

    public MemoryUserRepository() {
    }

    public void loginSuccess(Long userId, String email, String pwd, String userName) {
        memberForm.setUser_id(userId);
        memberForm.setUserEmail(email);
        memberForm.setPwd(pwd);
        memberForm.setUserName(userName);
        memberForm.setLogin(true);
    }

    public MemberForm getUser() {
        return memberForm;
    }

    public boolean checkLogin() {
        return memberForm.isLogin();
    }
}
