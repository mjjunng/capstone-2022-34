package com.capstone.momomeal.repository;

import com.capstone.momomeal.domain.ChatRoom;
import com.capstone.momomeal.domain.JoinedChatRoom;
import com.capstone.momomeal.domain.Members;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class JoinedChatRoomRepository {
    private final EntityManager em;

    public void save(JoinedChatRoom joinedChatRoom) {
        this.em.persist(joinedChatRoom);
    }

    public int deleteById(Long joinedChatRoomId) {
        int cnt = this.em.createQuery("delete from JoinedChatRoom jcr where jcr.id = :id").setParameter("id", joinedChatRoomId).executeUpdate();
        this.em.clear();
        return cnt;
    }

    public JoinedChatRoom findByMemberIdAndChatRoomId(Members member, ChatRoom chatRoom) {
        return (JoinedChatRoom)this.em.createQuery("select jcr from JoinedChatRoom jcr where jcr.member = :m and jcr.chatRoom = :cr", JoinedChatRoom.class).setParameter("m", member).setParameter("cr", chatRoom).getSingleResult();
    }

    public List findByChatRoom(ChatRoom chatRoom) {
        return this.em.createQuery("select jcr from JoinedChatRoom jcr where jcr.chatRoom = :cr").setParameter("cr", chatRoom).getResultList();
    }

    public JoinedChatRoomRepository(final EntityManager em) {
        this.em = em;
    }
}
