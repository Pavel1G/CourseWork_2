import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            Diary diary = new Diary();
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(diary, scanner);
                            break;
                        case 2:
                            removeTask(diary, scanner);
                            break;
                        case 3:
                            getTaskByDay(diary, scanner);
                            break;
                        case 4:
                            diary.getTaskAllTaskFromDiary();
                            break;
                        case 5:
                            editTask(diary, scanner);
                        case 6:
                            diary.getDeletedTask();
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

    private static void inputTask(Diary diary, Scanner scanner) throws Exception {
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
        diary.addTask(new Task(taskName, taskDescription, date, typeOfTask, typeOfRepeat));
    }

    public static void removeTask(Diary diary, Scanner scanner) throws Exception {
        System.out.print("Укажите уникальный номер задачи: ");
        int key = scanner.nextInt();
        diary.removeTask(key);
    }

    public static void getTaskByDay(Diary diary, Scanner scanner) {
        System.out.print("Укажите день в формате дд.мм.гггг: ");
        String dateString = scanner.next();
        diary.getTaskByDayFromDiary(dateString);
    }

    public static void editTask(Diary diary, Scanner scanner) throws Exception {
        System.out.println("Список всех задач.");
        diary.getTaskAllTaskFromDiary();
        System.out.print("Введите ID задачи - ");
        diary.editTask(scanner.nextInt());
    }

    private static void printMenu() {
        System.out.println(
                "1. Добавить задачу\n" +
                        "2. Удалить задачу\n" +
                        "3. Получить задачу на указанный день\n" +
                        "4. Получить все задачи\n" +
                        "5. Редактировать заголовок и описание задачи\n" +
                        "6. Получить список всех удаленных задач\n" +
                        "0. Выход"

        );
    }


}
