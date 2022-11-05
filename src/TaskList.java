import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TaskList {
    private Map<Integer, Task> taskList = new HashMap<>();
    private Map<Integer, Task> archiveTasksList = new HashMap<>();

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
                taskList.put(taskDaily.getId(), taskDaily);
                break;
            case 2:
                Task taskWeekly = new WeeklyTask(taskName, taskDescription, date, typeOfTask);
                taskList.put(taskWeekly.getId(), taskWeekly);
                break;
            case 3:
                Task taskMonthly = new MonthlyTask(taskName, taskDescription, date, typeOfTask);
                taskList.put(taskMonthly.getId(), taskMonthly);
                break;
            case 4:
                Task AnnualTask = new AnnualTask(taskName, taskDescription, date, typeOfTask);
                taskList.put(AnnualTask.getId(), AnnualTask);
                break;
            default:
                Task task = new Task(taskName, taskDescription, date, typeOfTask);
                taskList.put(task.getId(), task);
        }
    }

    public void removeTask(Scanner scanner) throws Exception {
        System.out.print("Укажите уникальный номер задачи: ");
        int key = scanner.nextInt();
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

    public void getTaskByDay(Scanner scanner) {
        System.out.print("Укажите день в формате дд.мм.гггг: ");
        String dateString = scanner.next();
//        Распарсил строку с помощью DateTimeFormatter
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate deadlineDate = LocalDate.parse(dateString, df);
        List taskByDay = new ArrayList<>();
//      Где-то здесь ошибка.
        for (Task value : taskList.values()) {
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

        System.out.println("Редактирование заголовка и описание задачи.\n" +
                "Было: заголовок - " + taskList.get(key).getTaskHeader() + ", описание - " + taskList.get(key).getTaskDescription());
        System.out.print("Введите новый заголовок - ");
        scanner.nextLine();
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
