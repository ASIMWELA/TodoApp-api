package com.todoapp.Todoapp.controller;


import com.todoapp.Todoapp.model.ToDo;
import com.todoapp.Todoapp.model.User;
import com.todoapp.Todoapp.repository.ToDoRepository;
import com.todoapp.Todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/todo/api"})
public class TodoController
{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ToDoRepository toDoRepository;

    @PutMapping({"/add-todo/{userId}"})
    public User createToDO(@RequestBody ToDo todo, @PathVariable("userId") int userId)
    {
        User user = userRepository.getOne(userId);
        if(user==null)
        {
            throw new UsernameNotFoundException("no user with id " + userId);
        }

        todo.setUser(user);
        toDoRepository.save(todo);

        List todos = user.getTodos();
        todos.add(todo);
        user.setTodos(todos);

        userRepository.save(user);

        return user;
    }

}
