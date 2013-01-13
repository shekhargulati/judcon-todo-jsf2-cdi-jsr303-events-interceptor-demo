package com.todo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

@Entity
public class TodoList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Size(min = 10, max = 20)
	private String name;

	@NotNull
	@Email
	private String email;

	@NotNull
	private Date createdOn = new Date();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@OrderColumn
	@JoinColumn(name = "todolist_id")
	private List<Todo> todos = new ArrayList<Todo>();

	@ElementCollection
	@CollectionTable(name = "Tags", joinColumns = @JoinColumn(name = "todolist_id"))
	@Column(name = "tag")
	@NotNull
	private List<String> tags;

	public TodoList(String name, List<Todo> todos, List<String> tags) {
		super();
		this.name = name;
		this.todos = todos;
		this.tags = tags;
	}

	public TodoList() {
		super();
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public List<Todo> getTodos() {
		return todos;
	}

	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "TodoList [id=" + id + ", name=" + name + ", tags=" + tags + "]";
	}

}
