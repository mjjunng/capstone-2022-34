package com.capstone.momomeal.domain;

import lombok.Getter;
import lombok.Setter;


/**
 * 채팅 메시지를 주고 받기 위한 DTO
 */
@Getter
@Setter
public class Message {
    public enum MessageType{
        ENTER, SEND
    }

    private MessageType messageType;
    private Long chatRoomId;
    private String sender;
    private String message;
}
