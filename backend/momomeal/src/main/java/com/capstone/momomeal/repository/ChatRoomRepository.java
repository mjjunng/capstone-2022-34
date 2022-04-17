package com.capstone.momomeal.repository;

import com.capstone.momomeal.domain.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatRoomRepository {

    private final EntityManager em;

    public void save(ChatRoom chatRoom){
        em.persist(chatRoom);
    }

    public ChatRoom findById(Long chatRoomId){
        return em.find(ChatRoom.class, chatRoomId);
    }

    public List<ChatRoom> findAll(){
        return em.createQuery("select cr from ChatRoom cr", ChatRoom.class)
                .getResultList();
    }


    public List<ChatRoom> findAllOrderByTime(){
        return em.createQuery("select cr from ChatRoom cr " +
                        "order by cr.createdDate desc", ChatRoom.class)
                .getResultList();
    }

    public List<ChatRoom> findAllOrderByDistance(){
        return em.createQuery("select cr from ChatRoom cr " +
                "order by cr.distance", ChatRoom.class)
                .getResultList();
    }

    public List<ChatRoom> findExceptParticipatedChatRoom(List<Long> participatedChatRoomIds){
        return em.createQuery("select cr from ChatRoom cr" +
                        " where cr.id not in :ids", ChatRoom.class)
                .setParameter("ids", participatedChatRoomIds)
                .getResultList();

    }
    public List<ChatRoom> findExceptParticipatedChatRoomOrderByTime(List<Long> participatedChatRoomIds){
        return em.createQuery("select cr from ChatRoom cr" +
                        " where cr.id not in :ids " +
                        "order by cr.createdDate desc", ChatRoom.class)
                .setParameter("ids", participatedChatRoomIds)
                .getResultList();

    }

    public List<ChatRoom> findExceptParticipatedChatRoomOrderByDistance(List<Long> participatedChatRoomIds){
        return em.createQuery("select cr from ChatRoom cr " +
                "where cr.id not in :ids " +
                "order by cr.distance", ChatRoom.class)
                .setParameter("ids", participatedChatRoomIds)
                .getResultList();
    }

    public int deleteById(Long chatRoomId){
        int cnt = em.createQuery("delete from ChatRoom cr" +
                        " where cr.id = :id")
                .setParameter("id", chatRoomId)
                .executeUpdate();// delete 되는 레코드 수
        em.clear();
        return cnt;

    }

    public List<ChatRoom> findByKeyword(String keyword){
        return em.createQuery("select cr from ChatRoom cr " +
                        "where cr.title like concat('%',:keyword,'%')", ChatRoom.class)
                .setParameter("keyword", keyword)
                .getResultList();
    }
}