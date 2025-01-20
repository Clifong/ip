package Acheron.Utility;

import java.time.LocalDate;

public class DateFormatter {
    public static String formatDate(LocalDate date) {
        String stringDate = String.valueOf(date);
        String day = stringDate.substring(stringDate.length() - 2);
        String month = String.valueOf(date.getMonth());
        String year = String.valueOf(date.getYear());
        return "%s %s %s".formatted(month, day, year);
    }
}
