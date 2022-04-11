package com.capstone.momomeal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 * 하나의 세션에 메시지 발송하는 서비스 구현
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class MsgService {

    private final ObjectMapper objectMapper;

    public <T> void sendMessage(WebSocketSession session, T message){
        try{
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e){
            log.error(e.getMessage(), e);
        }
    }
}
