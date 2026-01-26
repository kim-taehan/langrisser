package developx.langrisser.application.player;

import developx.langrisser.adapter.external.google.PlayerGoogle;
import developx.langrisser.adapter.external.google.PlayerGoogleSheetLoader;
import developx.langrisser.application.player.required.PlayerStore;
import developx.langrisser.application.snapshot.required.SyncSnapshotStore;
import developx.langrisser.domain.player.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerGoogleSheetImporter {

    private final PlayerGoogleSheetLoader loader;
    private final PlayerStore store;
    private final SyncSnapshotStore snapshotStore;

    @Transactional
    @Async
    public void importPlayers(String csvUrl) {
        List<PlayerGoogle> googlePlayers = loader.load(csvUrl);

        log.info("어쿄 시트와 연동합니다. 조회된 데이터 {} players", googlePlayers.size());
        for (PlayerGoogle googlePlayer : googlePlayers) {
            Player player = findOrCreate(googlePlayer);
            addPreviousNicknames(player, googlePlayer.oldNickname());
            addSummitTires(player, googlePlayer.tiers());
        }
        log.info("어쿄 시트와 연동이 완료되었습니다.");
        snapshotStore.update();
    }

    private void addSummitTires(Player player, Map<String, String> tiers) {
        tiers.forEach((key, value) -> player.addSummitRankIfAbsent(key, SeasonTier.fromLabel(value)));
    }

    private static void addPreviousNicknames(Player player, List<String> oldNicknames) {
        oldNicknames.forEach(player::addPreviousNicknames);
    }

    private Player findOrCreate(PlayerGoogle playerGoogle) {
        ServerType server = ServerType.fromLabel(playerGoogle.server());
        Map<String, SeasonTier> docTiers = playerGoogle.tiers().entrySet().stream().collect(
                Collectors.toMap(Map.Entry::getKey, entry -> SeasonTier.fromLabel(entry.getValue())));
        return store.findCandidates(
                        server,
                        playerGoogle.nickname(),
                        playerGoogle.oldNickname()
                )
                .stream()
                .filter(player ->
                        {
                            Map<String, SeasonTier> dbTiers = player.getSummitRanks().stream()
                                    .collect(Collectors.toMap(PlayerSummitRank::getSeason, PlayerSummitRank::getTier));
                            return dbTiers.entrySet().stream()
                                    .allMatch(e ->
                                            e.getValue().equals(docTiers.get(e.getKey()))
                                    );
                        }
                )
                .peek(player -> player.updateInfo(playerGoogle.nickname(), playerGoogle.cristal(), playerGoogle.cristalRank(), playerGoogle.point(), playerGoogle.pointRank()))
                .findFirst()
                .orElseGet(() -> savePlayer(playerGoogle));
    }

    private Player savePlayer(PlayerGoogle playerGoogle) {
        return store.save(
                Player.of(
                        playerGoogle.nickname(),
                        ServerType.fromLabel(playerGoogle.server()),
                        MetricRank.of(playerGoogle.cristal(), playerGoogle.cristalRank()),
                        MetricRank.of(playerGoogle.point(), playerGoogle.pointRank())
                )
        );
    }
}
