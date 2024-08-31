package com.example.demo.member.controller;

import com.example.demo.member.domain.Member;
import com.example.demo.member.repository.MemberRepository;

import org.junit.jupiter.api.Test;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.assertj.core.api.Assertions;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)

class MemberControllerTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    public void 회원가입() throws Exception{
        //given
        Member member = new Member("bassrkd", "rlaeh", "1234");

        //when
        Long id = memberRepository.save(member);

        //then
        Assertions.assertThat(id).isNotNull();

    }
}