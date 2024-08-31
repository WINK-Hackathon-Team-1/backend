package com.example.demo;

import com.example.demo.hello.domain.Member;
import com.example.demo.hello.repository.MemberRepository;
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
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        if (name == null) {
            throw new UsernameNotFoundException("User not found with username: " + name);
        }


        Member member = memberRepository.findByname(name);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(new SimpleGrantedAuthority("일반유저"));

        return new User(member.getName(),member.getPassword(),grantedAuthorities);
    }
}
