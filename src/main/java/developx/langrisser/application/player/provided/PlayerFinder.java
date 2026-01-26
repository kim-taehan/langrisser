package developx.langrisser.application.player.provided;

import developx.langrisser.application.player.dto.PlayerDetailView;
import developx.langrisser.application.player.dto.PlayerSummaryView;
import developx.langrisser.application.player.dto.FindPlayerQuery;

import java.util.List;

public interface PlayerFinder {

   PlayerDetailView findPlayer(Long playerId);

   List<PlayerSummaryView> findPlayers(FindPlayerQuery request);
}
