package developx.langrisser.adapter.persistence;

import developx.langrisser.application.snapshot.required.SyncSnapshotStore;
import developx.langrisser.domain.snapshot.SyncSnapshot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JpaSyncSnapshotStore implements SyncSnapshotStore {

    private final SyncSnapshotRepository repository;

    @Override
    public Optional<SyncSnapshot> findLatest() {
        return repository.findTopByOrderByCreatedAtDesc();
    }

    @Override
    public SyncSnapshot update() {
        return repository.save(
                SyncSnapshot.create()
        );
    }

}
