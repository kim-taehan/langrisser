package developx.langrisser.infrastructure.date;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class SimpleLocalDateFormatter implements LocalDateFormatter {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    @Override
    public String convert(LocalDateTime localDateTime) {
        return localDateTime.format(dateTimeFormatter);
    }
}
