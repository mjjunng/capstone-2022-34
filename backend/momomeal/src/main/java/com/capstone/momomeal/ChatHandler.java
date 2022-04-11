package com.capstone.momomeal;

import com.capstone.momomeal.domain.ChatRoom;
import com.capstone.momomeal.domain.Message;
import com.capstone.momomeal.service.ChatRoomService;
import com.capstone.momomeal.service.MsgService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class ChatHandler extends TextWebSocketHandler {
//    private static List<WebSocketSession> list = new ArrayList<>();
//    private final MsgService msgService;
//    private final ObjectMapper objectMapper;
//    private final ChatRoomService chatRoomService;
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        String payload = message.getPayload();
//        log.info("playload: " + payload);
//
////        for (WebSocketSession webSocketSession : list) {
////            webSocketSession.sendMessage(message);
////        }
//        Message msg = objectMapper.readValue(payload, Message.class);
//        ChatRoom chatRoom = chatRoomService.findById(msg.getChatRoomId());
//        chatRoom.handleActions(session, msg, msgService);
//    }
//
//    /**
//     * 클라이언트 접속 시 호출되는 메서드
//     */
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        list.add(session);
//        log.info(session + "클라이언트 접속");
//    }
//
//    /**
//     * 클라이언트 접속 해제 시 호출되는 메서드
//     */
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        log.info(session + " 클라이언트 접속 해제");
//        list.remove(session);
//    }
}
