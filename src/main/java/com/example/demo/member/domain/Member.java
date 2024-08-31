package com.example.demo.member.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    public Member(String userId, String name, String password) {
        this.name = name;
        this.password = password;
        this.userId = userId;
    }

    protected Member() {}
}
