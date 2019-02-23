package com.repository.UserService;

import com.entities.Role;
import com.entities.User;
import com.forms.UserDto;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 20.02.2019.
 */
@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public User registerNewUserAccount(UserDto accountDto){
        User user = new User();
        user.setUsername(accountDto.getUsername());
        user.setPassword(accountDto.getPassword());
        Set<Role> roles = new HashSet<Role>();
        user.setActive(true);
        user.setRoles(roles);
        user.addRole(Role.ADMIN);
        return repository.save(user);
    }

    @Override
    public boolean checkIfValidPassword(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }
}
