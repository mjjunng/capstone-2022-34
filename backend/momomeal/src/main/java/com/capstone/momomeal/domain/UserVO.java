package com.capstone.momomeal.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserVO {
    String userId = null;
    String userName = null;
    String pwd = null;
    boolean login = false;

    public UserVO(){
        userId = null;
        userName = null;
        pwd = null;
        login = false;
    }
}
