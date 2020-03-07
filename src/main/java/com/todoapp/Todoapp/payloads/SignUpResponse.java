package com.todoapp.Todoapp.payloads;

public class SignUpResponse
{
    private String message;


    public SignUpResponse(String message)
    {
        this.message = message;
    }

    public SignUpResponse()
    {

    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
