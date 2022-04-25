package controller.imanager;

import controller.manager.InMemoryHistoryManager;
import model.entity.Epic;
import model.entity.Subtask;
import model.entity.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class HistoryManagerTest {
    private static HistoryManager historyManager;
    private final Task task = new Task("test", "test", "NEW");
    private final Epic epic = new Epic("test", "test");
    private final Subtask subtask = new Subtask("test", "test", "NEW", 2L);

    @BeforeEach
    public void beforeEach() {
        historyManager = new InMemoryHistoryManager();
        task.setId(1L);
        epic.setId(2L);
        subtask.setId(3L);
    }

    @Test
    public void shouldAdd() {
        historyManager.add(task);

        final List<Task> history = historyManager.getHistory();

        Assertions.assertNotNull(history);
        Assertions.assertEquals(1, history.size(), "Неверное количество задач");
    }

    @Test
    public void shouldAddWithoutRepetitions() {
        historyManager.add(task);
        historyManager.add(epic);
        historyManager.add(subtask);
        historyManager.add(epic);
        historyManager.add(task);

        final List<Task> history = historyManager.getHistory();

        Assertions.assertNotNull(history, "История не пустая");
        Assertions.assertEquals(3, history.size(), "Присутствует дублирование");
    }

    @Test
    public void shouldNotRemoveAnythingWhenTheListIsEmpty() {
        historyManager.remove(task.getId());

        final List<Task> history = historyManager.getHistory();

        Assertions.assertEquals(0, history.size(), "Список пуст");
    }

    @Test
    public void shouldRemoveFirstElement() {
        historyManager.add(task);
        historyManager.add(epic);
        historyManager.add(subtask);
        historyManager.remove(task.getId());

        final List<Task> history = new ArrayList<>();
        history.add(epic);
        history.add(subtask);

        Assertions.assertArrayEquals(history.toArray(), historyManager.getHistory().toArray());
    }

    @Test
    public void shouldRemoveMiddleElement() {
        historyManager.add(task);
        historyManager.add(epic);
        historyManager.add(subtask);
        historyManager.remove(epic.getId());

        final List<Task> history = new ArrayList<>();
        history.add(task);
        history.add(subtask);

        Assertions.assertArrayEquals(history.toArray(), historyManager.getHistory().toArray());
    }

    @Test
    public void shouldRemoveLastElement() {
        historyManager.add(task);
        historyManager.add(epic);
        historyManager.add(subtask);
        historyManager.remove(subtask.getId());

        final List<Task> history = new ArrayList<>();
        history.add(task);
        history.add(epic);

        Assertions.assertArrayEquals(history.toArray(), historyManager.getHistory().toArray());
    }

    @Test
    public void shouldReturnEmptyListWhenEmptyTaskHistory() {
        final List<Task> history = new ArrayList<>();

        Assertions.assertEquals(0, history.size(), "Список не пуст");
        Assertions.assertArrayEquals(history.toArray(), historyManager.getHistory().toArray());
    }

    @Test
    public void shouldReturnWithoutRepetitions() {
        historyManager.add(task);
        historyManager.add(epic);
        historyManager.add(subtask);
        historyManager.add(epic);
        historyManager.add(task);

        final List<Task> history = new ArrayList<>();
        history.add(subtask);
        history.add(epic);
        history.add(task);

        Assertions.assertEquals(3, history.size(), "Присутствует дублирование");
        Assertions.assertArrayEquals(history.toArray(), historyManager.getHistory().toArray());
    }

}