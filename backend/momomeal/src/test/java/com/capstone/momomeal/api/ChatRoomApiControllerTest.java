package com.capstone.momomeal.api;

import com.capstone.momomeal.domain.Category;
import com.capstone.momomeal.domain.ChatRoom;
import com.capstone.momomeal.service.ChatRoomService;
import com.capstone.momomeal.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.BDDMockito.given;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ChatRoomApiController.class)
@MockBean(JpaMetamodelMappingContext.class)
class ChatRoomApiControllerTest {
//    @Autowired private MockMvc mvc;
//
//    @MockBean ChatRoomService chatRoomService;
//    @MockBean
//    MemberService memberService;

//    @Test
//    public void 카테고리별채팅방목록반환확인(){
//        ArrayList<ChatRoomApiController.ChatRoomListDto> chatRoomsWithCategory = new ArrayList<>();
//        chatRoomsWithCategory.add(ChatRoomApiController.ChatRoomListDto.builder().)
//    }

//    @Test
//    public void 전체채팅방목록반환확인() throws Exception{
//        List<ChatRoom> chatRooms = new ArrayList<>();
//        chatRooms.add(ChatRoom.builder()
//                .title("api-test1")
//                .pickupPlaceName("ff")
//                .pickupPlaceXCoord(22.2)
//                .pickupPlaceYCoord(33.3)
//                .category(Category.CHICKEN)
//                .id(1L)
//                .hostId(1L)
//                .maxCapacity(4)
//                .storeName("test")
//                .build());
//
//        chatRooms.add(ChatRoom.builder()
//                .title("api-test2")
//                .pickupPlaceName("ff")
//                .pickupPlaceXCoord(22.2)
//                .pickupPlaceYCoord(33.3)
//                .category(Category.PIZZA)
//                .id(1L)
//                .hostId(1L)
//                .maxCapacity(4)
//                .storeName("test")
//
//                .build());
//
////        given(chatRoomService.findAll()).willReturn(chatRooms);
//
//        List<ChatRoomApiController.ChatRoomListDto> result = new ArrayList<>();
//
//        result.add(new ChatRoomApiController.ChatRoomListDto(chatRooms.get(0)));
//        result.add(new ChatRoomApiController.ChatRoomListDto(chatRooms.get(1)));
//
//        ChatRoomApiController mock = org.mockito.Mockito.mock(ChatRoomApiController.class);
//        ObjectMapper mapper = new ObjectMapper();
//
//        given(mock.getChatRoomListDtos(chatRooms)).willReturn(result);
//
//        final ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/chat")
//                .contentType(MediaType.APPLICATION_JSON));
//
//        actions
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("title").value("api-test1"))
//                .andExpect(MockMvcResultMatchers.content().json(contains("api-test2")))
//                .andExpect(MockMvcResultMatchers.content().json(contains("api-test2")));
//    }


}