package com.capstone.momomeal.repository;

import com.capstone.momomeal.domain.ChatRoom;
import com.capstone.momomeal.domain.JoinedChatRoom;
import com.capstone.momomeal.domain.Members;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JoinedChatRoomRepository{
    private final EntityManager em;

    public void save(JoinedChatRoom joinedChatRoom){
        em.persist(joinedChatRoom);
    }

    public int deleteById(Long joinedChatRoomId){
        int cnt = em.createQuery("delete from JoinedChatRoom jcr" +
                        " where jcr.id = :id")
                .setParameter("id", joinedChatRoomId)
                .executeUpdate();// delete 되는 레코드 수
        em.clear();
        return cnt;

    }

    public JoinedChatRoom findByMemberIdAndChatRoomId(Members member, ChatRoom chatRoom){
        return em.createQuery("select jcr from JoinedChatRoom jcr " +
                "where jcr.member = :m and jcr.chatRoom = :cr", JoinedChatRoom.class)
                .setParameter("m", member)
                .setParameter("cr", chatRoom)
                .getSingleResult();
    }

    public List findByChatRoom(ChatRoom chatRoom){
        return em.createQuery("select jcr from JoinedChatRoom jcr " +
                        "where jcr.chatRoom = :cr")
                .setParameter("cr", chatRoom)
                .getResultList();
    }

}
