package com.epam.library.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccountDto {

    private long accountId;
    private String name;
    private String email;
    private long balance;

}
