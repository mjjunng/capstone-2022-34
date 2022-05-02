//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.capstone.momomeal.domain;

public class MemberForm {
    Long user_id = null;
    String userEmail = null;
    String userName = null;
    String pwd = null;
    boolean login = false;

    public MemberForm() {
    }

    public void setUser_id(final Long user_id) {
        this.user_id = user_id;
    }

    public void setUserEmail(final String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public void setPwd(final String pwd) {
        this.pwd = pwd;
    }

    public void setLogin(final boolean login) {
        this.login = login;
    }

    public Long getUser_id() {
        return this.user_id;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPwd() {
        return this.pwd;
    }

    public boolean isLogin() {
        return this.login;
    }

    public String toString() {
        Long var10000 = this.getUser_id();
        return "MemberForm(user_id=" + var10000 + ", userEmail=" + this.getUserEmail() + ", userName=" + this.getUserName() + ", pwd=" + this.getPwd() + ", login=" + this.isLogin() + ")";
    }
}
