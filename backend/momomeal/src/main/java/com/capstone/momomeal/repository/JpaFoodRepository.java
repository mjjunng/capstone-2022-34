package com.capstone.momomeal.repository;

import com.capstone.momomeal.domain.Foods;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
public class JpaFoodRepository implements FoodRepository{

    private final EntityManager em;

    public JpaFoodRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Foods save(Foods food) {
        log.info("food add = {}",food.toString());
        em.persist(food);
        return food;
    }

    @Override
    public Foods update(Foods food) {
        return null;
    }

    @Override
    public Foods remove(Foods food) {
        return null;
    }

    @Override
    public List<Foods> getFoodList() {
        List<Foods> result = em.createQuery("select m from Foods m",Foods.class).getResultList();
        return result;
    }

    @Override
    public Foods findFood(String foodname) {
        return null;
    }

    @Override
    public Foods findRs(String Rsname) {
        return null;
    }
}
