package com.example.demo.member.repository;

import com.example.demo.member.domain.Member;
import jakarta.persistence.EntityManager;
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



    public List<Member> findByUserId(String userId) {
        List<Member> members =  em.createQuery("select m from Member m where m.userId = :userId")
                .setParameter("userId", userId)
                .getResultList();

        return members;
    }



    public void editMember(String name,String password,String userId) {

        String query = "update Member m set m.password = :password, m.name = :name " +
                "where m.userId = :userId";

        em.createQuery(query)
                .setParameter("password", password )
                .setParameter("name", name)
                .setParameter("userId",userId)
                .executeUpdate();

    }

    public void delete(Member member) {
        em.remove(member);
    }

    public Member findByname(String name){

        String query = "select m from Member m where m.name = :name";

        return em.createQuery(query,Member.class).
                setParameter("name",name).getSingleResult();
    }






}
