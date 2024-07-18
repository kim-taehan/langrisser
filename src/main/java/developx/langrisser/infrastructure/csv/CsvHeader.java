package developx.langrisser.infrastructure.csv;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@Getter
public enum CsvHeader {

    RANK_POINT("rank(p)"),
    POINT,
    RANK_CRISTAL("rank(c)"),
    CRISTAL,
    NAME,
    SERVER,
    BEFORE_IDS("이전아이디");

    CsvHeader() {
        this.key = name().toLowerCase();
    }

    private final String key;


}
