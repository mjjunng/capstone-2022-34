package com.capstone.momomeal;

import com.capstone.momomeal.repository.*;
import com.capstone.momomeal.service.FoodService;
import com.capstone.momomeal.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    //스프링 사용할때 필요하다
//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource){this.dataSource = dataSource;}
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }

    /**
     * 이렇게 하면 자바코드로 통해서 빈을 생성했다
     * @Autowired, @Service, @Repository를 사용 안해도 된다
     * 생성자 주입을 권장한다
     */
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){ //이것도 DI
        return new JpaMemberRepository(em);
    }
    @Bean
    public FoodService foodService(){ return new FoodService(foodRepository());}

    @Bean
    public FoodRepository foodRepository(){
        return new JpaFoodRepository(em);
    }

    @Bean
    public MemoryUserRepository memoryUserService(){
        return new MemoryUserRepository();
    }
}
