package developx.langrisser.application.player.required;

import developx.langrisser.application.player.dto.FindPlayerQuery;
import developx.langrisser.domain.player.Player;
import developx.langrisser.domain.player.ServerType;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PlayerStore {

    Player save(Player player);

    Optional<Player> findById(Long playerId);

    List<Player> findAll(FindPlayerQuery request);

    void updateLastMatchDate(Long playerId, LocalDate localDate);


    Optional<Player> findByServerAndNickname(ServerType serverType, String nickname);

    List<Player> findCandidates(ServerType server, String nickname, List<String> oldNicknames);
}
