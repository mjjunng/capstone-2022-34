package com.capstone.momomeal.service;

import com.capstone.momomeal.domain.Foods;
import com.capstone.momomeal.repository.FoodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository){
        this.foodRepository = foodRepository;
    }

    public String join(Foods food){
        log.info("food info = {}",food.toString());

        foodRepository.save(food);
        return food.getUserid();
    }

    public List<Foods> findAll(){ return foodRepository.getFoodList();}
}
