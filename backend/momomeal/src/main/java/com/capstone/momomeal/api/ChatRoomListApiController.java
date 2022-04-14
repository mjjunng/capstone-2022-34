//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.capstone.momomeal.api;

import com.capstone.momomeal.domain.Category;
import com.capstone.momomeal.domain.ChatRoom;
import com.capstone.momomeal.domain.JoinedChatRoom;
import com.capstone.momomeal.domain.Members;
import com.capstone.momomeal.domain.TransStringToEnum;
import com.capstone.momomeal.service.ChatRoomService;
import com.capstone.momomeal.service.MemberService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatRoomListApiController {
    private final ChatRoomService chatRoomService;
    private final MemberService memberService;

    @GetMapping({"/chat-list/{categoryName}/{memberId}"})
    public ResponseEntity returnCategoryList(@PathVariable String categoryName, @PathVariable String memberId) {
        TransStringToEnum te = new TransStringToEnum();
        Category selectedCategory = te.transferStringToEnum(categoryName);
        List<ChatRoom> chatRooms = this.getChatRoomsExceptParticipatedCharRooms(memberId);
        List<ChatRoomListApiController.ChatRoomListDto> result = (List)chatRooms.stream().filter((c) -> {
            return c.getCategory().equals(selectedCategory);
        }).map((c) -> {
            return new ChatRoomListApiController.ChatRoomListDto(c);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping({"/chat-list/{memberId}"})
    public ResponseEntity returnAllList(@PathVariable String memberId) {
        List<ChatRoom> chatRooms = this.getChatRoomsExceptParticipatedCharRooms(memberId);
        List<ChatRoomListApiController.ChatRoomListDto> result = (List)chatRooms.stream().map((c) -> {
            return new ChatRoomListApiController.ChatRoomListDto(c);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    private List<ChatRoom> getChatRoomsExceptParticipatedCharRooms(String testMemberId) {
        List<ChatRoom> chatRooms = new ArrayList();
        Optional<Members> getMember = this.memberService.findEmail(testMemberId);
        if (getMember.isPresent()) {
            Members member = (Members)getMember.get();
            List<JoinedChatRoom> joinedChatRooms = member.getJoinedChatRooms();
            List<Long> participatedChatRoomIds = new ArrayList();
            Iterator var7 = joinedChatRooms.iterator();

            while(var7.hasNext()) {
                JoinedChatRoom joinedChatRoom = (JoinedChatRoom)var7.next();
                Long participatedChatRoomId = joinedChatRoom.getChatRoom().getId();
                participatedChatRoomIds.add(participatedChatRoomId);
            }

            if (participatedChatRoomIds.size() < 1) {
                chatRooms = this.chatRoomService.findAll();
            } else {
                chatRooms = this.chatRoomService.findExceptParticipatedChatRoom(participatedChatRoomIds);
            }
        }

        return (List)chatRooms;
    }

    @GetMapping({"/entered-chat-list/{memberId}"})
    public ResponseEntity returnEnteredChatRoomList(@PathVariable String memberId) {
        List<ChatRoomListApiController.EnteredChatRoomListDto> result = new ArrayList();
        Optional<Members> getMember = this.memberService.findEmail(memberId);
        if (getMember.isPresent()) {
            Members member = (Members)getMember.get();
            List<JoinedChatRoom> joinedChatRooms = member.getJoinedChatRooms();
            List<ChatRoom> chatRooms = new ArrayList();
            Iterator var7 = joinedChatRooms.iterator();

            while(var7.hasNext()) {
                JoinedChatRoom joinedChatRoom = (JoinedChatRoom)var7.next();
                chatRooms.add(joinedChatRoom.getChatRoom());
            }

            if (chatRooms.size() > 1) {
                result = (List)chatRooms.stream().map((c) -> {
                    return new ChatRoomListApiController.EnteredChatRoomListDto(c);
                }).collect(Collectors.toList());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public ChatRoomListApiController(final ChatRoomService chatRoomService, final MemberService memberService) {
        this.chatRoomService = chatRoomService;
        this.memberService = memberService;
    }

    static class EnteredChatRoomListDto {
        private Long chatRoomId;
        private String title;

        public EnteredChatRoomListDto(ChatRoom chatRoom) {
            this.chatRoomId = chatRoom.getId();
            this.title = chatRoom.getTitle();
        }

        public Long getChatRoomId() {
            return this.chatRoomId;
        }

        public String getTitle() {
            return this.title;
        }

        public void setChatRoomId(final Long chatRoomId) {
            this.chatRoomId = chatRoomId;
        }

        public void setTitle(final String title) {
            this.title = title;
        }

        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof ChatRoomListApiController.EnteredChatRoomListDto)) {
                return false;
            } else {
                ChatRoomListApiController.EnteredChatRoomListDto other = (ChatRoomListApiController.EnteredChatRoomListDto)o;
                if (!other.canEqual(this)) {
                    return false;
                } else {
                    Object this$chatRoomId = this.getChatRoomId();
                    Object other$chatRoomId = other.getChatRoomId();
                    if (this$chatRoomId == null) {
                        if (other$chatRoomId != null) {
                            return false;
                        }
                    } else if (!this$chatRoomId.equals(other$chatRoomId)) {
                        return false;
                    }

                    Object this$title = this.getTitle();
                    Object other$title = other.getTitle();
                    if (this$title == null) {
                        if (other$title != null) {
                            return false;
                        }
                    } else if (!this$title.equals(other$title)) {
                        return false;
                    }

                    return true;
                }
            }
        }

        protected boolean canEqual(final Object other) {
            return other instanceof ChatRoomListApiController.EnteredChatRoomListDto;
        }

        public int hashCode() {
            //int PRIME = true;
            int result = 1;
            Object $chatRoomId = this.getChatRoomId();
            //int result = result * 59 + ($chatRoomId == null ? 43 : $chatRoomId.hashCode());
            Object $title = this.getTitle();
            result = result * 59 + ($title == null ? 43 : $title.hashCode());
            return result;
        }

        public String toString() {
            Long var10000 = this.getChatRoomId();
            return "ChatRoomListApiController.EnteredChatRoomListDto(chatRoomId=" + var10000 + ", title=" + this.getTitle() + ")";
        }

        public EnteredChatRoomListDto() {
        }
    }

    static class ChatRoomListDto {
        private Long id;
        private String title;
        private String pickupPlaceName;
        private LocalDateTime createdDate;
        private double pickupPlaceXCoord;
        private double pickupPlaceYCoord;

        public ChatRoomListDto(ChatRoom chatRoom) {
            this.id = chatRoom.getId();
            this.title = chatRoom.getTitle();
            this.pickupPlaceName = chatRoom.getPickupPlaceName();
            this.createdDate = chatRoom.getCreatedDate();
            this.pickupPlaceXCoord = chatRoom.getPickupPlaceXCoord();
            this.pickupPlaceYCoord = chatRoom.getPickupPlaceYCoord();
        }

        public Long getId() {
            return this.id;
        }

        public String getTitle() {
            return this.title;
        }

        public String getPickupPlaceName() {
            return this.pickupPlaceName;
        }

        public LocalDateTime getCreatedDate() {
            return this.createdDate;
        }

        public double getPickupPlaceXCoord() {
            return this.pickupPlaceXCoord;
        }

        public double getPickupPlaceYCoord() {
            return this.pickupPlaceYCoord;
        }

        public void setId(final Long id) {
            this.id = id;
        }

        public void setTitle(final String title) {
            this.title = title;
        }

        public void setPickupPlaceName(final String pickupPlaceName) {
            this.pickupPlaceName = pickupPlaceName;
        }

        public void setCreatedDate(final LocalDateTime createdDate) {
            this.createdDate = createdDate;
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
            } else if (!(o instanceof ChatRoomListApiController.ChatRoomListDto)) {
                return false;
            } else {
                ChatRoomListApiController.ChatRoomListDto other = (ChatRoomListApiController.ChatRoomListDto)o;
                if (!other.canEqual(this)) {
                    return false;
                } else if (Double.compare(this.getPickupPlaceXCoord(), other.getPickupPlaceXCoord()) != 0) {
                    return false;
                } else if (Double.compare(this.getPickupPlaceYCoord(), other.getPickupPlaceYCoord()) != 0) {
                    return false;
                } else {
                    label64: {
                        Object this$id = this.getId();
                        Object other$id = other.getId();
                        if (this$id == null) {
                            if (other$id == null) {
                                break label64;
                            }
                        } else if (this$id.equals(other$id)) {
                            break label64;
                        }

                        return false;
                    }

                    label57: {
                        Object this$title = this.getTitle();
                        Object other$title = other.getTitle();
                        if (this$title == null) {
                            if (other$title == null) {
                                break label57;
                            }
                        } else if (this$title.equals(other$title)) {
                            break label57;
                        }

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

                    Object this$createdDate = this.getCreatedDate();
                    Object other$createdDate = other.getCreatedDate();
                    if (this$createdDate == null) {
                        if (other$createdDate != null) {
                            return false;
                        }
                    } else if (!this$createdDate.equals(other$createdDate)) {
                        return false;
                    }

                    return true;
                }
            }
        }

        protected boolean canEqual(final Object other) {
            return other instanceof ChatRoomListApiController.ChatRoomListDto;
        }

        public int hashCode() {
            //int PRIME = true;
            int result = 1;
            long $pickupPlaceXCoord = Double.doubleToLongBits(this.getPickupPlaceXCoord());
            //int result = result * 59 + (int)($pickupPlaceXCoord >>> 32 ^ $pickupPlaceXCoord);
            long $pickupPlaceYCoord = Double.doubleToLongBits(this.getPickupPlaceYCoord());
            result = result * 59 + (int)($pickupPlaceYCoord >>> 32 ^ $pickupPlaceYCoord);
            Object $id = this.getId();
            result = result * 59 + ($id == null ? 43 : $id.hashCode());
            Object $title = this.getTitle();
            result = result * 59 + ($title == null ? 43 : $title.hashCode());
            Object $pickupPlaceName = this.getPickupPlaceName();
            result = result * 59 + ($pickupPlaceName == null ? 43 : $pickupPlaceName.hashCode());
            Object $createdDate = this.getCreatedDate();
            result = result * 59 + ($createdDate == null ? 43 : $createdDate.hashCode());
            return result;
        }

        public String toString() {
            Long var10000 = this.getId();
            return "ChatRoomListApiController.ChatRoomListDto(id=" + var10000 + ", title=" + this.getTitle() + ", pickupPlaceName=" + this.getPickupPlaceName() + ", createdDate=" + this.getCreatedDate() + ", pickupPlaceXCoord=" + this.getPickupPlaceXCoord() + ", pickupPlaceYCoord=" + this.getPickupPlaceYCoord() + ")";
        }

        public ChatRoomListDto(final Long id, final String title, final String pickupPlaceName, final LocalDateTime createdDate, final double pickupPlaceXCoord, final double pickupPlaceYCoord) {
            this.id = id;
            this.title = title;
            this.pickupPlaceName = pickupPlaceName;
            this.createdDate = createdDate;
            this.pickupPlaceXCoord = pickupPlaceXCoord;
            this.pickupPlaceYCoord = pickupPlaceYCoord;
        }
    }

    static class Result<T> {
        private T data;

        public T getData() {
            return this.data;
        }

        public void setData(final T data) {
            this.data = data;
        }

        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof ChatRoomListApiController.Result)) {
                return false;
            } else {
                ChatRoomListApiController.Result<?> other = (ChatRoomListApiController.Result)o;
                if (!other.canEqual(this)) {
                    return false;
                } else {
                    Object this$data = this.getData();
                    Object other$data = other.getData();
                    if (this$data == null) {
                        if (other$data != null) {
                            return false;
                        }
                    } else if (!this$data.equals(other$data)) {
                        return false;
                    }

                    return true;
                }
            }
        }

        protected boolean canEqual(final Object other) {
            return other instanceof ChatRoomListApiController.Result;
        }

        public int hashCode() {
            //int PRIME = true;
            int result = 1;
            Object $data = this.getData();
            //int result = result * 59 + ($data == null ? 43 : $data.hashCode());
            return result;
        }

        public String toString() {
            return "ChatRoomListApiController.Result(data=" + this.getData() + ")";
        }

        public Result(final T data) {
            this.data = data;
        }
    }
}
