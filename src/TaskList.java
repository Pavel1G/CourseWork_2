import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TaskList<T extends Task & Repeating> {
    private Map<Integer, T> taskList = new HashMap<>();
    private Map<Integer, T> archiveTasksList = new HashMap<>();

    public void addTask(T task) {
        taskList.put(task.getId(), task);
    }

    public void removeTask(int key) throws Exception {
        if (!taskList.containsKey(key)) {
            throw new Exception("Задача с ключом " + key + " не найдена.");
        }
//        Создал мап архив задач, редактирую заголовки задач в мапе diary.
        archiveTasksList.put(taskList.get(key).getId(), taskList.get(key));
        taskList.get(key).setTaskHeader("(Удалена) " + taskList.get(key).getTaskHeader());
    }

    public void getDeletedTask() {
        for (Task value : archiveTasksList.values()) {
            System.out.println(value);
        }
    }

    public void getAllTaskFromTaskList() {
        for (Task value : taskList.values()) {
            System.out.println(value);
        }
    }

    public void getTaskByDayFromTaskList(String dateString) {
//        Распарсил строку с помощью DateTimeFormatter
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate deadlineDate = LocalDate.parse(dateString, df);
        List<Task> taskByDay = new ArrayList<>();

        for (T value : taskList.values()) {
            if (value.isAvailable(deadlineDate)) {
                taskByDay.add(value);
            }
        }

        System.out.println(taskByDay);
    }

//  Редактирование заголовка и описания задачи по ключу.

    public void editTask(int key) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Редактирование заголовка и описание задачи.\n" +
                "Было: заголовок - " + taskList.get(key).getTaskHeader() + ", описание - " + taskList.get(key).getTaskDescription());
        System.out.print("Введите новый заголовок - ");
        taskList.get(key).setTaskHeader(scanner.nextLine());
        System.out.println();
        System.out.print("Введите новое описание - ");
        taskList.get(key).setTaskDescription(scanner.nextLine());
    }


    @Override
    public String toString() {
        return "Diary{" + "tasks = " + taskList + '}';
    }
}
