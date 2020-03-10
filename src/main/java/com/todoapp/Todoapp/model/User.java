package com.todoapp.Todoapp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name="user")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private int userId;

    @Column(name="first_name",length = 50)
    private String firstName;

    @Column(name="last_name", length = 70)
    private String lastName;

    @Column(name="user_name", length = 20)
    private String userName;

    @JsonIgnore
    @Column(name="password", length = 100)
    private String password;

    @JsonIgnore
    @Column(name="roles")
    private String roles;

    @OneToMany(mappedBy = "user")
    private List<ToDo> todos;

    public User(String firstName, String lastName, String userName, String password, String roles)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    public User()
    {
    }

    public List<ToDo> getTodos()
    {
        return todos;
    }

    public void setTodos(List<ToDo> todos)
    {
        this.todos = todos;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setRoles(String roles)
    {
        this.roles = roles;
    }

    public String getRoles()
    {
        return this.roles;
    }
    public List<String> getRolesList()
    {
        if(this.roles.length() > 0)
        {
            return Arrays.asList(this.roles.split(","));
        }

        return new ArrayList<>();
    }

}
