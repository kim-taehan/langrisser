package developx.langrisser.application.player;

import developx.langrisser.application.player.dto.PlayerDetailView;
import developx.langrisser.application.player.dto.PlayerSummaryView;
import developx.langrisser.application.player.provided.PlayerFinder;
import developx.langrisser.application.player.required.PlayerStore;
import developx.langrisser.application.player.dto.FindPlayerQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlayerQueryService implements PlayerFinder {

    private final PlayerStore store;

    @Override
    public PlayerDetailView findPlayer(Long playerId) {
        return store.findById(playerId)
                .map(PlayerDetailView::from)
                .orElseThrow(() -> new IllegalArgumentException("player 가 존재하지 않습니다. =" + playerId));
    }

    @Override
    public List<PlayerSummaryView> findPlayers(FindPlayerQuery request) {
        return store.findAll(request)
                .stream()
                .map(PlayerSummaryView::from)
                .toList();
    }
}
