package com.capstone.momomeal.controller;

import com.capstone.momomeal.domain.chatting.ChatMessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class StompChatController {
    private final SimpMessagingTemplate template;

    /*
    클라이언트가 SEND할 수 있는 경로
    stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping
    경로가 병합됨
    /pub/chat/enter
     */
    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMessageDTO message){
        message.setMessage(message.getWriter() + "님이 채팅방에 참여하였습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getChatRoodId(), message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageDTO message){
        template.convertAndSend("/sub/chat/room/" + message.getChatRoodId(), message);
    }
}
