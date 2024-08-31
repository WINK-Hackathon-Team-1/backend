package com.example.demo.member.domain;

import com.example.demo.community.domain.Community;
import com.example.demo.communityList.domain.CommunityList;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String userId;

    private String name;

    private String password;

    @OneToMany(mappedBy = "member")
    private List<CommunityList> communityList = new ArrayList<>();

    public Member(String userId, String name, String password) {
        this.name = name;
        this.password = password;
        this.userId = userId;
    }

    protected Member() {}
}
