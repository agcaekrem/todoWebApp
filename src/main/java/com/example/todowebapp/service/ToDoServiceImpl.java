package com.example.todowebapp.service;

import com.example.todowebapp.model.ToDo;
import com.example.todowebapp.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoServiceImpl implements ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    @Override
    public List<ToDo> getToDoByUser(String user) {
        List<ToDo> toDoList = new ArrayList<ToDo>();
        for (ToDo toDo : toDoRepository.findByUserName(user))
        {
            toDoList.add(toDo);
        }
        return toDoList;
    }

        @Override
    public Optional<ToDo> getToDoById(long id) {
        return toDoRepository.findById(id);
    }

    @Override
    public void updateToDo(ToDo toDo) {
      toDoRepository.save(toDo);
    }

    @Override
    public void addToDo(long userId, Date startDate, Date targetDate, String workDescription, String userName) {

        toDoRepository.save(new ToDo(userId,startDate,targetDate,workDescription,userName));

   }

    @Override
    public void deleteToDo(long id) {
     Optional<ToDo> todo =toDoRepository.findById(id);
     if (todo.isPresent()){
         toDoRepository.delete(todo.get());
     }

    }

    @Override
    public void saveToDo(@Valid ToDo toDo) {
     toDoRepository.save(toDo);
    }
}
