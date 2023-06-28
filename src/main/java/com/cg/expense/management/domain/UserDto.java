package com.cg.expense.management.domain;

import com.cg.expense.management.repository.entity.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {

    private Long userId;

    private String userName;

    private String password;

    private Long mobile;

    private String email;

    private Set<Role> roles;
}
