package com.todoapp.Todoapp.security;

import com.todoapp.Todoapp.model.User;
import com.todoapp.Todoapp.model.UserPrincipal;
import com.todoapp.Todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
    {
        User user = userRepository.findByUserName(s);
        UserPrincipal userPrincipal = new UserPrincipal(user);
        return userPrincipal;
    }

}
