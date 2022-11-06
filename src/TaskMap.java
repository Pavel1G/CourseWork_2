import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TaskMap {
    private Map<Integer, Task> taskMap = new HashMap<>();
    private Map<Integer, Task> archiveTasksMap = new HashMap<>();

    public void addTask(Scanner scanner) throws Exception {
        System.out.print("Введите название задачи: ");
        String taskName = scanner.next();
        System.out.print("Введите описание задачи: ");
//        Нужен костыль на строчке ниже, иначе не вводится строка!!!
        scanner.nextLine();
        String taskDescription = scanner.nextLine();
        System.out.print("Введите дату задачи в формате дд.мм.гггг: ");
        String date = scanner.next();
        System.out.print("Укажите тип задачи (1 - личная, 2 - рабочая): ");
        int typeOfTask = scanner.nextInt();
        System.out.print("Укажите тип повторяемости задачи (1 - ежедневная, 2 - еженедельная, 3 - ежемесячная," +
                "4 - ежегодная: ");
        int typeOfRepeat = scanner.nextInt();
        System.out.println();

        switch (typeOfRepeat) {
            case 1:
                Task taskDaily = new DailyTask(taskName, taskDescription, date, typeOfTask);
                taskMap.put(taskDaily.getId(), taskDaily);
                break;
            case 2:
                Task taskWeekly = new WeeklyTask(taskName, taskDescription, date, typeOfTask);
                taskMap.put(taskWeekly.getId(), taskWeekly);
                break;
            case 3:
                Task taskMonthly = new MonthlyTask(taskName, taskDescription, date, typeOfTask);
                taskMap.put(taskMonthly.getId(), taskMonthly);
                break;
            case 4:
                Task AnnualTask = new AnnualTask(taskName, taskDescription, date, typeOfTask);
                taskMap.put(AnnualTask.getId(), AnnualTask);
                break;
            default:
                Task task = new Task(taskName, taskDescription, date, typeOfTask);
                taskMap.put(task.getId(), task);
        }
    }

    public void removeTask(Scanner scanner) throws Exception {
        System.out.print("Укажите уникальный номер задачи: ");
        int key = scanner.nextInt();
        if (!taskMap.containsKey(key)) {
            throw new Exception("Задача с ключом " + key + " не найдена.");
        }
//        Создал мап архив задач, редактирую заголовки задач в мапе diary.
        archiveTasksMap.put(taskMap.get(key).getId(), taskMap.get(key));
        taskMap.get(key).setLabelDelete();
    }

    public void getDeletedTask() {
        for (Task value : archiveTasksMap.values()) {
            System.out.println(value);
        }
    }

    public void getAllTaskFromTaskList() {
        for (Task value : taskMap.values()) {
            System.out.println(value);
        }
    }

    public void getTaskByDay(Scanner scanner) {
        System.out.print("Укажите день в формате дд.мм.гггг: ");
        String dateString = scanner.next();
//        Распарсил строку с помощью DateTimeFormatter
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate deadlineDate = LocalDate.parse(dateString, df);

        List taskByDay = new ArrayList<>();
        for (Task value : taskMap.values()) {
            if (value.isAvailable(deadlineDate)) {
                taskByDay.add(value);
            }
        }
        System.out.println(taskByDay);
    }

//  Редактирование заголовка и описания задачи по ключу.

    public void editTask(Scanner scanner) throws Exception {
        System.out.println("Список всех задач.");
        getAllTaskFromTaskList();
        System.out.print("Введите ID задачи - ");
        int key = scanner.nextInt();
        Task editableTask = taskMap.get(key);

        System.out.println("Редактирование заголовка и описание задачи.\n" +
                "Было: заголовок - " + editableTask.getTaskHeader() + ", описание - " + editableTask.getTaskDescription());
        System.out.print("Введите новый заголовок - ");
        scanner.nextLine();
        editableTask.setTaskHeader(scanner.nextLine());
        System.out.println();
        System.out.print("Введите новое описание - ");
        editableTask.setTaskDescription(scanner.nextLine());
    }


    @Override
    public String toString() {
        return "Diary{" + "tasks = " + taskMap + '}';
    }
}
