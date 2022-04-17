package com.capstone.momomeal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp/chat")
                .setAllowedOrigins("http://localhost:8080")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // client의 send 요청 처리
        registry.setApplicationDestinationPrefixes("/pub");
        /*  해당 경로로 simpleBroker를 등록. simpleBroker는 해당하는 경로를
            subscribe하는 client에게 메시지 전달하는 간단한 작업 수행
         */
        registry.enableSimpleBroker("/sub");
    }
}
