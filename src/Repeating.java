import java.time.LocalDate;
import java.time.LocalDateTime;

public interface Repeating {
    LocalDateTime getNextReminderDay(int num);
}
