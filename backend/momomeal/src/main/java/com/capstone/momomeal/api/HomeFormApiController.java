package com.capstone.momomeal.api;

import com.capstone.momomeal.repository.MemoryUserRepository;
import java.util.HashMap;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeFormApiController {
    private final MemoryUserRepository memoryUserRepository;

    @ResponseBody
    @RequestMapping
    public HashMap<String, Object> checkLogin() {
        HashMap<String, Object> map = new HashMap();
        map.put("checkdata", this.memoryUserRepository.checkLogin());
        return map;
    }
}
