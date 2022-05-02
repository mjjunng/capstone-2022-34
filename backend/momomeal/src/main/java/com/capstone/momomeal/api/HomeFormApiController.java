package com.capstone.momomeal.api;

import com.capstone.momomeal.repository.MemoryUserRepository;
import java.util.HashMap;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeFormApiController {
    private final MemoryUserRepository memoryUserRepository;

    @ResponseBody
    @RequestMapping(value = "checkLogin.do", method = RequestMethod.GET)
    public HashMap<String, Object> checkLogin() {
        HashMap<String, Object> map = new HashMap();
        boolean check = memoryUserRepository.checkLogin();
        map.put("check", check);
        return map;
    }


}
