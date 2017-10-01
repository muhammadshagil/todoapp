package com.store.todo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.model.ToDo;
import com.store.services.ToDoService;

@RestController
public class AppController {
	@Autowired
	private ToDoService toDoService;
	
	@RequestMapping(value="/api")
	public List<String> list(){
		List<String> newList = new ArrayList<String>();
		newList.add("test");
		return newList;
	}
	
	@RequestMapping(value = "/todo/list", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<ToDo> getToDos(){
        return toDoService.listAll();
    }
	
	@RequestMapping(value="/todo/add", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
	public String savetoDo(@RequestBody ToDo toDo){
			if(null != toDo){
				toDoService.saveOrUpdate(toDo);
			}
			return "{ToDO added}";
	}
	@RequestMapping(value = "/todo/deleteAll")
    public String deleteAllTodos(){
        toDoService.deleteAll();
        return "All products are deleted";
    }
	
	@RequestMapping(value = "/todo/delete", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE , produces= MediaType.APPLICATION_JSON_VALUE)
    public String delete(ToDo todo){
        toDoService.delete(todo.getId());
        toDoService.delete(todo);
        return "{deleted todo}";
    }
}
