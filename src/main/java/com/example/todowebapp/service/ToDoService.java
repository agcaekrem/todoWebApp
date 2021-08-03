package com.example.todowebapp.service;

import com.example.todowebapp.model.ToDo;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ToDoService {

    List<ToDo>getToDoByUser(String user);
    Optional<ToDo> getToDoById(long id);
    void updateToDo(ToDo toDo);
    void addToDo(long userId, Date startDate,Date targetDate,String workDescription,String userName);
    void deleteToDo(long id);
    void saveToDo(@Valid ToDo toDo);

}

