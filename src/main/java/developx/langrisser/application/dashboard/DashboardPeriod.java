package developx.langrisser.application.dashboard;

import developx.langrisser.domain.LabeledEnum;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public enum DashboardPeriod implements LabeledEnum {
    ALL("전체"),
    WEEK("최근 1주"),
    MONTH("최근 1개월");

    private final String label;


    @Override
    public String label() {
        return this.label;
    }

    public LocalDate fromDate() {
        return switch (this) {
            case ALL -> null;
            case WEEK -> LocalDate.now().minusWeeks(1);
            case MONTH -> LocalDate.now().minusMonths(1);
        };
    }
}
