package com.crud.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.model.Todo;
import com.crud.service.TodoService;

@RestController
public class TodoController {
    
    @Autowired
    TodoService todoService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/todo")
    private List<Todo> getAllTodo()
    {
        return todoService.getAllTodo();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/todo/{id}")
    private Todo getTodo(@PathVariable("id") int id)
    {
        return todoService.getTodoById(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/todo/{id}")
    private void deleteTodo(@PathVariable("id") int id) {
        todoService.delete(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/todo")
    private int saveTodo(@RequestBody Todo todo) {
        todoService.saveOrUpdate(todo);
        return todo.getId();
    }
}
