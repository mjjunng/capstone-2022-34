package com.capstone.momomeal.api;

import com.capstone.momomeal.domain.ChatRoom;
import com.capstone.momomeal.service.ChatRoomService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class SearchApiController {
    private final ChatRoomService chatRoomService;

    @GetMapping("/searched-chat-list/{keyword}")
    public List<ChatRoomListDto> getSearchChatRoomList(@PathVariable String keyword){

        List<ChatRoom> searchedChatRooms = chatRoomService.getSearchedChatRooms(keyword);

        // 검색한 채팅방의 dto만 뽑음
        List<ChatRoomListDto> result = searchedChatRooms
                .stream().map(c -> new ChatRoomListDto(c))
                .collect(Collectors.toList());
        return result;
    }

    @Data
    @AllArgsConstructor
    static class ChatRoomListDto{
        private Long id;
        private String title;
        private String pickupPlaceName;
        private LocalDateTime createdDate;
        private int distance;

        public ChatRoomListDto(ChatRoom chatRoom) {
            this.id = chatRoom.getId();
            this.title = chatRoom.getTitle();
            this.pickupPlaceName = chatRoom.getPickupPlaceName();
            this.createdDate = chatRoom.getCreatedDate();
            this.distance = getDistance();
        }
    }
}
