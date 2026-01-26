package developx.langrisser.application.snapshot.dto;

import java.time.LocalDateTime;

public record SyncSnapshotDto(
       LocalDateTime latestDate,
       String updateLink
) {

    public static SyncSnapshotDto of(LocalDateTime localDateTime, String updateLink) {
        return new SyncSnapshotDto(localDateTime, updateLink);
    }
}
