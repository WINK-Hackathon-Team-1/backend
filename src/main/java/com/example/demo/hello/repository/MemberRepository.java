package com.example.demo.hello.repository;

import com.example.demo.hello.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class MemberRepository {

    private final EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public void delete(Member member) {
        em.remove(member);
    }

    public Member findByname(String name){

        String query = "select m from Member m where m.name = :name";

        return em.createQuery(query,Member.class).
                setParameter("name",name).getSingleResult();
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m",Member.class).getResultList();
    }




}
