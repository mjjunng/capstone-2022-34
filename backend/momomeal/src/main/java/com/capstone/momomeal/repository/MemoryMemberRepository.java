package com.capstone.momomeal.repository;

import com.capstone.momomeal.domain.Members;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static List<Members> store = new ArrayList<>();

    @Override
    public Members save(Members member) {
        store.add(member);
        return member;
    }

    @Override
    public Optional<Members> findById(String name) {
        for(Members data : store){
            if(data.getUsername() == name){
                return Optional.ofNullable(data);
            }
            else{
                return null;
            }
        }
        return null;
    }

    @Override
    public Optional<Members> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Members> findAll() {
        return store;
    }

    @Override
    public Optional<Members> findIdAndPwd(String userid, String pwd) {
        return Optional.empty();
    }

    public void clearStore(){
        store.clear();
    }
}
