import java.time.LocalDate;

public class MonthlyTask extends Task {

    public MonthlyTask(String taskHeader, String taskDescription, String date, int typeOfTask) throws Exception {
        super(taskHeader, taskDescription, date, typeOfTask);
    }

    public boolean isAvailable(LocalDate localDate) {
        LocalDate dateOfCreate = this.getDate();

        while (!dateOfCreate.isAfter(localDate)) {
            if (dateOfCreate.equals(localDate)) {
                return true;
            }
            dateOfCreate = dateOfCreate.plusMonths(1);
        }
        return false;
    }
}