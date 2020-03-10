package com.todoapp.Todoapp.payloads;

import com.todoapp.Todoapp.model.ToDo;

import java.util.*;

public class ToDoListResponse<T, X>
{
    private T userName;
    private X todos;


    public ToDoListResponse()
    {
    }


    public ToDoListResponse(T userName, X todos)
    {
        this.userName = userName;
        this.todos = todos;
    }

    public List<ToDoListResponse> getUserTodos(T user, X todo)
    {

        List userTodos = new ArrayList<>();

        userTodos.add(0,user);
        userTodos.add(todo);

        return userTodos;
    }


}
