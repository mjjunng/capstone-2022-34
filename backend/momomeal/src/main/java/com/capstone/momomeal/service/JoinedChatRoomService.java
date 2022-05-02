package com.capstone.momomeal.service;

import com.capstone.momomeal.domain.ChatRoom;
import com.capstone.momomeal.domain.JoinedChatRoom;
import com.capstone.momomeal.domain.MemberStatus;
import com.capstone.momomeal.domain.Members;
import com.capstone.momomeal.repository.JoinedChatRoomRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class JoinedChatRoomService {
    private final JoinedChatRoomRepository joinedChatRoomRepository;


    /**
     * 참여한 채팅방 저장 메서드
     * @param joinedChatRoom 저장할 참여한 채팅방 object
     * @return  저장한 참여 채팅방 id값
     */
    public Long save(JoinedChatRoom joinedChatRoom){
        joinedChatRoomRepository.save(joinedChatRoom);
        return joinedChatRoom.getId();
    }


    /**
     * 참여한 채팅방 생성 메서드 - 호스트가 아닌 사용자가 해당 채팅방에 참여하는 메서드이다.
     * @param member 참여 요청을 한 member
     * @param chatRoom member가 참여하려는 chatRoom
     * @return 생성한 joinedChatRoom id
     */
    public Long createJoinedChatRoom(Members member, ChatRoom chatRoom){
        JoinedChatRoom joinedChatRoom = new JoinedChatRoom(chatRoom, MemberStatus.MEMBER);
        joinedChatRoom.setMember(member);
        save(joinedChatRoom);
        return joinedChatRoom.getId();

    }

    /**
     * 참여한 채팅방(JoinedChatRoom) 삭제 - JoinChatRoom만 DB에서 삭제
     * @param joinedChatRoomId
     */
    public int delete(Long joinedChatRoomId){
        return joinedChatRoomRepository.deleteById(joinedChatRoomId); // JoinedChatRoomId 삭제
    }

    /**
     * memberId와 chatRoomId를 통해 해당 회원이 해당 채팅방에 참여하고 있는 JoinedChatRoom 반환
     * @param member 삭제를 요청한 member
     * @param chatRoom  삭제를 요청받은 chatRoom-(chatRoom이 삭제X joinedChatRoom이 삭제)
     * @return 삭제할 joinedChatRoom 객체
     */
    @Transactional(readOnly = true)
    public JoinedChatRoom findByMemberIdAndChatRoomId(Members member, ChatRoom chatRoom){
        return joinedChatRoomRepository.findByMemberIdAndChatRoomId(member, chatRoom);
    }

    public List<JoinedChatRoom> findByChatRoom(ChatRoom chatRoom){
        return joinedChatRoomRepository.findByChatRoom(chatRoom);
    }

    public int countByChatRoom(ChatRoom chatRoom){
        int cnt = 0;
        List byChatRoom = findByChatRoom(chatRoom);

        if (byChatRoom != null) cnt = byChatRoom.size();


        return cnt;
    }
}