package developx.langrisser.application.snapshot;

import developx.langrisser.application.player.PlayerGoogleSheetImporter;
import developx.langrisser.application.snapshot.dto.SyncSnapshotDto;
import developx.langrisser.application.snapshot.provided.SyncSnapshotFinder;
import developx.langrisser.application.snapshot.required.SyncSnapshotStore;
import developx.langrisser.domain.snapshot.SyncSnapshot;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SnapshotQueryService implements SyncSnapshotFinder {

    private final SyncSnapshotStore store;
    private final PlayerGoogleSheetImporter importer;

    @Value("${google.sheet.base-url}")
    private String baseUrl;

    @Value("${google.sheet.csv-path}")
    private String csvPath;

    @Override
    public SyncSnapshotDto findLatestDate() {
        Optional<LocalDateTime> localDateTime = store.findLatest()
                .map(SyncSnapshot::getCreatedAt);

        return SyncSnapshotDto.of(
                localDateTime.orElse(null),
                baseUrl
        );
    }


    public void update(SyncSnapshotDto request) {
        importer.importPlayers(request.updateLink() + csvPath);
    }
}
