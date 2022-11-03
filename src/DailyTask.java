import java.time.LocalDate;

public class DailyTask extends Task implements Repeating {

    public DailyTask(String taskHeader, String taskDescription, String date, int typeOfTask) throws Exception {
        super(taskHeader, taskDescription, date, typeOfTask);
    }

    @Override
    public boolean isAvailable(LocalDate localDate) {
        return true;
    }
}
