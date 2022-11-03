import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Diary implements Repeating {
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Task> archiveTasks = new HashMap<>();

    public void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void removeTask(int key) throws Exception {
        if (!tasks.containsKey(key)) {
            throw new Exception("Задача с ключом " + key + " не найдена.");
        }
//        Создал мап архив задач, редактирую заголовки задач в мапе diary.
        archiveTasks.put(tasks.get(key).getId(), tasks.get(key));
        tasks.get(key).setTaskHeader("(Удалена) " + tasks.get(key).getTaskHeader());
    }

    public void getDeletedTask() {
        for (Task value : archiveTasks.values()) {
            System.out.println(value);
        }
    }

    public void getTaskAllTaskFromDiary() {
        for (Task value : tasks.values()) {
            System.out.println(value);
        }
    }

    public void getTaskByDayFromDiary(String dateString) {
//        Распарсил строку с помощью DateTimeFormatter
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate deadlineDate = LocalDate.parse(dateString, df);
        List<Task> taskByDay = new ArrayList<>();


        for (Task value : tasks.values()) {
            LocalDate dateForEquals = value.getDate();
            if (dateForEquals.equals(LocalDate.parse(dateString, df)) || value.getTypeOfRepeat() == 1) {
                taskByDay.add(value);
            }

//            Не могу додуматься как грамотно реализовать повторяемость - хочу в Task строках 89 - 104
//            как-то реализовать получение следующих дней, когда объект задача будет выполняться, а здесь,
//            в Diary, сделать бесконечный цикл while с методом isBefore с параметром dataString.
//            Правильно ли я размышляю или надо сделать по-другому?
            switch (value.getTypeOfRepeat()) {
                case 1:
                    while (dateForEquals.isBefore(deadlineDate)) {
                        if (dateForEquals.equals(deadlineDate)) {
                            taskByDay.add(value);
                        }
                        dateForEquals.plusWeeks(1);
                    }
                case 2:
                    while (dateForEquals.isBefore(deadlineDate)) {
                        if (dateForEquals.equals(deadlineDate)) {
                            taskByDay.add(value);
                        }
                        dateForEquals.plusMonths(1);
                    }
                case 3:
                    while (dateForEquals.isBefore(deadlineDate)) {
                        if (dateForEquals.equals(deadlineDate)) {
                            taskByDay.add(value);
                        }
                        dateForEquals.plusYears(1);
                    }
            }
        }
        System.out.println(taskByDay);
    }

//  Редактирование заголовка и описания задачи по ключу.

    public void editTask(int key) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Редактирование заголовка и описание задачи.\n" +
                "Было: заголовок - " + tasks.get(key).getTaskHeader() + ", описание - " + tasks.get(key).getTaskDescription());
        System.out.print("Введите новый заголовок - ");
        tasks.get(key).setTaskHeader(scanner.nextLine());
        System.out.println();
        System.out.print("Введите новое описание - ");
        tasks.get(key).setTaskDescription(scanner.nextLine());
    }


    @Override
    public String toString() {
        return "Diary{" + "tasks = " + tasks + '}';
    }

    //    Я знаю, что можно этот метод переопределить и положить его в качестве параметра циклов while в строках
//    50, 57, 64, но мне по-прежнему все это кажется странным, и я никак не могу найти применение этому интерфейсу,
//    поэтому пускай пока полежит.
    @Override
    public boolean isAvailable(LocalDate localDate) {
        return false;
    }
}
