package com.todoapp.Todoapp.repository;

import com.todoapp.Todoapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>
{
    User findByUserName(String userName);
    Boolean existsByUserName(String userName);
}
