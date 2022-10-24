import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            // todo: обрабатываем пункт меню 2
                            break;
                        case 3:
                            // todo: обрабатываем пункт меню 3
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

    private static void inputTask(Scanner scanner) throws Exception {
        Diary diary = new Diary();
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

    private static void printMenu() {
        System.out.println(
                "1. Добавить задачу\n" +
                        "2. Удалить задачу\n" +
                        "3. Получить задачу на указанный день\n" +
                        "0. Выход"

        );
    }


}
