package developx.langrisser.application.dashboard.dto;

import developx.langrisser.domain.LabeledEnum;

public record LabeledEnumStat(
        String code,
        String label,
        String displayName,
        WinLoseCount count
) {

    public static LabeledEnumStat from(
            LabeledEnum key,
            WinLoseCount count
    ) {
        return new LabeledEnumStat(
                key.code(),
                key.label(),
                key.displayName(),
                count
        );
    }

    public int winRate() {
        long total = count.winCount() + count.loseCount();
        if (total == 0) return 0;
        return (int) Math.round((double) count.winCount() * 100 / total);
    }

    public long winCount() {
        return count.winCount();
    }

    public long loseCount() {
        return count.loseCount();
    }
}