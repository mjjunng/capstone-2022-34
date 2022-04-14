//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.capstone.momomeal.domain;

public class ChatRoomRequestDTO {
    private String categoryName;
    private String title;
    private String hostId;
    private int maxCapacity;
    private String storeName;
    private String pickupPlaceName;
    private double pickupPlaceXCoord;
    private double pickupPlaceYCoord;

    public ChatRoomRequestDTO() {
    }

    public String getCategoryName() {
        return this.categoryName;
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

    public void setCategoryName(final String categoryName) {
        this.categoryName = categoryName;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setHostId(final String hostId) {
        this.hostId = hostId;
    }

    public void setMaxCapacity(final int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setStoreName(final String storeName) {
        this.storeName = storeName;
    }

    public void setPickupPlaceName(final String pickupPlaceName) {
        this.pickupPlaceName = pickupPlaceName;
    }

    public void setPickupPlaceXCoord(final double pickupPlaceXCoord) {
        this.pickupPlaceXCoord = pickupPlaceXCoord;
    }

    public void setPickupPlaceYCoord(final double pickupPlaceYCoord) {
        this.pickupPlaceYCoord = pickupPlaceYCoord;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ChatRoomRequestDTO)) {
            return false;
        } else {
            ChatRoomRequestDTO other = (ChatRoomRequestDTO)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getMaxCapacity() != other.getMaxCapacity()) {
                return false;
            } else if (Double.compare(this.getPickupPlaceXCoord(), other.getPickupPlaceXCoord()) != 0) {
                return false;
            } else if (Double.compare(this.getPickupPlaceYCoord(), other.getPickupPlaceYCoord()) != 0) {
                return false;
            } else {
                Object this$categoryName = this.getCategoryName();
                Object other$categoryName = other.getCategoryName();
                if (this$categoryName == null) {
                    if (other$categoryName != null) {
                        return false;
                    }
                } else if (!this$categoryName.equals(other$categoryName)) {
                    return false;
                }

                label71: {
                    Object this$title = this.getTitle();
                    Object other$title = other.getTitle();
                    if (this$title == null) {
                        if (other$title == null) {
                            break label71;
                        }
                    } else if (this$title.equals(other$title)) {
                        break label71;
                    }

                    return false;
                }

                label64: {
                    Object this$hostId = this.getHostId();
                    Object other$hostId = other.getHostId();
                    if (this$hostId == null) {
                        if (other$hostId == null) {
                            break label64;
                        }
                    } else if (this$hostId.equals(other$hostId)) {
                        break label64;
                    }

                    return false;
                }

                Object this$storeName = this.getStoreName();
                Object other$storeName = other.getStoreName();
                if (this$storeName == null) {
                    if (other$storeName != null) {
                        return false;
                    }
                } else if (!this$storeName.equals(other$storeName)) {
                    return false;
                }

                Object this$pickupPlaceName = this.getPickupPlaceName();
                Object other$pickupPlaceName = other.getPickupPlaceName();
                if (this$pickupPlaceName == null) {
                    if (other$pickupPlaceName != null) {
                        return false;
                    }
                } else if (!this$pickupPlaceName.equals(other$pickupPlaceName)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ChatRoomRequestDTO;
    }

    public int hashCode() {
        //int PRIME = true;
        int result = 1;
        //int result = result * 59 + this.getMaxCapacity();
        long $pickupPlaceXCoord = Double.doubleToLongBits(this.getPickupPlaceXCoord());
        result = result * 59 + (int)($pickupPlaceXCoord >>> 32 ^ $pickupPlaceXCoord);
        long $pickupPlaceYCoord = Double.doubleToLongBits(this.getPickupPlaceYCoord());
        result = result * 59 + (int)($pickupPlaceYCoord >>> 32 ^ $pickupPlaceYCoord);
        Object $categoryName = this.getCategoryName();
        result = result * 59 + ($categoryName == null ? 43 : $categoryName.hashCode());
        Object $title = this.getTitle();
        result = result * 59 + ($title == null ? 43 : $title.hashCode());
        Object $hostId = this.getHostId();
        result = result * 59 + ($hostId == null ? 43 : $hostId.hashCode());
        Object $storeName = this.getStoreName();
        result = result * 59 + ($storeName == null ? 43 : $storeName.hashCode());
        Object $pickupPlaceName = this.getPickupPlaceName();
        result = result * 59 + ($pickupPlaceName == null ? 43 : $pickupPlaceName.hashCode());
        return result;
    }

    public String toString() {
        String var10000 = this.getCategoryName();
        return "ChatRoomRequestDTO(categoryName=" + var10000 + ", title=" + this.getTitle() + ", hostId=" + this.getHostId() + ", maxCapacity=" + this.getMaxCapacity() + ", storeName=" + this.getStoreName() + ", pickupPlaceName=" + this.getPickupPlaceName() + ", pickupPlaceXCoord=" + this.getPickupPlaceXCoord() + ", pickupPlaceYCoord=" + this.getPickupPlaceYCoord() + ")";
    }
}
