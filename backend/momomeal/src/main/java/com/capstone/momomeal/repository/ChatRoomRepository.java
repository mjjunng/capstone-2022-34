
package com.capstone.momomeal.repository;

import com.capstone.momomeal.domain.ChatRoom;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class ChatRoomRepository {
    private final EntityManager em;

    public void save(ChatRoom chatRoom) {
        this.em.persist(chatRoom);
    }

    public ChatRoom findById(Long chatRoomId) {
        return (ChatRoom)this.em.find(ChatRoom.class, chatRoomId);
    }

    public List<ChatRoom> findAll() {
        return this.em.createQuery("select cr from ChatRoom cr", ChatRoom.class).getResultList();
    }

    public List<ChatRoom> findExceptParticipatedChatRoom(List<Long> participatedChatRoomIds) {
        return this.em.createQuery("select cr from ChatRoom cr where cr.id not in :ids").setParameter("ids", participatedChatRoomIds).getResultList();
    }

    public int deleteById(Long chatRoomId) {
        int cnt = this.em.createQuery("delete from ChatRoom cr where cr.id = :id").setParameter("id", chatRoomId).executeUpdate();
        this.em.clear();
        return cnt;
    }

    public ChatRoomRepository(final EntityManager em) {
        this.em = em;
    }
}
