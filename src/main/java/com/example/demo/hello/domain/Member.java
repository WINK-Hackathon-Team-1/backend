package com.example.demo.hello.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    private String password;

    public Member(String name,String password) {
        this.name = name;
        this.password = password;
    }

    protected Member() {}
}
