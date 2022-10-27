import java.time.LocalDateTime;

public interface Repeating {

    //        Установка следующих дат, когда установлено напоминание

    LocalDateTime getDaily();

    LocalDateTime getWeekly();

    LocalDateTime getMonthly();

    LocalDateTime getAnnually();
}
