package com.capstone.momomeal.domain;
import lombok.Data;

/**
 * 안드로이드로에서 받는 채팅방 데이터
 */

@Data
public class ChatRoomRequestDTO {
    private String categoryName;
    private String title;
    private Long hostId;
    private int maxCapacity;
    private String storeName;
    private String pickupPlaceName;
    private double pickupPlaceXCoord;
    private double pickupPlaceYCoord;
}