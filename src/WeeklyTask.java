import java.time.LocalDate;

public class WeeklyTask extends Task implements Repeating {

    public WeeklyTask(String taskHeader, String taskDescription, String date, int typeOfTask) throws Exception {
        super(taskHeader, taskDescription, date, typeOfTask);
    }

    @Override
    public boolean isAvailable(LocalDate localDate) {
        LocalDate dateOfCreate = this.getDate();
        if (this.getDate().equals(localDate)) {
            return true;
        } else {
            while (dateOfCreate.isBefore(localDate)) {
                if (dateOfCreate.equals(localDate)) {
                    return true;
                }
                dateOfCreate.plusWeeks(1);
            }
            return false;
        }
    }
}
