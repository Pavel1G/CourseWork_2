import java.time.LocalDateTime;
import java.util.*;

public class Diary<T extends Task & Repeating> {
    private Map<Integer, Task> tasks = new HashMap<>();

    public void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void removeTask(int key) {
        tasks.remove(key);
    }

    public void getTaskAllTaskFromDiary() {
        for (Task value : tasks.values()) {
            System.out.println(value);
        }
    }

    public List<Task> getTaskByDayFromDiary(String dateString) {
        int day = Integer.parseInt(dateString.substring(0, 2));
        int month = Integer.parseInt(dateString.substring(3, 5));
        int year = Integer.parseInt(dateString.substring(6, 10));
        LocalDateTime date = LocalDateTime.of(year, month, day, 0, 0);

        List<Task> taskByDay = new ArrayList<>();
        for (Task value : tasks.values()) {
            if (value.getDate().equals(date)) {
                taskByDay.add(value);
            }
        }
        return taskByDay;
    }

    @Override
    public String toString() {
        return "Diary{" +
                "tasks=" + tasks +
                '}';
    }
}
