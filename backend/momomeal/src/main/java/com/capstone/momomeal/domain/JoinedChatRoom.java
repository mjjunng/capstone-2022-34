//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.capstone.momomeal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class JoinedChatRoom {
    @Id
    @GeneratedValue
    @Column(
            name = "joined_chatroom_id"
    )
    private Long id;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "user_id"
    )
    private Members member;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "chatroom_id"
    )
    private ChatRoom chatRoom;
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    public JoinedChatRoom(ChatRoom chatRoom, MemberStatus status) {
        this.chatRoom = chatRoom;
        this.status = status;
    }

    public void setMember(Members member) {
        this.member = member;
        member.getJoinedChatRooms().add(this);
    }

    public Long getId() {
        return this.id;
    }

    public Members getMember() {
        return this.member;
    }

    public ChatRoom getChatRoom() {
        return this.chatRoom;
    }

    public MemberStatus getStatus() {
        return this.status;
    }

    public JoinedChatRoom() {
    }
}
