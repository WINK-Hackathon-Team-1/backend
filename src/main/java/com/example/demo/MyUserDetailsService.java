package com.example.demo;

import com.example.demo.member.domain.Member;
import com.example.demo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;



    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        if (userId == null) {

            throw new UsernameNotFoundException("User not found with username: " + userId);
        }


        List<Member> members = memberRepository.findByUserId(userId);

        if (members.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + userId);
        }else{

            Member member = members.get(0);
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            grantedAuthorities.add(new SimpleGrantedAuthority("일반유저"));

            return new User(member.getUserId(),member.getPassword(),grantedAuthorities);

        }


    }
}
