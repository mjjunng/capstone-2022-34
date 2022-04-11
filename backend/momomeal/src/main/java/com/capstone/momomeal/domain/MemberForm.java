//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.capstone.momomeal.domain;

import lombok.Data;
import lombok.Getter;

@Data
public class MemberForm {
    Long user_id = null;
    String userEmail = null;
    String userName = null;
    String pwd = null;
    boolean login = false;

    public MemberForm() {
    }
}
