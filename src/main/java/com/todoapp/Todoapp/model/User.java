package com.todoapp.Todoapp.model;


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
    private int userId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="user_name")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="roles")
    private String roles;

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

    public int getUserId()
    {
        return this.userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getUserName()
    {
        return this.userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return this.password;
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
