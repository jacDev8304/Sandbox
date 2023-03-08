package com.crud.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/todo")
    private List<Todo> getAllTodo()
    {
        return todoService.getAllTodo();
    }

    @GetMapping("/todo/{id}")
    private Todo getTodo(@PathVariable("id") int id)
    {
        return todoService.getTodoById(id);
    }

    @DeleteMapping("/todo/{id}")
    private void deleteTodo(@PathVariable("id") int id) {
        todoService.delete(id);
    }

    @PostMapping("/todo")
    private int saveTodo(@RequestBody Todo todo) {
        todoService.saveOrUpdate(todo);
        return todo.getId();
    }
}
