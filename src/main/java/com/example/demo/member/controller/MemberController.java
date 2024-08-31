package com.example.demo.member.controller;

import com.example.demo.member.domain.Member;
import com.example.demo.member.repository.MemberRepository;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    @PostMapping("/members")
    public String saveMemberV2(@RequestBody MemberDto request) throws Exception {

        if (!memberRepository.findByUserId(request.userId).isEmpty()){
            return "회원가입 실패";
        }else{
            String hash = passwordEncoder.encode(request.getPassword());

            Member member = new Member(request.getUserId(), request.getName(), hash);

            Long id = memberRepository.save(member);

            return "회원가입 성공";
        }




    }


    //회원조회
    @GetMapping("/user-info")
    public Map<String, Object> getUserInfo() {
        Map<String, Object> response = new HashMap<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof User user) {
            response.put("userId", user.getUsername());

            // 필요에 따라 추가적인 사용자 정보 포함
            response.put("authorities", user.getAuthorities());
        } else {
            response.put("error", "User not authenticated");
        }

        return response;


    }




    //회원 아이디로 조회
    @GetMapping("/members/{userId}")
    public Member getUserIdMember(@PathVariable String userId){
        List<Member> members = memberRepository.findByUserId(userId);
        Member member = members.get(0);
        return member;
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/member/edit")
    public String editMember(@RequestBody MemberEditDto request) throws Exception {

        List<Member> findMembers = memberRepository.findByUserId(request.userId);
        Member findMember = findMembers.get(0);

        if (!passwordEncoder.matches(request.nowPassword, findMember.getPassword() )){
            return "현재 비밀번호가 틀렸습니다";
        }else{

            String hash = passwordEncoder.encode(request.newPassword);

            memberRepository.editMember(request.getName(),
                    hash, request.getUserId());

            return "회원수정성공";
        }



    }


    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/delete/{userId}")
    public void deleteMember(@PathVariable String userId) throws Exception {

        if (userId == null){
            throw new Exception("유저 아이디가 없습니다!");
        }

        List<Member> members = memberRepository.findByUserId(userId);

        Member member = members.get(0);

        memberRepository.delete(member);

    }


    @Data
    @Getter
    public static class MemberDto {
        public String userId;
        public String name;
        public String password;

    }

    @Data
    @Getter
    public static class MemberEditDto {
        public String nowPassword;
        public String newPassword;
        public String name;
        public String userId;

    }

}
