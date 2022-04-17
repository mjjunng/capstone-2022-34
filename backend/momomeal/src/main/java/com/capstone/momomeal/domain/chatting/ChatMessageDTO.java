package com.capstone.momomeal.domain.chatting;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDTO {
    private String chatRoodId;
    private String writer;
    private String message;
}
