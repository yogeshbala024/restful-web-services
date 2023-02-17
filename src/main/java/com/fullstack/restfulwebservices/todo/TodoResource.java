package com.fullstack.restfulwebservices.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@CrossOrigin
@RestController
public class TodoResource {
    @Autowired
    private TodoService todoService;

    @GetMapping("users/{userName}/todos")
    public List<Todo> getAllTodos(@PathVariable String userName){
        return this.todoService.findAll();
    }

    @GetMapping("users/{userName}/todos/{id}")
    public Todo getTodo(@PathVariable String userName,@PathVariable long id){
        return this.todoService.findById(id);
    }

    @PutMapping("users/{userName}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(
            @PathVariable String userName,
            @PathVariable long id,
            @RequestBody Todo todo){
        Todo todoUpdated = this.todoService.save(todo);
        return new ResponseEntity<Todo>(todoUpdated, HttpStatus.OK);

    }

    @PostMapping("users/{userName}/todos")
    public ResponseEntity<Void> createTodo(@PathVariable String userName, @RequestBody Todo todo){
        Todo newTodo = this.todoService.save(todo);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newTodo.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }
    @DeleteMapping("users/{userName}/todos/{id}")
    public ResponseEntity<Todo> deleteTodoById(@PathVariable String userName, @PathVariable long id){
        Todo todo = this.todoService.deleteById(id);
        if(todo!= null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();

    }
}
