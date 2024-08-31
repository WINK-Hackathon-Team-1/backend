package com.example.demo.albamon.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.ToString;

@Entity
@Table(name = "albamon")
@ToString
@Getter
public class Albamon {

    @Id @GeneratedValue
    private Integer id;
    private String title;
    private String status;
    private String company;
    private String payType;
    private String pay;
    private Boolean negoPay;
    private String workingTime;
    private String workingPeriod;
    private String negoWorkPeriod;
    private String workingWeek;
    private String workingDate;
    private Boolean trainee;
    private String parts;
    private String closingDate;
    private String closingDateWithDDay;
    private String closingDDay;
    private String age;
    private Float latitude;
    private Float longitude;
    private Boolean existsMap;
    private String workplaceAddress;
    private String workplaceArea;


}
