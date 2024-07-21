package developx.langrisser.match;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MatchType {
    NONE("선택안함"),
    SUMMIT("서밋"),
    CASUAL("캐주얼"),
    CONTEST("사설대회")
    ;

    private final String text;
}
