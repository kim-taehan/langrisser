package developx.langrisser.domain.match;

import developx.langrisser.domain.LabeledEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
public enum TurnOrder implements LabeledEnum {
    FIRST("▶ 선공"),
    SECOND("후공 ◀")
    ;
    private final String displayName;

    @Override
    public String label() {
        return displayName;
    }
}