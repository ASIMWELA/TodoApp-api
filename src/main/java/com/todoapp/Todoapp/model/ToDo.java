package com.todoapp.Todoapp.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="todo")
public class ToDo
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="todo_id")
    private int id;

    @Column(name="description")
    private String description;

    @Column(name="completed")
    private Boolean completed;

    @Column(name="creation_date")
    private LocalDate dateCreated;

    @Column(name="modified_date")
    private LocalDate dateModified;

    @Column(name="important")
    private Boolean important;

    @Column(name="urgent")
    private Boolean urgent;

    @Column(name="priority")
    private int priority;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public ToDo(String description, Boolean completed, LocalDate dateCreated, LocalDate dateModified, Boolean important, Boolean urgent, int priority)
    {
        this.description = description;
        this.completed = completed;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.important = important;
        this.urgent = urgent;
        this.priority = priority;
    }

    public ToDo()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Boolean getCompleted()
    {
        return completed;
    }

    public void setCompleted(Boolean completed)
    {
        this.completed = completed;
    }

    public LocalDate getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateModified()
    {
        return dateModified;
    }

    public void setDateModified(LocalDate dateModified)
    {
        this.dateModified = dateModified;
    }

    public Boolean getImportant()
    {
        return important;
    }

    public void setImportant(Boolean important)
    {
        this.important = important;
    }

    public Boolean getUrgent()
    {
        return urgent;
    }

    public void setUrgent(Boolean urgent)
    {
        this.urgent = urgent;
    }

    public int getPriority()
    {
        return priority;
    }

    public void setPriority(int priority)
    {
        this.priority = priority;
    }

}
