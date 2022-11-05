import java.time.LocalDate;

public class AnnualTask extends Task {

    public AnnualTask(String taskHeader, String taskDescription, String date, int typeOfTask) throws Exception {
        super(taskHeader, taskDescription, date, typeOfTask);
    }

    @Override
    public boolean isAvailable(LocalDate localDate) {
        LocalDate dateOfCreate = this.getDate();
        if (dateOfCreate.equals(localDate)) {
            return true;
        } else {
            while (dateOfCreate.isBefore(localDate.plusDays(1))) {
                if (dateOfCreate.equals(localDate)) {
                    return true;
                }
                dateOfCreate = dateOfCreate.plusYears(1);
            }
            return false;
        }
    }
}
