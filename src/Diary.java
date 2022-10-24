import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Diary {
    private Map<Integer, Task> tasks = new HashMap<>();

    public void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void removeTask(int key) {
        tasks.remove(key);
    }

//    Установка следующих дат, когда установлено напоминание
//    public LocalDateTime setTypeOfRepeat(int num) {
//        switch (num) {
//            case 1:
//                return date.plusDays(1);
//            case 2:
//                return date.plusWeeks(1);
//            case 3:
//                return date.plusMonths(1);
//            case 4:
//                return date.plusYears(1);
//            default:
//                return this.date;
//        }
//    }
}
