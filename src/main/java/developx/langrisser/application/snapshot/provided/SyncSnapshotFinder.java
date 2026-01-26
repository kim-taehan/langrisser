package developx.langrisser.application.snapshot.provided;

import developx.langrisser.application.snapshot.dto.SyncSnapshotDto;

public interface SyncSnapshotFinder {
    SyncSnapshotDto findLatestDate();

    void update(SyncSnapshotDto request);
}
