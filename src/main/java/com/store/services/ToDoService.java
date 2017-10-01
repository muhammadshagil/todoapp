package com.store.services;

import java.util.List;

import com.store.model.ToDo;

public interface ToDoService {

    List<ToDo> listAll();

    ToDo getById(String id);
    ToDo saveOrUpdate(ToDo todo);
    void deleteAll();
    void delete(ToDo todo);
    void delete(int id);
}
