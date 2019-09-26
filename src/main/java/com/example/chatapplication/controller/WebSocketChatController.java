package com.example.chatapplication.controller;

import com.example.chatapplication.domain.WebSocketChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/chatapplication")
    public WebSocketChatMessage sendMessage(@Payload WebSocketChatMessage webSocketChatMessage){
        return webSocketChatMessage;
    }

    @MessageMapping("/chat.newUser")
    @SendTo("/topic/chatapplication")
    public WebSocketChatMessage newUser(@Payload WebSocketChatMessage webSocketChatMessage,
                                        SimpMessageHeaderAccessor simpMessageHeaderAccessor){
        simpMessageHeaderAccessor.getSessionAttributes().put("username",webSocketChatMessage.getSender());
        return webSocketChatMessage;
    }
}
