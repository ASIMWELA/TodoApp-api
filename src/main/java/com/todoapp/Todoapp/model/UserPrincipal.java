package com.todoapp.Todoapp.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails
{
    private User user;

    public UserPrincipal(User user)
    {
        this.user = user;
    }
    public UserPrincipal()
    {}
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        List<GrantedAuthority> gAuth = new ArrayList<>();

        this.user.getRolesList().forEach(p->{
            GrantedAuthority au = new SimpleGrantedAuthority("ROLE_" + p);
            gAuth.add(au);
        });

        return gAuth;
    }

    @Override
    public String getPassword()
    {
        return this.user.getPassword();
    }

    @Override
    public String getUsername()
    {
        return this.user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
