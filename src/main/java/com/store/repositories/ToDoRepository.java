package com.store.repositories;

import org.springframework.data.repository.CrudRepository;

import com.store.model.ToDo;

public interface ToDoRepository extends CrudRepository<ToDo, String> {
}
