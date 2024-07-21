package developx.langrisser.match;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Orders {

    NONE("선택안함"),
    FIRST("선공"),
    SECOND("후공")
    ;

    private final String text;

}
