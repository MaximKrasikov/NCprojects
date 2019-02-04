package com.repository.UserService;

import com.entities.User;

/**
 * Created by Admin on 04.02.2019.
 */
public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
