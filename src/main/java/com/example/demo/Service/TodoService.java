package com.example.demo.Service;

import java.util.List;

//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Todo;
import com.example.demo.Repository.TodoRepository;

@Service
public class TodoService {
	
	public TodoRepository todoRepository;
	
	

	public TodoService(TodoRepository todoRepository){
		this.todoRepository = todoRepository;
	}

	public List<Todo> create(Todo todo){
		todoRepository.save(todo);
		return list();
	}

	public List<Todo> list(){
		Sort sort = Sort.by("prioridade").descending().and(
			Sort.by("nome")
		);
		
		return todoRepository.findAll(sort);
	}

	public List<Todo> update(Todo todo){
		todoRepository.save(todo);
		return list();

	}

	public List<Todo> delete(Long id){
		todoRepository.deleteById(id);
		return list();

	}

}
