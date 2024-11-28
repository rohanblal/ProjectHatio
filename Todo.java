package main;

import java.util.UUID;

public class Todo {
	private String id;
	private String description;
	private String status;
	private String createdDate;
	private String updatedDate;

	public Todo(String description, String createdDate) {
		this.id = UUID.randomUUID().toString();
		this.description = description;
		this.status = "Pending";
		this.createdDate = createdDate;
		this.updatedDate = createdDate;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

}
