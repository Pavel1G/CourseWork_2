import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            TaskMap taskMap = new TaskMap();
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            taskMap.addTask(scanner);
                            break;
                        case 2:
                            taskMap.removeTask(scanner);
                            break;
                        case 3:
                            taskMap.getTaskByDay(scanner);
                            break;
                        case 4:
                            taskMap.getAllTaskFromTaskList();
                            break;
                        case 5:
                            taskMap.editTask(scanner);
                            break;
                        case 6:
                            taskMap.getDeletedTask();
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
