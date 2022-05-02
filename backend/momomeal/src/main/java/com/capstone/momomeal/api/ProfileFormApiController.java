//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.capstone.momomeal.api;

import com.capstone.momomeal.domain.Members;
import com.capstone.momomeal.service.MemberReviewService;
import com.capstone.momomeal.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;
import java.util.OptionalInt;

@RestController
@RequiredArgsConstructor
public class ProfileFormApiController {

    private final MemberReviewService memberReviewService;
    private final MemberService memberService;

    @ResponseBody
    @RequestMapping(value = "getUserInfo.do", method = RequestMethod.POST)
    public HashMap<String, Object> getUserInfo(@RequestBody HashMap<String, Object> map){
        HashMap<String, Object> returnData = new HashMap<>();

        Long user_id = (Long)map.get("user_id");

        Optional<Members> c_member = memberService.findById(user_id);
        if(c_member.equals(null)){
            returnData.put("fail",0);
            return  returnData;
        }
        Members member = c_member.get();

        returnData.put("name",member.getRealName());
        returnData.put("email",member.getEmail());
        returnData.put("img_url",member.getImg());
        //returnData.put("int",member.TotalRate());

        return returnData;
    }

    @ResponseBody
    @RequestMapping(value = "curtUserInfo.do",method = RequestMethod.POST)
    public HashMap<String, Object> curtUserInfo(@RequestBody HashMap<String, Object> map){
        HashMap<String, Object> returnData = new HashMap<>();

        Long user_id = (Long)map.get("user_id");

        Optional<Members> c_member = memberService.findById(user_id);
        if(c_member.equals(null)){
            returnData.put("fail",0);
            return  returnData;
        }
        Members member = c_member.get();

        returnData.put("name",member.getRealName());
        returnData.put("img_url",member.getImg());

        return returnData;
    }

}
