package com.example.demo.hello.controller;

import com.example.demo.hello.domain.Member;
import com.example.demo.hello.repository.MemberRepository;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor

public class MemberController {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/members")
    public String saveMemberV2(@RequestBody MemberDto request){

        String hash = passwordEncoder.encode(request.getPassword());

        Member member = new Member(request.getName(),hash);

        Long id = memberRepository.save(member);

        return request.name;
    }

    @GetMapping("/members")
    public List<Member> getMembers(){
        return memberRepository.findAll();
    }

    @GetMapping("/user-info")
    public Map<String, Object> getUserInfo() {
        Map<String, Object> response = new HashMap<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            response.put("username", user.getUsername());
            // 필요에 따라 추가적인 사용자 정보 포함
            response.put("authorities", user.getAuthorities());
        } else {
            response.put("error", "User not authenticated");
        }

        return response;


    }

    @GetMapping("/members/{name}")
    public Member getMemberName(@PathVariable String name){
        return memberRepository.findByname(name);
    }



    @DeleteMapping("/delete/{id}")
    public Member deleteMember(@PathVariable Long id){

        Member member = memberRepository.findById(id);

        //memberRepository.delete(member);

        return member;
    }



    @GetMapping("/")
    public String showAllMembers(){
        return "members";
    }

    @Data
    @Getter
    public static class MemberDto {
        public String name;
        public String password;

    }

}
