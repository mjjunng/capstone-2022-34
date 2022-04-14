//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.capstone.momomeal.api;

import com.capstone.momomeal.domain.ChatRoom;
import com.capstone.momomeal.domain.ChatRoomRequestDTO;
import com.capstone.momomeal.domain.JoinedChatRoom;
import com.capstone.momomeal.domain.Members;
import com.capstone.momomeal.service.ChatRoomService;
import com.capstone.momomeal.service.JoinedChatRoomService;
import com.capstone.momomeal.service.MemberService;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatRoomApiController {
    private final ChatRoomService chatRoomService;
    private final MemberService memberService;
    private final JoinedChatRoomService joinedChatRoomService;

    @PostMapping({"/chat"})
    public ChatRoomApiController.CreateChatRoomResponse saveChatRoom(@RequestBody @Valid ChatRoomRequestDTO requestDTO) {
        Optional<Members> getMember = this.memberService.findEmail(requestDTO.getHostId());
        ChatRoomApiController.CreateChatRoomResponse result;
        if (getMember.isPresent()) {
            Members member = (Members)getMember.get();
            Long createChatRoomId = this.chatRoomService.createChatRoom(member, requestDTO);
            result = new ChatRoomApiController.CreateChatRoomResponse(createChatRoomId);
        } else {
            result = new ChatRoomApiController.CreateChatRoomResponse();
        }

        return result;
    }

    @GetMapping({"/clicked-chat/{chatroomId}"})
    public ResponseEntity returnClickedChatRoomData(@PathVariable Long chatroomId) {
        ChatRoom clickedChatRoom = this.chatRoomService.findById(chatroomId);
        ChatRoomApiController.ClickedChatRoomDto result;
        if (clickedChatRoom == null) {
            result = new ChatRoomApiController.ClickedChatRoomDto();
        } else {
            result = new ChatRoomApiController.ClickedChatRoomDto(clickedChatRoom);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping({"/chat/{memberId}/{chatroomId}"})
    public ChatRoomApiController.CreateJoinedChatRoomResponse enterChatRoom(@PathVariable String memberId, @PathVariable Long chatroomId) {
        Optional<Members> getMember = this.memberService.findEmail(memberId);
        ChatRoom findChatRoom = this.chatRoomService.findById(chatroomId);
        ChatRoomApiController.CreateJoinedChatRoomResponse result;
        if (getMember.isPresent()) {
            Members findMember = (Members)getMember.get();
            Long joinedChatRoomId = this.joinedChatRoomService.createJoinedChatRoom(findMember, findChatRoom);
            result = new ChatRoomApiController.CreateJoinedChatRoomResponse(joinedChatRoomId);
        } else {
            result = new ChatRoomApiController.CreateJoinedChatRoomResponse();
        }

        return result;
    }

    @DeleteMapping({"/deleted-chat/{memberId}/{chatroomId}"})
    public ChatRoomApiController.deleteCountDto deleteJoinedChatRoom(@PathVariable String memberId, @PathVariable Long chatroomId) {
        Optional<Members> getMember = this.memberService.findEmail(memberId);
        ChatRoom chatRoom = this.chatRoomService.findById(chatroomId);
        ChatRoomApiController.deleteCountDto result;
        if (getMember.isPresent()) {
            Members member = (Members)getMember.get();
            JoinedChatRoom toDeleteJoinedChatRoom = this.joinedChatRoomService.findByMemberIdAndChatRoomId(member, chatRoom);
            member.deleteJoinChatRoomFromMember(toDeleteJoinedChatRoom);
            int cntDeletedJCRecord = this.joinedChatRoomService.delete(toDeleteJoinedChatRoom.getId());
            int cntDeleteCRecord = 0;
            int cnt = this.joinedChatRoomService.countByChatRoom(chatRoom);
            if (cnt == 0) {
                cntDeleteCRecord = this.chatRoomService.delete(chatroomId);
            }

            result = new ChatRoomApiController.deleteCountDto(cntDeletedJCRecord, cntDeleteCRecord);
        } else {
            result = new ChatRoomApiController.deleteCountDto();
        }

        return result;
    }

    public ChatRoomApiController(final ChatRoomService chatRoomService, final MemberService memberService, final JoinedChatRoomService joinedChatRoomService) {
        this.chatRoomService = chatRoomService;
        this.memberService = memberService;
        this.joinedChatRoomService = joinedChatRoomService;
    }

    static class deleteCountDto {
        private int cntDeletedJoinedChatRoomRecord;
        private int cntDeletedChatRoomRecord;

        public int getCntDeletedJoinedChatRoomRecord() {
            return this.cntDeletedJoinedChatRoomRecord;
        }

        public int getCntDeletedChatRoomRecord() {
            return this.cntDeletedChatRoomRecord;
        }

        public void setCntDeletedJoinedChatRoomRecord(final int cntDeletedJoinedChatRoomRecord) {
            this.cntDeletedJoinedChatRoomRecord = cntDeletedJoinedChatRoomRecord;
        }

        public void setCntDeletedChatRoomRecord(final int cntDeletedChatRoomRecord) {
            this.cntDeletedChatRoomRecord = cntDeletedChatRoomRecord;
        }

        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof ChatRoomApiController.deleteCountDto)) {
                return false;
            } else {
                ChatRoomApiController.deleteCountDto other = (ChatRoomApiController.deleteCountDto)o;
                if (!other.canEqual(this)) {
                    return false;
                } else if (this.getCntDeletedJoinedChatRoomRecord() != other.getCntDeletedJoinedChatRoomRecord()) {
                    return false;
                } else {
                    return this.getCntDeletedChatRoomRecord() == other.getCntDeletedChatRoomRecord();
                }
            }
        }

        protected boolean canEqual(final Object other) {
            return other instanceof ChatRoomApiController.deleteCountDto;
        }

        public int hashCode() {
            //int PRIME = true;
            int result = 1;
            //int result = result * 59 + this.getCntDeletedJoinedChatRoomRecord();
            result = result * 59 + this.getCntDeletedChatRoomRecord();
            return result;
        }

        public String toString() {
            int var10000 = this.getCntDeletedJoinedChatRoomRecord();
            return "ChatRoomApiController.deleteCountDto(cntDeletedJoinedChatRoomRecord=" + var10000 + ", cntDeletedChatRoomRecord=" + this.getCntDeletedChatRoomRecord() + ")";
        }

        public deleteCountDto(final int cntDeletedJoinedChatRoomRecord, final int cntDeletedChatRoomRecord) {
            this.cntDeletedJoinedChatRoomRecord = cntDeletedJoinedChatRoomRecord;
            this.cntDeletedChatRoomRecord = cntDeletedChatRoomRecord;
        }

        public deleteCountDto() {
        }
    }

    static class CreateJoinedChatRoomResponse {
        private Long id;

        public Long getId() {
            return this.id;
        }

        public void setId(final Long id) {
            this.id = id;
        }

        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof ChatRoomApiController.CreateJoinedChatRoomResponse)) {
                return false;
            } else {
                ChatRoomApiController.CreateJoinedChatRoomResponse other = (ChatRoomApiController.CreateJoinedChatRoomResponse)o;
                if (!other.canEqual(this)) {
                    return false;
                } else {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id != null) {
                            return false;
                        }
                    } else if (!this$id.equals(other$id)) {
                        return false;
                    }

                    return true;
                }
            }
        }

        protected boolean canEqual(final Object other) {
            return other instanceof ChatRoomApiController.CreateJoinedChatRoomResponse;
        }

        public int hashCode() {
            //int PRIME = true;
            int result = 1;
            Object $id = this.getId();
            //int result = result * 59 + ($id == null ? 43 : $id.hashCode());
            return result;
        }

        public String toString() {
            return "ChatRoomApiController.CreateJoinedChatRoomResponse(id=" + this.getId() + ")";
        }

        public CreateJoinedChatRoomResponse(final Long id) {
            this.id = id;
        }

        public CreateJoinedChatRoomResponse() {
        }
    }

    static class ClickedChatRoomDto {
        private Long chatRoomId;
        private String title;
        private String category;
        private int maxCapacity;
        private String storeName;
        private String pickupPlaceName;
        private double pickupPlaceXCoord;
        private double pickupPlaceYCoord;

        public ClickedChatRoomDto(ChatRoom chatRoom) {
            this.chatRoomId = chatRoom.getId();
            this.title = chatRoom.getTitle();
            this.category = chatRoom.getCategory().getName();
            this.maxCapacity = chatRoom.getMaxCapacity();
            this.storeName = chatRoom.getStoreName();
            this.pickupPlaceName = chatRoom.getPickupPlaceName();
            this.pickupPlaceXCoord = chatRoom.getPickupPlaceXCoord();
            this.pickupPlaceYCoord = chatRoom.getPickupPlaceYCoord();
        }

        public Long getChatRoomId() {
            return this.chatRoomId;
        }

        public String getTitle() {
            return this.title;
        }

        public String getCategory() {
            return this.category;
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

        public void setChatRoomId(final Long chatRoomId) {
            this.chatRoomId = chatRoomId;
        }

        public void setTitle(final String title) {
            this.title = title;
        }

        public void setCategory(final String category) {
            this.category = category;
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
            } else if (!(o instanceof ChatRoomApiController.ClickedChatRoomDto)) {
                return false;
            } else {
                ChatRoomApiController.ClickedChatRoomDto other = (ChatRoomApiController.ClickedChatRoomDto)o;
                if (!other.canEqual(this)) {
                    return false;
                } else if (this.getMaxCapacity() != other.getMaxCapacity()) {
                    return false;
                } else if (Double.compare(this.getPickupPlaceXCoord(), other.getPickupPlaceXCoord()) != 0) {
                    return false;
                } else if (Double.compare(this.getPickupPlaceYCoord(), other.getPickupPlaceYCoord()) != 0) {
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
                        Object this$category = this.getCategory();
                        Object other$category = other.getCategory();
                        if (this$category == null) {
                            if (other$category == null) {
                                break label64;
                            }
                        } else if (this$category.equals(other$category)) {
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
            return other instanceof ChatRoomApiController.ClickedChatRoomDto;
        }

        public int hashCode() {
            //int PRIME = true;
            int result = 1;
            //int result = result * 59 + this.getMaxCapacity();
            long $pickupPlaceXCoord = Double.doubleToLongBits(this.getPickupPlaceXCoord());
            result = result * 59 + (int)($pickupPlaceXCoord >>> 32 ^ $pickupPlaceXCoord);
            long $pickupPlaceYCoord = Double.doubleToLongBits(this.getPickupPlaceYCoord());
            result = result * 59 + (int)($pickupPlaceYCoord >>> 32 ^ $pickupPlaceYCoord);
            Object $chatRoomId = this.getChatRoomId();
            result = result * 59 + ($chatRoomId == null ? 43 : $chatRoomId.hashCode());
            Object $title = this.getTitle();
            result = result * 59 + ($title == null ? 43 : $title.hashCode());
            Object $category = this.getCategory();
            result = result * 59 + ($category == null ? 43 : $category.hashCode());
            Object $storeName = this.getStoreName();
            result = result * 59 + ($storeName == null ? 43 : $storeName.hashCode());
            Object $pickupPlaceName = this.getPickupPlaceName();
            result = result * 59 + ($pickupPlaceName == null ? 43 : $pickupPlaceName.hashCode());
            return result;
        }

        public String toString() {
            Long var10000 = this.getChatRoomId();
            return "ChatRoomApiController.ClickedChatRoomDto(chatRoomId=" + var10000 + ", title=" + this.getTitle() + ", category=" + this.getCategory() + ", maxCapacity=" + this.getMaxCapacity() + ", storeName=" + this.getStoreName() + ", pickupPlaceName=" + this.getPickupPlaceName() + ", pickupPlaceXCoord=" + this.getPickupPlaceXCoord() + ", pickupPlaceYCoord=" + this.getPickupPlaceYCoord() + ")";
        }

        public ClickedChatRoomDto() {
        }
    }

    static class CreateChatRoomResponse {
        private Long id;

        public CreateChatRoomResponse(Long id) {
            this.id = id;
        }

        public Long getId() {
            return this.id;
        }

        public void setId(final Long id) {
            this.id = id;
        }

        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof ChatRoomApiController.CreateChatRoomResponse)) {
                return false;
            } else {
                ChatRoomApiController.CreateChatRoomResponse other = (ChatRoomApiController.CreateChatRoomResponse)o;
                if (!other.canEqual(this)) {
                    return false;
                } else {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id != null) {
                            return false;
                        }
                    } else if (!this$id.equals(other$id)) {
                        return false;
                    }

                    return true;
                }
            }
        }

        protected boolean canEqual(final Object other) {
            return other instanceof ChatRoomApiController.CreateChatRoomResponse;
        }

        public int hashCode() {
            //int PRIME = true;
            int result = 1;
            Object $id = this.getId();
            //int result = result * 59 + ($id == null ? 43 : $id.hashCode());
            return result;
        }

        public String toString() {
            return "ChatRoomApiController.CreateChatRoomResponse(id=" + this.getId() + ")";
        }

        public CreateChatRoomResponse() {
        }
    }
}
