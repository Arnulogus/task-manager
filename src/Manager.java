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
}
