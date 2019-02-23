package com.entities;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Admin on 04.02.2019.
 */
/*будет определять роли пользователя*/
public enum Role implements GrantedAuthority {
    ADMIN,
    USER;

    @Override
    public String getAuthority() {
        return name();
    }

    Role(){
    }
}