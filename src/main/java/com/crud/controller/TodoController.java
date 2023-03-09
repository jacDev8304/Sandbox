package com.crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crud.model.Todo;
import com.crud.service.TodoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

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
    private Todo getTodo(@PathVariable("id") @NotBlank int id)
    {
        return todoService.getTodoById(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/todo/{id}")
    private void deleteTodo(@PathVariable("id") @NotBlank int id) {
        todoService.delete(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/todo")
    private ResponseEntity<String> saveTodo(@Valid @RequestBody Todo todo) {
        System.out.println(todo);
        todoService.save(todo);
        return ResponseEntity.ok("Todo added");
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/todo/{id}")
    private ResponseEntity<String> updateTodo(@Valid @RequestBody Todo todo) {
        todoService.update(todo);
        return ResponseEntity.ok("Todo added");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
    MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
