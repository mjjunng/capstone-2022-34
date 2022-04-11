package com.capstone.momomeal.repository;

import com.capstone.momomeal.domain.Members;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Slf4j
public class JpaMemberRepository implements MemberRepository{

    //Spring과 DB를 관리해준다다
    //JPA를 사용할라면 EntityManager를 주입받아야 한다
   private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Members save(Members member) {
        log.info("member = {}",member.toString());
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Members> findById(String id) {//PK이기에 다르게 조회해야한다
        Members member = em.find(Members.class, id);
        return Optional.ofNullable(member);
    }

    public Optional<Members> findByName(String name){
        List<Members> result = em.createQuery("select m from Members where m.name = :name", Members.class).
                setParameter("userName", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Members> findAll() {
        List<Members> result = em.createQuery("select m from Members m", Members.class).getResultList();
        return result;
    }

    @Override
    public Optional<Members> findIdAndPwd(String userid, String pwd) { //비밀번호 검색
        List<Members> result = em.createQuery("select m from Members m where m.pwd = :pwd AND m.userid = :userid", Members.class)
                .setParameter("pwd",pwd)
                .setParameter("userid", userid)
                .getResultList();
        return result.stream().findAny();
    }

}
