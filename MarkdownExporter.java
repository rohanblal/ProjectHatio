package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MarkdownExporter {

    public static String generateProjectSummary(Project project) {
        StringBuilder markdown = new StringBuilder();

        markdown.append("# ").append(project.getTitle()).append("\n\n");

        List<Todo> todos = project.getTodos();
        long completedCount = todos.stream().filter(todo -> todo.getStatus().equalsIgnoreCase("Completed")).count();
        markdown.append("Summary: ").append(completedCount).append("/").append(todos.size()).append(" todos completed\n\n");

        markdown.append("## Pending\n");
        List<Todo> pendingTodos = todos.stream().filter(todo -> !todo.getStatus().equalsIgnoreCase("Completed")).collect(Collectors.toList());

        if (pendingTodos.isEmpty()) {
            markdown.append("No pending tasks.\n");
        } else {
            for (Todo todo : pendingTodos) {
                markdown.append(" [ ] ").append(todo.getDescription()).append("\n");
            }
        }

        markdown.append("\n");
        markdown.append("## Completed\n");
        List<Todo> completedTodos = todos.stream().filter(todo -> todo.getStatus().equalsIgnoreCase("Completed")).collect(Collectors.toList());

        if (completedTodos.isEmpty()) {
            markdown.append("No completed tasks.\n");
        } else {
            for (Todo todo : completedTodos) {
                markdown.append(" [x] ").append(todo.getDescription()).append("\n");
            }
        }

        return markdown.toString();
    }
    
    public static void saveToFile(String content, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
            System.out.println("Project summary saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving project summary to file: " + e.getMessage());
        }
    }
}
