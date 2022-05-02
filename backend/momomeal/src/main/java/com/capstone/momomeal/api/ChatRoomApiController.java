package com.capstone.momomeal.api;

import com.capstone.momomeal.domain.*;
import com.capstone.momomeal.service.ChatRoomService;
import com.capstone.momomeal.service.JoinedChatRoomService;
import com.capstone.momomeal.service.MemberService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ChatRoomApiController {
    private final ChatRoomService chatRoomService;
    private final MemberService memberService;
    private final JoinedChatRoomService joinedChatRoomService;

    /**
     * 채팅방 생성 응답 api
     * @param requestDTO 안드로이드에서 받은 채팅방 데이터
     * @return 생성한 채팅방(ChatRoom) id값
     */
    @PostMapping("/chat")
    public CreateChatRoomResponse saveChatRoom(@RequestBody @Valid ChatRoomRequestDTO requestDTO) {
        CreateChatRoomResponse result;
        // 현재 회원 데이터 가져오기
        Optional<Members> getMember = memberService.findById(requestDTO.getHostId());

        if (getMember.isPresent()){
            Members member = getMember.get();
            // 사용자의 현재 위치와 수령 장소까지의 거리
            int distance =calculateDistance(member.getX_value(), member.getY_value(),
                    requestDTO.getPickupPlaceXCoord(), requestDTO.getPickupPlaceYCoord());

            // 채팅방 생성
            Long createChatRoomId = chatRoomService.createChatRoom(member, requestDTO, distance);
            result = new CreateChatRoomResponse(createChatRoomId);
        } else{
            result = new CreateChatRoomResponse();
        }

        return result;
    }

    // 사용자의 현재 위치와 수령 장소까지의 거리 계산 메서드
    public int calculateDistance(double memberX, double memberY, double placeX, double placeY){
        double x = Math.cos(Math.toRadians(memberX) * 6400 * 2 * 3.14 / 360) * Math.abs(memberY - placeY);
        double y = 111 * Math.abs(memberX - placeX);

        double theta = memberY - placeY;
        double dist = Math.sin(deg2rad(memberX)) * Math.sin(deg2rad(placeX)) + Math.cos(deg2rad(memberX))
                    * Math.cos(deg2rad(placeX)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist *= 60 * 1.1515 * 1609.344;

        return (int) dist;

    }

    // converts decimal degrees to radians
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // converts radians to decimal degrees
    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }


    /**
     * 채팅방 생성 요청 처리 후 응답
     */
    @Data
    @NoArgsConstructor
    static class CreateChatRoomResponse {
        private Long id;

        public CreateChatRoomResponse(Long id) {
            this.id = id;
        }
    }

    /**
     * 사용자가 클릭한 채팅방 데이터(dto) 전송 api
     * @param chatroomId 클릭한 채팅방 id
     * @return 클릭한 채팅방 데이터(dto)
     */
    @GetMapping("/clicked-chat/{chatroomId}")
    public ResponseEntity returnClickedChatRoomData(@PathVariable Long chatroomId){
        // chatRoomId를 통해 해당 채팅방 데이터 조회
        ChatRoom clickedChatRoom = chatRoomService.findById(chatroomId);

        ClickedChatRoomDto result;


        if (clickedChatRoom == null){   // 없는 채팅방 요청 -> 빈 값
            result = new ClickedChatRoomDto();
        } else{
            result = new ClickedChatRoomDto(clickedChatRoom);   // 해당 chatRoom dto로 변환
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(result);

    }

    @Data
    @NoArgsConstructor
    static class ClickedChatRoomDto{
        private Long chatRoomId;
        private String title;
        private String category;
        private int maxCapacity;
        private String storeName;
        private String pickupPlaceName;
        private int distance;

        public ClickedChatRoomDto(ChatRoom chatRoom) {
            this.chatRoomId = chatRoom.getId();
            this.title = chatRoom.getTitle();
            this.category = chatRoom.getCategory().getName();
            this.maxCapacity = chatRoom.getMaxCapacity();
            this.storeName = chatRoom.getStoreName();
            this.pickupPlaceName = chatRoom.getPickupPlaceName();
            this.distance = chatRoom.getDistance();
        }

    }

    /**
     * 호스트가 아닌 사용자의 채팅방 참여 응답 api
     * 해당 채팅방 멤버에 해당 사용자 추가함
     */
    @GetMapping("/chat/{memberId}/{chatroomId}")
    public CreateJoinedChatRoomResponse enterChatRoom(@PathVariable Long memberId,
                                                      @PathVariable Long chatroomId){

        CreateJoinedChatRoomResponse result;
        // id값으로 회원 객체 가져오기
        Optional<Members> getMember = memberService.findById(memberId);
        // id값으로 채팅방 객체 가져오기
        ChatRoom findChatRoom = chatRoomService.findById(chatroomId);

        // joinedChatRoom 생성
        if (getMember.isPresent()){
            Members findMember = getMember.get();
            Long joinedChatRoomId = joinedChatRoomService.createJoinedChatRoom(findMember, findChatRoom);
            result = new CreateJoinedChatRoomResponse(joinedChatRoomId);
        } else {
            result = new CreateJoinedChatRoomResponse();
        }

        return result;
    }

    /**
     * 채팅방 참여 요청 처리 후 응답
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class CreateJoinedChatRoomResponse {
        private Long id;
    }

    /**
     * 채팅방 삭제 응답 api - 참여한 채팅방(JoinedChatRoom) 삭제 - 연관관계 모두 삭제해야 함
     * @param chatroomId 삭제하려는 joinedChatRoom와 연관된 chatRoom id
     * @return deleteCountDto: 삭제한 joinedChatRoom 레코드 수, 삭제한 chatRoom 레코드 수
     */
    @DeleteMapping("/deleted-chat/{memberId}/{chatroomId}")
    public deleteCountDto deleteJoinedChatRoom(@PathVariable Long memberId,
                                               @PathVariable Long chatroomId){
        deleteCountDto result;

        Optional<Members> getMember = memberService.findById(memberId);
        ChatRoom chatRoom = chatRoomService.findById(chatroomId);

        if (getMember.isPresent()){
            Members member = getMember.get();
            JoinedChatRoom toDeleteJoinedChatRoom = joinedChatRoomService
                    .findByMemberIdAndChatRoomId(member, chatRoom);

            member.deleteJoinChatRoomFromMember(toDeleteJoinedChatRoom);

            int cntDeletedJCRecord = joinedChatRoomService.delete(toDeleteJoinedChatRoom.getId());

            // joinedChatRoom과 연관된 chatRoom없으면 chatRoom도 DB에서 삭제함
            int cntDeleteCRecord = 0;
            int cnt = joinedChatRoomService.countByChatRoom(chatRoom);
            if (cnt == 0) {
                cntDeleteCRecord = chatRoomService.delete(chatroomId);
            }

            result = new deleteCountDto(cntDeletedJCRecord, cntDeleteCRecord);

        } else{
            result = new deleteCountDto();
        }

        return result;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class deleteCountDto{
        private int cntDeletedJoinedChatRoomRecord;
        private int cntDeletedChatRoomRecord;

    }


    @GetMapping("/entered-chat-info/{chatroomId}")
    public chatRoomInfoDto returnChatRoomInfo(@PathVariable Long chatroomId){
        ChatRoom chatRoom = chatRoomService.findById(chatroomId);

        // 참여중인 채팅방과 연관된 joinedChatRooms
        List<JoinedChatRoom> joinedChatRooms = joinedChatRoomService.findByChatRoom(chatRoom);

        // 채팅방에 참여 중인 멤버의 이름 리스트
        List <String> memberNameList = new ArrayList<>();

        // joinedChatRooms에서 멤버의 이름 뽑아낸다.
        for (JoinedChatRoom joinedChatRoom : joinedChatRooms) {
            memberNameList.add(joinedChatRoom.getMember().getRealName());
        }

        return new chatRoomInfoDto(memberNameList, chatRoom.getStoreName(), chatRoom.getPickupPlaceName());

    }

    @Data
    @AllArgsConstructor
    static class chatRoomInfoDto{
        private List<String> memberNames = new ArrayList<>();
        private String storeName;
        private String pickupPlaceName;
    }
}
