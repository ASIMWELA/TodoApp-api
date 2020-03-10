package com.todoapp.Todoapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Column(name="title", length = 50)
    private String title;

    @Column(name="description", length = 150)
    private String description;

    @Column(name="completed", length = 6)
    private Boolean completed;

    @Column(name="creation_date", length = 15)
    private LocalDate dateCreated;

    @Column(name="modified_date", length = 15)
    private LocalDate dateModified;

    @Column(name="important")
    private Boolean important;

    @Column(name="urgent")
    private Boolean urgent;

    @Column(name="priority", length = 2)
    private int priority;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
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

    public ToDo(String title, String description, Boolean completed, LocalDate dateCreated, LocalDate dateModified, Boolean important, Boolean urgent, int priority)
    {
        this.title = title;
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

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
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
        return LocalDate.now();
    }

    public void setDateCreated(LocalDate dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateModified()
    {
        return LocalDate.now();
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
