package com.yswdqz.library.model;

import lombok.Data;

@Data
public class Administrator {
    private Integer id;
    private String name;
    private Character gender;
    private String password;
    private String contactInfo;
    private String accountNumber;
}
