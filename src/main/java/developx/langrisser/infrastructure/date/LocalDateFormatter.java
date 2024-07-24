package developx.langrisser.infrastructure.date;

import java.time.LocalDateTime;

public interface LocalDateFormatter {
    String convert(LocalDateTime localDateTime);

}
