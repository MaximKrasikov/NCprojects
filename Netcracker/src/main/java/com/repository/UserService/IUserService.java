package com.repository.UserService;

import com.entities.User;
import com.forms.UserDto;

/**
 * Created by Admin on 20.02.2019.
 */
public interface IUserService {
    User registerNewUserAccount(UserDto accountDto);
    boolean checkIfValidPassword(User user, String password);
}
