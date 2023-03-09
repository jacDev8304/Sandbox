package com.crud.repository;  

import com.crud.model.Todo;  

import org.springframework.data.repository.CrudRepository;  

public interface TodoRepository extends CrudRepository<Todo, Integer>  {}  