import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private static Integer countTask = 0;

    public enum TypeOfTask {
        PERSONAL("Личные задачи"), WORK("Рабочие задачи");

        private String typeOfTask;

        TypeOfTask(String typeOfTask) {
            this.typeOfTask = typeOfTask;
        }

        public String getTypeOfTask() {
            return typeOfTask;
        }

    }

    private String taskHeader;
    private String taskDescription;
    private LocalDate date;
    private TypeOfTask typeOfTask;
    private int typeOfRepeat;
    private LocalDate dateOfCreation;
    private Integer id;

    public Task(String taskHeader, String taskDescription, String date, int typeOfTask, int typeOfRepeat) throws Exception {
        this.id = countTask;
        setTaskHeader(taskHeader);
        setTaskDescription(taskDescription);
        setDate(date);
        setTypeOfTask(typeOfTask);
        this.typeOfRepeat = typeOfRepeat;
        this.dateOfCreation = LocalDate.now();
        countTask++;
    }


    public String getTaskHeader() {
        return taskHeader;
    }

    public void setTaskHeader(String taskHeader) throws Exception {
        if (taskHeader == null || taskHeader.isBlank()) {
            throw new Exception("Укажите название задачи");
        } else {
            this.taskHeader = taskHeader;
        }
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) throws Exception {
        if (taskDescription == null || taskDescription.isBlank()) {
            throw new Exception("Укажите описание задачи задачи");
        } else {
            this.taskDescription = taskDescription;
        }
    }

    public LocalDate getDate() {
        return date;
    }


    public void setDate(String dateString) throws Exception {
        if (dateString == null) throw new Exception("Укажите описание дату создания задачи");

        //        Распарсил строку с помощью DateTimeFormatter
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.date = LocalDate.parse(dateString, df);
    }

    public void setTypeOfTask(int num) throws Exception {
        if (num == 1) {
            this.typeOfTask = TypeOfTask.PERSONAL;
        } else if (num == 2) {
            this.typeOfTask = TypeOfTask.WORK;
        } else {
            throw new Exception("Укажите правильный вариант.");
        }
    }


    public int getTypeOfRepeat() {
        return typeOfRepeat;
    }

    public TypeOfTask getTypeOfTask() {
        return typeOfTask;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + id + "taskHeader='" + taskHeader + '\'' + ", " +
                "taskDescription='" + taskDescription + '\'' + '}';
    }


}
