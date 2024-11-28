package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	 private static List<Project> projects = new ArrayList<>();
	 private static Scanner scanner = new Scanner(System.in);
	    
	 public static void main(String[] args) {
		 if (!Authentication.login()) {
	            System.out.println("Authentication failed..!!");
	            return;
	        }
		 
	        Scanner scanner = new Scanner(System.in);

	        System.out.println("Welcome to Project Manager");
	        while (true) {
	            System.out.println("\nMenu:");
	            System.out.println("1. Create a New Project");
	            System.out.println("2. List All Projects");
	            System.out.println("3. View a Project");
	            System.out.println("4. Exit");
	            System.out.print("Enter your choice: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine();

	            switch (choice) {
	                case 1:
	                    createNewProject();
	                    break;
	                case 2:
	                    listAllProjects();
	                    break;
	                case 3:
	                    viewProject();
	                    break;
	                case 4:
	                	System.out.println("Exiting");
	                    return;
	                default:
	                    System.out.println("Invalid choice.");
	            }
	        }
	    }
	 
	 	private static void createNewProject() {
		    System.out.println("\n--- Create a New Project ---");
		    System.out.print("Enter project title: ");
		    String title = scanner.nextLine();
		    String createdDate = java.time.LocalDate.now().toString();

		    Project project = new Project(title, createdDate);
		    projects.add(project);

		    System.out.println("Project created successfully!");
		}
	 	
	 	private static void listAllProjects() {
	 	    System.out.println("\n--- List of Projects ---");
	 	    if (projects.isEmpty()) {
	 	        System.out.println("No projects available.");
	 	        return;
	 	    }

	 	    for (int i = 0; i < projects.size(); i++) {
	 	        Project project = projects.get(i);
	 	        System.out.printf("%d. %s (Created on: %s)\n", i + 1, project.getTitle(), project.getCreatedDate());
	 	    }
	 	}
	 	
	 	private static void viewProject() {
	 	    System.out.println("\n--- View a Project ---");
	 	    if (projects.isEmpty()) {
	 	        System.out.println("No projects available.");
	 	        return;
	 	    }

	 	    listAllProjects();
	 	    System.out.print("Enter the project number to view details: ");
	 	    int projectIndex = scanner.nextInt() - 1;
	 	    scanner.nextLine();

	 	    if (projectIndex < 0 || projectIndex >= projects.size()) {
	 	        System.out.println("Invalid project number.");
	 	        return;
	 	    }

	 	    Project project = projects.get(projectIndex);
	 	    System.out.println("\nProject Details:");
	 	    System.out.println("Title: " + project.getTitle());
	 	    System.out.println("Created Date: " + project.getCreatedDate());
	 	    System.out.println("Todos: ");

	 	    if (project.getTodos().isEmpty()) {
	 	        System.out.println("  No todos available.");
	 	    } else {
	 	    	 for (int i = 0; i < project.getTodos().size(); i++) {
	                 Todo todo = project.getTodos().get(i);
	                 System.out.printf("  %d. %s [%s]\n", i + 1, todo.getDescription(), todo.getStatus());
	             }
	         }

	         System.out.println("\nTodo Menu:");
	         System.out.println("1. Add Todo");
	         System.out.println("2. Edit Todo");
	         System.out.println("3. Delete Todo");
	         System.out.println("4. Mark Todo as Complete");
	         System.out.println("5. Mark Todo as Pending");
	         System.out.println("6. Export Project to Markdown");
	         System.out.println("7. Go Back");
	         System.out.print("Enter your choice: ");
	         int choice = scanner.nextInt();
	         scanner.nextLine();

	         switch (choice) {
	             case 1:
	                 addTodoToProject(project);
	                 break;
	             case 2:
	                 editTodoInProject(project);
	                 break;
	             case 3:
	                 deleteTodoFromProject(project);
	                 break;
	             case 4:
	                 updateTodoStatus(project, "Completed");
	                 break;
	             case 5:
	                 updateTodoStatus(project, "Pending");
	                 break;
	             case 6:
	            	    String markdownContent = MarkdownExporter.generateProjectSummary(project);
	            	    MarkdownExporter.saveToFile(markdownContent, project.getTitle() + ".md");
	                 break;
	             case 7:
	                 return;
	             default:
	                 System.out.println("Invalid choice. Please try again.");
	 	        }
	 	    }

	 	private static void addTodoToProject(Project project) {
	 	    System.out.println("\n--- Add a New Todo ---");
	 	    System.out.print("Enter Todo description: ");
	 	    String description = scanner.nextLine();
	 	    String createdDate = java.time.LocalDate.now().toString();

	 	    Todo todo = new Todo(description, createdDate);
	 	    project.addTodo(todo);

	 	    System.out.println("Todo added successfully!");
	 	}
	 	
	 	private static void editTodoInProject(Project project) {
	 	    System.out.println("\n--- Edit a Todo ---");
	 	    if (project.getTodos().isEmpty()) {
	 	        System.out.println("No todos available.");
	 	        return;
	 	    }

	 	    for (int i = 0; i < project.getTodos().size(); i++) {
	 	        Todo todo = project.getTodos().get(i);
	 	        System.out.printf("%d. %s [%s]\n", i + 1, todo.getDescription(), todo.getStatus());
	 	    }

	 	    System.out.print("Enter the todo number to edit: ");
	 	    int todoIndex = scanner.nextInt() - 1;
	 	    scanner.nextLine();

	 	    if (todoIndex < 0 || todoIndex >= project.getTodos().size()) {
	 	        System.out.println("Invalid todo number.");
	 	        return;
	 	    }

	 	    Todo todo = project.getTodos().get(todoIndex);
	 	    System.out.print("Enter new description: ");
	 	    String newDescription = scanner.nextLine();
	 	    todo.setDescription(newDescription);
	 	    todo.setUpdatedDate(java.time.LocalDate.now().toString());

	 	    System.out.println("Todo updated successfully!");
	 	}
	 	
	 	private static void deleteTodoFromProject(Project project) {
	 	    System.out.println("\n--- Delete a Todo ---");
	 	    if (project.getTodos().isEmpty()) {
	 	        System.out.println("No todos available.");
	 	        return;
	 	    }

	 	    for (int i = 0; i < project.getTodos().size(); i++) {
	 	        Todo todo = project.getTodos().get(i);
	 	        System.out.printf("%d. %s [%s]\n", i + 1, todo.getDescription(), todo.getStatus());
	 	    }

	 	    System.out.print("Enter the todo number to delete: ");
	 	    int todoIndex = scanner.nextInt() - 1;
	 	    scanner.nextLine();

	 	    if (todoIndex < 0 || todoIndex >= project.getTodos().size()) {
	 	        System.out.println("Invalid todo number.");
	 	        return;
	 	    }

	 	    project.removeTodo(project.getTodos().get(todoIndex).getId());
	 	    System.out.println("Todo deleted successfully!");
	 	}
	 	
	 	private static void updateTodoStatus(Project project, String status) {
	 	    System.out.println("\n--- Mark Todo as " + status + " ---");
	 	    if (project.getTodos().isEmpty()) {
	 	        System.out.println("No todos available.");
	 	        return;
	 	    }

	 	    for (int i = 0; i < project.getTodos().size(); i++) {
	 	        Todo todo = project.getTodos().get(i);
	 	        System.out.printf("%d. %s [%s]\n", i + 1, todo.getDescription(), todo.getStatus());
	 	    }

	 	    System.out.print("Enter the todo number to update: ");
	 	    int todoIndex = scanner.nextInt() - 1;
	 	    scanner.nextLine();

	 	    if (todoIndex < 0 || todoIndex >= project.getTodos().size()) {
	 	        System.out.println("Invalid todo number.");
	 	        return;
	 	    }

	 	    Todo todo = project.getTodos().get(todoIndex);
	 	    todo.setStatus(status);
	 	    todo.setUpdatedDate(java.time.LocalDate.now().toString());

	 	    System.out.println("Todo status updated to " + status + " successfully!");
	 	}
}
