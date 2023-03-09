package com.crud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.model.Todo;
import com.crud.repository.TodoRepository;

@Service
public class TodoService {
    
    @Autowired
    TodoRepository todoRepository;

    public List<Todo> getAllTodo() {
        List<Todo> todos = new ArrayList<Todo>();
        todoRepository.findAll().forEach(todo -> todos.add(todo));
        return todos;
    }

    public Todo getTodoById(int id) {
        return todoRepository.findById(id).get();
    }

    public Boolean getExistsById(int id) {
        return todoRepository.existsById(id);
    }

    public void save(Todo todo) {
        todoRepository.save(todo);
    }
    
    public void update(Todo todo) {
        todoRepository.save(todo);
    }

    public void delete(int id) {
        todoRepository.deleteById(id);
    }
}
