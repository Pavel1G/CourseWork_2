import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Task implements Repeating {
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
    private LocalDateTime dateOfCreation;
    private Integer id = countTask;
    public Map<Integer, Task> tasks = new HashMap<>();

    public Task(String taskHeader, String taskDescription, String date,
                int typeOfTask, int typeOfRepeat) throws Exception {
        setTaskHeader(taskHeader);
        setTaskDescription(taskDescription);
        setDate(date);
        setTypeOfTask(typeOfTask);
        this.typeOfRepeat = typeOfRepeat;
        this.dateOfCreation = LocalDateTime.now();
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

    public LocalDateTime getNexDateTask(int typeOfRepeat) {
        switch (typeOfRepeat) {
            case 1:
                getDaily();
            case 2:
                getWeekly();
            case 3:
                getMonthly();
            case 4:
                getAnnually();
        }
        return this.dateOfCreation;
    }

    @Override
    public LocalDateTime getDaily() {
        return this.dateOfCreation.plusDays(1);
    }

    @Override
    public LocalDateTime getWeekly() {
        return this.dateOfCreation.plusWeeks(1);
    }

    @Override
    public LocalDateTime getMonthly() {
        return this.dateOfCreation.plusMonths(1);
    }

    @Override
    public LocalDateTime getAnnually() {
        return this.dateOfCreation.plusYears(1);
    }


    public TypeOfTask getTypeOfTask() {
        return typeOfTask;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskHeader='" + taskHeader + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                '}';
    }
}
