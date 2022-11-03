import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            TaskList taskList = new TaskList();
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(taskList, scanner);
                            break;
                        case 2:
                            removeTask(taskList, scanner);
                            break;
                        case 3:
                            getTaskByDay(taskList, scanner);
                            break;
                        case 4:
                            taskList.getAllTaskFromTaskList();
                            break;
                        case 5:
                            editTask(taskList, scanner);
                        case 6:
                            taskList.getDeletedTask();
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void inputTask(TaskList taskList, Scanner scanner) throws Exception {
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
                taskList.addTask(new DailyTask(taskName, taskDescription, date, typeOfTask));
            case 2:
                taskList.addTask((new WeeklyTask(taskName, taskDescription, date, typeOfTask)));
            case 3:
                taskList.addTask((new MonthlyTask(taskName, taskDescription, date, typeOfTask)));
            case 4:
                taskList.addTask((new AnnualTask(taskName, taskDescription, date, typeOfTask)));
            default:
                taskList.addTask((new Task(taskName, taskDescription, date, typeOfTask)));
        }
    }

    public static void removeTask(TaskList taskList, Scanner scanner) throws Exception {
        System.out.print("Укажите уникальный номер задачи: ");
        int key = scanner.nextInt();
        taskList.removeTask(key);
    }

    public static void getTaskByDay(TaskList taskList, Scanner scanner) {
        System.out.print("Укажите день в формате дд.мм.гггг: ");
        String dateString = scanner.next();
        taskList.getTaskByDayFromTaskList(dateString);
    }

    public static void editTask(TaskList taskList, Scanner scanner) throws Exception {
        System.out.println("Список всех задач.");
        taskList.getAllTaskFromTaskList();
        System.out.print("Введите ID задачи - ");
        taskList.editTask(scanner.nextInt());
    }

    private static void printMenu() {
        System.out.println(
                "1. Добавить задачу\n" +
                        "2. Удалить задачу\n" +
                        "3. Получить задачи на указанный день\n" +
                        "4. Получить все задачи\n" +
                        "5. Редактировать заголовок и описание задачи\n" +
                        "6. Получить список всех удаленных задач\n" +
                        "0. Выход"

        );
    }


}
