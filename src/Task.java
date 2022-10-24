import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    private LocalDateTime date;
    private TypeOfTask typeOfTask;
    private int typeOfRepeat;
    private Integer id = countTask;
    public Map<Integer, Task> tasks = new HashMap<>();

    public Task(String taskHeader, String taskDescription, String date,
                int typeOfTask, int typeOfRepeat) throws Exception {
        setTaskHeader(taskHeader);
        setTaskDescription(taskDescription);
        setDate(date);
        setTypeOfTask(typeOfTask);
        this.typeOfRepeat = typeOfRepeat;
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

    public LocalDateTime getDate() {
        return date;
    }


    public void setDate(String dateString) throws Exception {
        if (dateString == null) throw new Exception("Укажите описание дату создания задачи");
        int day = Integer.parseInt(dateString.substring(0, 2));
        int month = Integer.parseInt(dateString.substring(3, 5));
        int year = Integer.parseInt(dateString.substring(6, 10));
        //Решить костыль с часами и минутами.
        this.date = LocalDateTime.of(year, month, day, 0, 0);
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

    public TypeOfTask getTypeOfTask() {
        return typeOfTask;
    }

    public Integer getId() {
        return id;
    }


}
