package main;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Project {
	private String id;
	private String title;
	private String createdDate;
	private List<Todo> todos;

	public Project(String title, String createdDate) {
		this.id = UUID.randomUUID().toString();
		this.title = title;
		this.createdDate = createdDate;
		this.todos = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public List<Todo> getTodos() {
		return todos;
	}

	public void addTodo(Todo todo) {
		todos.add(todo);
	}

	public void removeTodo(String todoId) {
		todos.removeIf(todo -> todo.getId().equals(todoId));
	}
}
