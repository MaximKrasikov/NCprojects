package com.repository.UserService;

import com.entities.Phones;
import com.entities.User;
import com.repository.PhoneRepository;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 04.02.2019.
 */
@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PhoneRepository phoneRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
       return userRepository.findByUsername(s);
    }

    public Phones getPhoneById(long id){
        return phoneRepository.findPhoneById(id);
    }

    public void save(User user ){
        userRepository.save(user);
    }
}
