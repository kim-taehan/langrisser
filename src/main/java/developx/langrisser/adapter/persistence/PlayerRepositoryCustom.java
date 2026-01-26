package developx.langrisser.adapter.persistence;


import developx.langrisser.application.player.dto.FindPlayerQuery;
import developx.langrisser.domain.player.Player;
import developx.langrisser.domain.player.ServerType;

import java.util.List;

public interface PlayerRepositoryCustom {

    List<Player> findAll(FindPlayerQuery request);

    List<Player> findCandidates(ServerType server, String nickname, List<String> oldNicknames);
}
