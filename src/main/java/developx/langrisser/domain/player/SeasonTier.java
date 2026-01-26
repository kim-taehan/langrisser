package developx.langrisser.domain.player;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum SeasonTier {

    NONE(Integer.MAX_VALUE, "-"),
    BRONZE_3(3300, "브3"),
    BRONZE_2(3200, "브2"),
    BRONZE_1(3100, "브1"),

    SILVER_3(2300, "실3"),
    SILVER_2(2200, "실2"),
    SILVER_1(2100, "실1"),

    GOLD_3(1300, "골3"),
    GOLD_2(1200, "골2"),
    GOLD_1(1100, "골1"),

    WONDER(1000, "원더"),

    TOP_512(512, "512", "512등"),

    // 토러
    TOP_256(256, "256", "256강"),
    TOP_128(128, "128", "128강"),
    TOP_64(64, "64", "64강"),
    TOP_32(32, "32", "32강"),

    // 본선
    TOP_16(16, "16", "16강"),
    TOP_8(8, "8", "8강"),

    // 4강러
    TOP_4(4, "4", "4강"),

    // 준우승자
    TOP_2(2, "2", "준우승"),

    // 우승자
    TOP_1(1, "1", "우승"),
    ;

    private final int order;
    private final String label;
    private final String displayName;

    SeasonTier(int order, String label) {
        this.order = order;
        this.label = label;
        this.displayName = label;
    }

    SeasonTier(int order, String label, String displayName) {
        this.order = order;
        this.label = label;
        this.displayName = displayName;
    }

    public boolean isBetterThan(SeasonTier other) {
        return this.order < other.order;
    }

    public String label() {
        return label;
    }

    public int order() {
        return order;
    }

    public String displayName() {

        return displayName == null ? "기록 없음" : displayName;
    }


     /* =========================
       label → enum 매핑
       ========================= */

    private static final Map<String, SeasonTier> BY_LABEL =
            Arrays.stream(values())
                    .collect(Collectors.toMap(
                            SeasonTier::label,
                            Function.identity()
                    ));

    /* =========================
       public method 딱 하나
       ========================= */
    public static SeasonTier fromLabel(String label) {
        if (label == null || label.isBlank()) {
            return NONE;
        }
        return BY_LABEL.getOrDefault(label.trim(), NONE);
    }


}


