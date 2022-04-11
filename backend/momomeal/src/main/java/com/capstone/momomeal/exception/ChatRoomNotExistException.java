package com.capstone.momomeal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid chatroomId")
public class ChatRoomNotExistException extends RuntimeException{
}
