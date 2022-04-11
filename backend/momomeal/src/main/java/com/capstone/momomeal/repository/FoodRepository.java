package com.capstone.momomeal.repository;

import com.capstone.momomeal.domain.Foods;

import java.util.List;

public interface FoodRepository {
    Foods save(Foods food); //추가
    Foods update(Foods food); //변경
    Foods remove(Foods food); //삭제
    List<Foods> getFoodList(); //목록 불러오기
    Foods findFood(String foodname);
    Foods findRs(String Rsname);

}
