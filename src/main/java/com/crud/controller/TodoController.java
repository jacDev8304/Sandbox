package com.crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @GetMapping("/todo")
    private List<Todo> getAllTodo()
    {
        return todoService.getAllTodo();
    }

    @GetMapping("/todo/{id}")
    private Todo getTodo(@Valid @PathVariable("id") @NotBlank int id)
    {
        if (todoService.getExistsById(id)) {
            return todoService.getTodoById(id);
        }
        throw new TodoNotFoundException();   
    }

    @DeleteMapping("/todo/{id}")
    private ResponseEntity<String> deleteTodo(@Valid @PathVariable("id") @NotBlank int id) {
        if (todoService.getExistsById(id)) {
            todoService.delete(id);
            return ResponseEntity.ok(id + " Deleted!");
        }
        return ResponseEntity.badRequest().body("Todo does not exist!");
    }

    @PostMapping("/todo")
    private ResponseEntity<String> saveTodo(@Valid @RequestBody Todo todo) {
        if (todoService.getExistsById(todo.getId())) {
            return ResponseEntity.badRequest().body("id already exists!");
        }
        // TODO: refactor duplicate checks
        if (todo.getTitle().length() == 0) {
            return ResponseEntity.badRequest().body("Title can not be blank");
        }
        if (todo.getDescription().length() == 0) {
            return ResponseEntity.badRequest().body("Description can not be blank");
        }

        todoService.save(todo);
        return ResponseEntity.ok("Todo added " + todo.getId());
    }

    @PutMapping("/todo/{id}")
    private ResponseEntity<String> updateTodo(@Valid @PathVariable("id") @NotBlank int id, @RequestBody Todo todo) {
        if (todoService.getExistsById(id)) {
            // TODO: refactor duplicate checks
            if (todo.getTitle().length() == 0) {
                return ResponseEntity.badRequest().body("Title can not be blank");
            }
            if (todo.getDescription().length() == 0) {
                return ResponseEntity.badRequest().body("Description can not be blank");
            }
            todo.setId(id);
            todoService.update(todo);
            return ResponseEntity.ok("id: " + id + ", Todo updated");
        }
        return ResponseEntity.badRequest().body("Todo does not exist, cannot be updated");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, IllegalArgumentException.class})
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

    @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Todo")  // 404
    public class TodoNotFoundException extends RuntimeException {}
}
