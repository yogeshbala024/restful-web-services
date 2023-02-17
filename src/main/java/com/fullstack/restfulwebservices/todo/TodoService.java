package com.fullstack.restfulwebservices.todo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TodoService {

    private static List<Todo> todos = new ArrayList<>();
    private static Long idCounter = 0L;
    static {
        todos.add(new Todo(++idCounter, "Bala", "Music Bajao", new Date(), false));
        todos.add(new Todo(++idCounter, "Bala", "nacho nacho", new Date(), false));
        todos.add(new Todo(++idCounter, "Bala", "job kidar?", new Date(), false));
    }



    public List<Todo> findAll(){
        return todos;
    }

    public Todo save(Todo todo){
        if(todo.getId() == -1 || todo.getId() == 0){
            todo.setId( ++idCounter);
            todos.add(todo);
        }else{
            deleteById(todo.getId());
            todos.add(todo);
        }
        return todo;
    }

    public Todo deleteById(long id){
        Todo todo = findById(id);
        if(todo== null) return null;

        if(todos.remove(todo))
            return todo;

        return null;

    }

    Todo findById(long id){
        for(Todo todo : todos){
            if(todo.getId() == id)
                return todo;
        }
        return null;
    }



}
