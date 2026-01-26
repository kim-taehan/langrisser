package developx.langrisser.application.dashboard.dto;

public record WinLoseCount(
        long winCount,
        long loseCount
) {

    public long total() {
        return winCount + loseCount;
    }

    public double winRate() {
        if (total() == 0) return 0.0;
        return (double) winCount / total() * 100;
    }
}