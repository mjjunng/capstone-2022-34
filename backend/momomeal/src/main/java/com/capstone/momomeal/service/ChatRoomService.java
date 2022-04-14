//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.capstone.momomeal.service;

import com.capstone.momomeal.domain.Category;
import com.capstone.momomeal.domain.ChatRoom;
import com.capstone.momomeal.domain.ChatRoomRequestDTO;
import com.capstone.momomeal.domain.JoinedChatRoom;
import com.capstone.momomeal.domain.MemberStatus;
import com.capstone.momomeal.domain.Members;
import com.capstone.momomeal.domain.TransStringToEnum;
import com.capstone.momomeal.repository.ChatRoomRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(
        readOnly = true
)
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final JoinedChatRoomService joinedChatRoomService;

    @Transactional
    public Long save(ChatRoom chatRoom) {
        this.chatRoomRepository.save(chatRoom);
        return chatRoom.getId();
    }

    @Transactional
    public Long createChatRoom(Members member, ChatRoomRequestDTO requestDTO) {
        TransStringToEnum te = new TransStringToEnum();
        Category category = te.transferStringToEnum(requestDTO.getCategoryName());
        ChatRoom chatRoom = new ChatRoom(category, requestDTO.getTitle(), requestDTO.getHostId(), requestDTO.getMaxCapacity(), requestDTO.getStoreName(), requestDTO.getPickupPlaceName(), requestDTO.getPickupPlaceXCoord(), requestDTO.getPickupPlaceYCoord());
        this.save(chatRoom);
        JoinedChatRoom joinedChatRoom = new JoinedChatRoom(chatRoom, MemberStatus.HOST);
        joinedChatRoom.setMember(member);
        this.joinedChatRoomService.save(joinedChatRoom);
        return chatRoom.getId();
    }

    public ChatRoom findById(Long id) {
        return this.chatRoomRepository.findById(id);
    }

    public List<ChatRoom> findAll() {
        return this.chatRoomRepository.findAll();
    }

    public List<ChatRoom> findExceptParticipatedChatRoom(List<Long> participatedChatRoomIds) {
        return this.chatRoomRepository.findExceptParticipatedChatRoom(participatedChatRoomIds);
    }

    @Transactional
    public int delete(Long chatRoomId) {
        return this.chatRoomRepository.deleteById(chatRoomId);
    }

    public ChatRoomService(final ChatRoomRepository chatRoomRepository, final JoinedChatRoomService joinedChatRoomService) {
        this.chatRoomRepository = chatRoomRepository;
        this.joinedChatRoomService = joinedChatRoomService;
    }
}
