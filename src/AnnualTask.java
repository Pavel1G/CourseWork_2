import java.time.LocalDate;

public class AnnualTask extends Task implements Repeating {

    public AnnualTask(String taskHeader, String taskDescription, String date, int typeOfTask) throws Exception {
        super(taskHeader, taskDescription, date, typeOfTask);
    }

    @Override
    public boolean isAvailable(LocalDate localDate) {
        LocalDate dateOfCreate = this.getDate();
        if (dateOfCreate.equals(localDate)) {
            return true;
        } else {
            while (dateOfCreate.isBefore(localDate)) {
                if (dateOfCreate.equals(localDate)) {
                    return true;
                }
                dateOfCreate.plusYears(1);
            }
            return false;
        }
    }
}
