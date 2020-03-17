package com.todoapp.Todoapp.controller;


import com.todoapp.Todoapp.exception.ResourceNotFoundException;
import com.todoapp.Todoapp.exception.UserNotLoggedInException;
import com.todoapp.Todoapp.model.ToDo;
import com.todoapp.Todoapp.model.User;
import com.todoapp.Todoapp.payloads.ToDoListResponse;
import com.todoapp.Todoapp.repository.ToDoRepository;
import com.todoapp.Todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @PutMapping({"/add-todo/{userName}"})
    @ResponseStatus(HttpStatus.CREATED)
    public User createToDO(@RequestBody ToDo todo, @PathVariable("userName") String userName)
    {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String authUsername = ((UserDetails)principal).getUsername();

        User user = null;
        if(userRepository.existsByUserName(userName))
        {
            user = userRepository.findByUserName(userName);
            if ((principal instanceof UserDetails) && (user.getUserName().equals(authUsername)) )
            {

                todo.setUser(user);
                toDoRepository.save(todo);

                List<ToDo> todos = user.getTodos();
                todos.add(todo);
                user.setTodos(todos);

                userRepository.save(user);
            }
            else
            {
                throw new UserNotLoggedInException("User not logged in or wrong token");
            }


        }
        else{
            throw new UsernameNotFoundException("No user with username " + userName);
        }
        return user;

    }

    @GetMapping({"/users/{userId}"})
    public List<?> getToDos(@PathVariable("userId") int userId)
    {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("no user with id " + userId));
        List<ToDo> userTodos;
        ToDoListResponse userAndTodo = new ToDoListResponse();

        List<?> userNameTodo;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();

        if ((principal instanceof UserDetails) && (user.getUserName().equals(username)) )
        {
            userTodos = user.getTodos();

            userNameTodo = userAndTodo.getUserTodos(username, userTodos);
        }
        else
        {
            throw new UserNotLoggedInException("User not logged in or wrong token");
        }


        return userNameTodo;
    }

}
