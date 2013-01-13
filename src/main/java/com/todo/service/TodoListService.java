package com.todo.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.todo.domain.TodoList;

@Stateless
public class TodoListService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Inject
	Event<TodoList> todolistEvent;

	public TodoList create(TodoList todoList) {
		entityManager.persist(todoList);
		todolistEvent.fire(todoList);
		return todoList;
	}

	public TodoList find(Long id) {
		TodoList todoList = entityManager.find(TodoList.class, id);
		List<String> tags = todoList.getTags();
		System.out.println("Tags : " + tags);
		return todoList;
	}

}
