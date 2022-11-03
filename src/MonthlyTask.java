import java.time.LocalDate;

public class MonthlyTask extends Task implements Repeating {

    public MonthlyTask(String taskHeader, String taskDescription, String date, int typeOfTask) throws Exception {
        super(taskHeader, taskDescription, date, typeOfTask);
    }

    public boolean isAvailable(LocalDate localDate) {
        LocalDate dateOfCreate = this.getDate();
        if (dateOfCreate.equals(localDate)) {
            return true;
        } else {
            while (dateOfCreate.isBefore(localDate)) {
                if (dateOfCreate.equals(localDate)) {
                    return true;
                }
                dateOfCreate.plusMonths(1);
            }
            return false;
        }
    }
}