package com.todoapp.Todoapp.controller;


import com.todoapp.Todoapp.exception.UserNotLoggedInException;
import com.todoapp.Todoapp.model.ToDo;
import com.todoapp.Todoapp.model.User;
import com.todoapp.Todoapp.repository.ToDoRepository;
import com.todoapp.Todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
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

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();

        if ((principal instanceof UserDetails) && (user.getUserName().equals(username)) )
        {
            todo.setUser(user);
            toDoRepository.save(todo);

            List todos = user.getTodos();
            todos.add(todo);
            user.setTodos(todos);

            userRepository.save(user);
        }
        else
        {
            throw new UserNotLoggedInException("User not logged in");
        }

        return user;
    }

}
