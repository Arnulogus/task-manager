import java.util.HashMap;
import java.util.Scanner;

public class Manager {
    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, Subtask> subtasks;
    private HashMap<Integer, Epic> epics;
    private Long id;

    Scanner scanner;

    public Manager() {
        id = 0L;
        tasks = new HashMap<>();
        subtasks = new HashMap<>();
        epics = new HashMap<>();
    }

    private Long getId() {
        id++;
        return id;
    }

    public Object getTasks() {
        return tasks.values();
    }

    public Object getSubtasks() {
        return subtasks.values();
    }

    public Object getEpics() {
        return epics.values();
    }

    public void deleteTasks() {
        if (!tasks.isEmpty()) {
            tasks.clear();
        }
    }

    public void deleteEpics() {
        if (!epics.isEmpty()) {
            epics.clear();
        }
    }

    public void deleteSubtasks() {
        for (Epic epic : epics.values()) {
            epic.setStatus("NEW");
        }
        if (!subtasks.isEmpty()) {
            subtasks.clear();
        }
    }
}
