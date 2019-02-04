package com.repository.UserService;

import com.entities.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public User findUserByName(String username) {
        return  userRepository.findUserByName(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
