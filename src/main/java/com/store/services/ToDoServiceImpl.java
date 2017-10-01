package com.store.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.model.ToDo;
import com.store.repositories.ToDoRepository;

@Service
public class ToDoServiceImpl implements ToDoService {
	
    private ToDoRepository toDoRepository;

	@Autowired
    public void setProductRepository(ToDoRepository productRepository) {
		this.toDoRepository = productRepository;
	}

	@Override
    public List<ToDo> listAll() {
        List<ToDo> products = new ArrayList<>();
        toDoRepository.findAll().forEach(products::add); 
        return products;
    }

    @Override
    public ToDo getById(String id) {
        return toDoRepository.findOne(id);
    }

    @Override
    public ToDo saveOrUpdate(ToDo product) {
        toDoRepository.save(product);
        return product;
    }
    @Override
    public void deleteAll(){
    	toDoRepository.deleteAll();
    }

	@Override
	public void delete(ToDo todo) {
		toDoRepository.delete(todo);
	}
	
	@Override
	public void delete(int id) {
		toDoRepository.delete(""+id);
	}
}
