package developx.langrisser.adapter.persistence;

import developx.langrisser.domain.snapshot.SyncSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SyncSnapshotRepository extends JpaRepository<SyncSnapshot, Long> {
    Optional<SyncSnapshot> findTopByOrderByCreatedAtDesc();
}
