package com.crud.repository;  

import org.springframework.data.repository.CrudRepository;  
import com.crud.model.Todo;  
public interface TodoRepository extends CrudRepository<Todo, Integer>  
{  
}  