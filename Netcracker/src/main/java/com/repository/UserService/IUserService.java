package com.repository.UserService;

import com.entities.User;
import com.forms.UserDto;

import java.util.List;

/**
 * Created by Admin on 20.02.2019.
 */
public interface IUserService {
    User registerNewUserAccount(UserDto accountDto);
    boolean checkIfValidPassword(User user, String password);

    /*======Rest API=======*/
    List<User> findAllUsers();
    boolean isUserExist(User user);
    User findByName(String name);
    User findById(long id);
    void saveUser(User user);
    void updateUser(User currentUser);
    void deleteUserById(long id);
    void deleteAllUsers();
}
