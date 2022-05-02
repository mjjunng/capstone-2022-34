package com.capstone.momomeal.service;

import com.capstone.momomeal.domain.*;
import com.capstone.momomeal.repository.JoinedChatRoomRepository;
import com.capstone.momomeal.repository.RecommendCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RecommendCategoryService {

    private final RecommendCategoryRepository recommendCategoryRepository;


    /**
     * 회원의 추천 카테고리 저장 메서드
     * @param recommendCategory 저장할 추천 카테고리 object
     * @return  저장한 추천 카테고리 id값
     */
    public Long save(RecommendCategory recommendCategory){
        recommendCategoryRepository.save(recommendCategory);
        return recommendCategory.getId();
    }

    /**
     * 추천 카테고리 object 생성 메서드
     * @param member 해당 회원
     * @return 생성한 추천 카테고리 object
     */
    public RecommendCategory createRecommendCategory(Members member){
        RecommendCategory recommendCategory = new RecommendCategory(member);
        member.setRecommendCategory(recommendCategory);
        save(recommendCategory);
        return recommendCategory;
    }

    /**
     * 설문지에서 사용자가 선택한 카테고리의 가중치값 더하는 메서드
     * @param category 설문지에서 사용자가 선택한 카테고리
     * @param value 해당 카테고리 value만큼 가중치 더함
     */
    public void addValue(RecommendCategory recommendCategory, String category, int value){
        switch (category) {
            case "Chicken":
                recommendCategory.setCntChicken(recommendCategory.getCntChicken() + value);
                break;
            case "Pizza":
                recommendCategory.setCntPizza(recommendCategory.getCntPizza() + value);
                break;
            case "Korean":
                recommendCategory.setCntKorean(recommendCategory.getCntKorean() + value);
                break;
            case "Chinese":
                recommendCategory.setCntChinese(recommendCategory.getCntChinese() + value);
                break;
            case "Japanese":
                recommendCategory.setCntJapanese(recommendCategory.getCntJapanese() + value);
                break;
            case "Western":
                recommendCategory.setCntWestern(recommendCategory.getCntWestern() + value);
                break;
            case "Snackbar":
                recommendCategory.setCntSnackbar(recommendCategory.getCntSnackbar() + value);
                break;
            case "MidnightSnack":
                recommendCategory.setCntMidnightSnack(recommendCategory.getCntMidnightSnack() + value);
                break;
            case "BoiledPork":
                recommendCategory.setCntBoiledPork(recommendCategory.getCntBoiledPork() + value);
                break;
            case "CafeAndDesert":
                recommendCategory.setCntCafeAndDesert(recommendCategory.getCntCafeAndDesert() + value);
                break;
            case "Fastfood":
                recommendCategory.setCntFastfood(recommendCategory.getCntFastfood() + value);
                break;
            default:
                recommendCategory.setCnEtc(recommendCategory.getCnEtc() + value);
                break;
        }

    }


    /**
     *
     * @param recommendCategory 해당 추천 카테고리 객체
     * @param threshold 임계값 - 이 값보다 큰 값을 가진 카테고리를 추천
     * @return 임계값을 넘은 카테고리 리스트 -> 추천 카테고리 목록
     */
    public List<Category> getRecommendCategoryOverThresholdList(RecommendCategory recommendCategory,
                                                            int threshold){

        // threshold값을 넘으면 리스트에 추가
        List <Category> recommendCategoryOverThresholdList = new ArrayList<>();
        if (threshold <= recommendCategory.getCntChicken()){
            recommendCategoryOverThresholdList.add(Category.CHICKEN);
        }
        if (threshold <= recommendCategory.getCntPizza()){
            recommendCategoryOverThresholdList.add(Category.PIZZA);
        }
        if (threshold <= recommendCategory.getCntKorean()){
            recommendCategoryOverThresholdList.add(Category.KOREAN);
        }
        if (threshold <= recommendCategory.getCntChinese()){
            recommendCategoryOverThresholdList.add(Category.CHINESE);
        }
        if (threshold <= recommendCategory.getCntJapanese()){
            recommendCategoryOverThresholdList.add(Category.JAPANESE);
        }
        if (threshold <= recommendCategory.getCntWestern()){
            recommendCategoryOverThresholdList.add(Category.WESTERN);
        }
        if (threshold <= recommendCategory.getCntSnackbar()){
            recommendCategoryOverThresholdList.add(Category.SNACKBAR);
        }
        if (threshold <= recommendCategory.getCntMidnightSnack()){
            recommendCategoryOverThresholdList.add(Category.MIDNIGHTSNACK);
        }
        if (threshold <= recommendCategory.getCntBoiledPork()){
            recommendCategoryOverThresholdList.add(Category.BOILEDPORK);
        }
        if (threshold <= recommendCategory.getCntCafeAndDesert()){
            recommendCategoryOverThresholdList.add(Category.CAFE);
        }
        if (threshold <= recommendCategory.getCntFastfood()){
            recommendCategoryOverThresholdList.add(Category.FASTFOOD);
        }
        if (threshold <= recommendCategory.getCnEtc()){
            recommendCategoryOverThresholdList.add(Category.ETC);
        }

        return recommendCategoryOverThresholdList;
    }
}
