package developx.langrisser.web.request;

import developx.langrisser.match.MatchType;
import developx.langrisser.match.Stage;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
public record MatchesRequest(
        Stage stage,
        MatchType matchType,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate fromDate,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate toDate
) {
    public MatchesRequest initData() {
        if (isNull()) {
            return MatchesRequest.builder()
                    .stage(Stage.DESERT_EYES)
                    .matchType(MatchType.NONE)
                    .fromDate(LocalDate.now().minusDays(10))
                    .toDate(LocalDate.now())
                    .build();
        }
        return this;
    }
    private boolean isNull(){
        return stage == null && matchType == null && fromDate == null && toDate == null;
    }
}
