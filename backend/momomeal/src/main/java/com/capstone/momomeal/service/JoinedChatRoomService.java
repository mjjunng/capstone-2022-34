package com.capstone.momomeal.service;

import com.capstone.momomeal.domain.ChatRoom;
import com.capstone.momomeal.domain.JoinedChatRoom;
import com.capstone.momomeal.domain.MemberStatus;
import com.capstone.momomeal.domain.Members;
import com.capstone.momomeal.repository.JoinedChatRoomRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JoinedChatRoomService {
    private final JoinedChatRoomRepository joinedChatRoomRepository;

    public Long save(JoinedChatRoom joinedChatRoom) {
        this.joinedChatRoomRepository.save(joinedChatRoom);
        return joinedChatRoom.getId();
    }

    public Long createJoinedChatRoom(Members member, ChatRoom chatRoom) {
        JoinedChatRoom joinedChatRoom = new JoinedChatRoom(chatRoom, MemberStatus.MEMBER);
        joinedChatRoom.setMember(member);
        this.save(joinedChatRoom);
        return joinedChatRoom.getId();
    }

    public int delete(Long joinedChatRoomId) {
        return this.joinedChatRoomRepository.deleteById(joinedChatRoomId);
    }

    @Transactional(
            readOnly = true
    )
    public JoinedChatRoom findByMemberIdAndChatRoomId(Members member, ChatRoom chatRoom) {
        return this.joinedChatRoomRepository.findByMemberIdAndChatRoomId(member, chatRoom);
    }

    public int countByChatRoom(ChatRoom chatRoom) {
        int cnt = 0;
        List byChatRoom = this.joinedChatRoomRepository.findByChatRoom(chatRoom);
        if (byChatRoom != null) {
            cnt = byChatRoom.size();
        }

        return cnt;
    }

    public JoinedChatRoomService(final JoinedChatRoomRepository joinedChatRoomRepository) {
        this.joinedChatRoomRepository = joinedChatRoomRepository;
    }
}
