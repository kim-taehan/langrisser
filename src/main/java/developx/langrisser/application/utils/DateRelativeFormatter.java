package developx.langrisser.application.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class DateRelativeFormatter {

    private DateRelativeFormatter() {}

    public static String format(LocalDate date) {
        LocalDate today = LocalDate.now();

        if (date.isEqual(today)) {
            return "오늘";
        }

        long days = ChronoUnit.DAYS.between(date, today);

        if (days < 7) {
            return days + "일 전";
        }

        if (days < 30) {
            long weeks = days / 7;
            return weeks + "주 전";
        }

        if (days < 365) {
            long months = days / 30;
            return months + "개월 전";
        }

        long years = days / 365;
        return years + "년 전";
    }
}
