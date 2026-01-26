package developx.langrisser.infrastructure.bootstrap;


import developx.langrisser.application.player.PlayerGoogleSheetImporter;
import developx.langrisser.application.snapshot.required.SyncSnapshotStore;
import developx.langrisser.domain.snapshot.SyncSnapshot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class PlayerImportStartupListener {

    private final PlayerGoogleSheetImporter importer;
    private final SyncSnapshotStore snapshotStore;

    private static final String CSV_URL = "https://docs.google.com/spreadsheets/d/19eLjTpcTDSZLiyTAMJGSBXJkWLAmq7QYs3t9qWEGv40/export?format=csv&gid=0";

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void onApplicationReady() {
        snapshotStore.findLatest().ifPresentOrElse(
                syncSnapshot -> log.info("등록된 snapshot 이 존재합니다. {}", syncSnapshot.getCreatedAt()),
                () -> {
                    importer.importPlayers(CSV_URL);
                }
        );
    }
}