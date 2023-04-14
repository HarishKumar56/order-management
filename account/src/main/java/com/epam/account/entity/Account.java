package com.epam.account.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Account {

    @Id
    @GeneratedValue
    private long accountId;
    private String name;
    private String email;
    private long balance;
}
