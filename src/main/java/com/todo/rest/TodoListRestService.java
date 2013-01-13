package com.todo.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.todo.domain.TodoList;
import com.todo.repository.TodoListRepository;
import com.todo.service.TodoListService;

@Path("/todolists")
public class TodoListRestService {

	@Inject
	private TodoListService todoListService;

	@Inject
	private TodoListRepository todoListRepository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TodoList> listAllTodoLists() {
		return todoListRepository.findAllTodoListsOrderedByCreationDate();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public TodoList lookupTodoListById(@PathParam("id") long id) {
		TodoList todoList = todoListRepository.findById(id);
		if (todoList == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return todoList;
	}
	
	

}
