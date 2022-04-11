package com.capstone.momomeal.domain;

import com.capstone.momomeal.service.MsgService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.web.socket.WebSocketSession;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class ChatRoom extends BaseTimeEntity{
    @Id @GeneratedValue
    @Column(name = "chatroom_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Category category;
    private String title;
    private String hostId;
    private int maxCapacity;
    private String storeName;
    private String pickupPlaceName;
    private double pickupPlaceXCoord;
    private double pickupPlaceYCoord;

    public ChatRoom() {
    }


    public ChatRoom(Category category, String title, String hostId, int maxCapacity,
                    String storeName, String pickupPlaceName, double pickupPlaceXCoord,
                    double pickupPlaceYCoord) {
        this.category = category;
        this.title = title;
        this.hostId = hostId;
        this.maxCapacity = maxCapacity;
        this.storeName = storeName;
        this.pickupPlaceName = pickupPlaceName;
        this.pickupPlaceXCoord = pickupPlaceXCoord;
        this.pickupPlaceYCoord = pickupPlaceYCoord;
    }
    // websocket-채팅
//    public void handleActions(WebSocketSession session, Message message, MsgService msgService){
//        if (message.getMessageType().equals(Message.MessageType.ENTER)) {
//            sessions.add(session);
//            message.setMessage(message.getSender() + "님이 입장했습니다.");
//        }
//        sendMessage(message, msgService);
//    }
//
//    public <T> void sendMessage(T message, MsgService msgService) {
//        sessions.parallelStream().forEach(session -> msgService.sendMessage(session, message));
//    }
}
