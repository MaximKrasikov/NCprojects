package com.repository.UserService;

import com.entities.User;

public interface UserService {
    User findUserByName(String username);

    void save(User user);
}
