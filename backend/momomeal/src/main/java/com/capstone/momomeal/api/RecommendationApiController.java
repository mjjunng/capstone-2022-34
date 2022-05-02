package com.capstone.momomeal.api;


import com.capstone.momomeal.domain.*;
import com.capstone.momomeal.service.ChatRoomService;
import com.capstone.momomeal.service.MemberService;
import com.capstone.momomeal.service.RecommendCategoryService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class RecommendationApiController {
    private final MemberService memberService;
    private final RecommendCategoryService recommendCategoryService;
    private final ChatRoomService chatRoomService;


    /**
     * 추천 카테고리 객체 생성하고, 설문조사에서 사용자가 선택한 카테고리의 가중치 값 더하는 메서드
     * @param memberId 현재 사용자의 id값
     * @param categories 설문조사에서 사용자가 선택한 카테고리 리스트
     */
    @GetMapping("/preferred-category/{memberId}/{categories}")
    public void getPreferredCategory(@PathVariable Long memberId, @PathVariable List<String> categories){
        List <String> nullRemovedCategories = categories.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        // 현재 회원 데이터 가져오기
        Optional<Members> getMember = memberService.findById(memberId);

        if (getMember.isPresent()){
            Members member = getMember.get();
            RecommendCategory recommendCategory;

            // 추천 카테고리 객체 생성
            recommendCategory =
                    recommendCategoryService.createRecommendCategory(member);


            // 설문조사에서 사용자가 선택한 카테고리의 가중치 100더해서 저장
            for (String category : nullRemovedCategories){
                recommendCategoryService.addValue(recommendCategory, category, 100);
            }

        }
    }

    /**
     * 임계값을 기준으로 사용자에게 추천할 카테고리 뽑아서 해당 카테고리 && 참여하지 않은
     * 채팅방 DTO 목록 반환하는 메서드
     * @param memberId 현재 사용자의 id값
     * @return 추천 카테고리 채팅방 DTO 목록
     */
    @GetMapping("/recommend-chat-list/{memberId}")
    public ResponseEntity returnRecommendChatRoomList(@PathVariable Long memberId){
        Optional<Members> getMember = memberService.findById(memberId);
        int threshold = 5;  // 임계값 임의로 5로 설정
        List<ChatRoomListDto> result = new ArrayList<>();

        if (getMember.isPresent()){
            Members member = getMember.get();
            // 사용자가 이미 참여한 채팅 거르기 위해 사용자가 참여한 채팅방 id(ChatRoomId)값이 필요
            List<JoinedChatRoom> joinedChatRooms = member.getJoinedChatRooms(); // 참여한 joinedChatRooms
            List<Long> participatedChatRoomIds = new ArrayList<>();    // 참여한 chatRoomIds

            for (JoinedChatRoom joinedChatRoom : joinedChatRooms) {
                Long participatedChatRoomId = joinedChatRoom.getChatRoom().getId();
                participatedChatRoomIds.add(participatedChatRoomId);
            }   // end

            RecommendCategory recommendCategory = member.getRecommendCategory();

            if (recommendCategory != null){ // 추천 카테고리 객체가 존재할 때
                // 추천 카테고리 리스트
                List<Category> recommendCategoryOverThresholdList =
                        recommendCategoryService.getRecommendCategoryOverThresholdList(recommendCategory,
                                threshold);

                // 추천할 카테고리 리스트가 존재할 때
                if (recommendCategoryOverThresholdList != null){
                    // 추천할 카테고리의 채팅방 리스트
                    List<ChatRoom> allExceptParticipatedChatRooms = chatRoomService.
                            findByCategoryIn(recommendCategoryOverThresholdList, participatedChatRoomIds);

                    // 추천 카테고리 채팅방에서 dto만 뽑음
                    result = allExceptParticipatedChatRooms.stream()
                            .map(c -> new ChatRoomListDto(c))
                            .collect(Collectors.toList());

                }
            }

        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);

    }

    @Data
    @AllArgsConstructor
    static class ChatRoomListDto{
        private Long id;
        private String title;
        private String pickupPlaceName;
        private LocalDateTime createdDate;
        private int distance;

        public ChatRoomListDto(ChatRoom chatRoom) {
            this.id = chatRoom.getId();
            this.title = chatRoom.getTitle();
            this.pickupPlaceName = chatRoom.getPickupPlaceName();
            this.createdDate = chatRoom.getCreatedDate();
            this.distance = chatRoom.getDistance();
        }
    }





}
