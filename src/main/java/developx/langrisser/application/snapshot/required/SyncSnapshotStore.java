package developx.langrisser.application.snapshot.required;

import developx.langrisser.domain.snapshot.SyncSnapshot;

import java.util.Optional;

public interface SyncSnapshotStore {

    Optional<SyncSnapshot> findLatest();

    SyncSnapshot update();
}
