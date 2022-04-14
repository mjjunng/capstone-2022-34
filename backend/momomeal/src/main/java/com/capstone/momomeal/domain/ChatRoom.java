//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.capstone.momomeal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ChatRoom extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(
            name = "chatroom_id"
    )
    private Long id;
    @Enumerated(EnumType.STRING)
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

    public ChatRoom(Category category, String title, String hostId, int maxCapacity, String storeName, String pickupPlaceName, double pickupPlaceXCoord, double pickupPlaceYCoord) {
        this.category = category;
        this.title = title;
        this.hostId = hostId;
        this.maxCapacity = maxCapacity;
        this.storeName = storeName;
        this.pickupPlaceName = pickupPlaceName;
        this.pickupPlaceXCoord = pickupPlaceXCoord;
        this.pickupPlaceYCoord = pickupPlaceYCoord;
    }

    public static ChatRoom.ChatRoomBuilder builder() {
        return new ChatRoom.ChatRoomBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public Category getCategory() {
        return this.category;
    }

    public String getTitle() {
        return this.title;
    }

    public String getHostId() {
        return this.hostId;
    }

    public int getMaxCapacity() {
        return this.maxCapacity;
    }

    public String getStoreName() {
        return this.storeName;
    }

    public String getPickupPlaceName() {
        return this.pickupPlaceName;
    }

    public double getPickupPlaceXCoord() {
        return this.pickupPlaceXCoord;
    }

    public double getPickupPlaceYCoord() {
        return this.pickupPlaceYCoord;
    }

    public ChatRoom(final Long id, final Category category, final String title, final String hostId, final int maxCapacity, final String storeName, final String pickupPlaceName, final double pickupPlaceXCoord, final double pickupPlaceYCoord) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.hostId = hostId;
        this.maxCapacity = maxCapacity;
        this.storeName = storeName;
        this.pickupPlaceName = pickupPlaceName;
        this.pickupPlaceXCoord = pickupPlaceXCoord;
        this.pickupPlaceYCoord = pickupPlaceYCoord;
    }

    public static class ChatRoomBuilder {
        private Long id;
        private Category category;
        private String title;
        private String hostId;
        private int maxCapacity;
        private String storeName;
        private String pickupPlaceName;
        private double pickupPlaceXCoord;
        private double pickupPlaceYCoord;

        ChatRoomBuilder() {
        }

        public ChatRoom.ChatRoomBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public ChatRoom.ChatRoomBuilder category(final Category category) {
            this.category = category;
            return this;
        }

        public ChatRoom.ChatRoomBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public ChatRoom.ChatRoomBuilder hostId(final String hostId) {
            this.hostId = hostId;
            return this;
        }

        public ChatRoom.ChatRoomBuilder maxCapacity(final int maxCapacity) {
            this.maxCapacity = maxCapacity;
            return this;
        }

        public ChatRoom.ChatRoomBuilder storeName(final String storeName) {
            this.storeName = storeName;
            return this;
        }

        public ChatRoom.ChatRoomBuilder pickupPlaceName(final String pickupPlaceName) {
            this.pickupPlaceName = pickupPlaceName;
            return this;
        }

        public ChatRoom.ChatRoomBuilder pickupPlaceXCoord(final double pickupPlaceXCoord) {
            this.pickupPlaceXCoord = pickupPlaceXCoord;
            return this;
        }

        public ChatRoom.ChatRoomBuilder pickupPlaceYCoord(final double pickupPlaceYCoord) {
            this.pickupPlaceYCoord = pickupPlaceYCoord;
            return this;
        }

        public ChatRoom build() {
            return new ChatRoom(this.id, this.category, this.title, this.hostId, this.maxCapacity, this.storeName, this.pickupPlaceName, this.pickupPlaceXCoord, this.pickupPlaceYCoord);
        }

        public String toString() {
            return "ChatRoom.ChatRoomBuilder(id=" + this.id + ", category=" + this.category + ", title=" + this.title + ", hostId=" + this.hostId + ", maxCapacity=" + this.maxCapacity + ", storeName=" + this.storeName + ", pickupPlaceName=" + this.pickupPlaceName + ", pickupPlaceXCoord=" + this.pickupPlaceXCoord + ", pickupPlaceYCoord=" + this.pickupPlaceYCoord + ")";
        }
    }
}
