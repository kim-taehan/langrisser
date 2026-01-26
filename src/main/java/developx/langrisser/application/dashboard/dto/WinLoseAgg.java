package developx.langrisser.application.dashboard.dto;

public record WinLoseAgg(
        String code,   // enum name()
        long winCount,
        long loseCount
) {}