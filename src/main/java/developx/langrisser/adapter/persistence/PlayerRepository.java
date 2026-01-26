package developx.langrisser.adapter.persistence;

import developx.langrisser.domain.player.Player;
import developx.langrisser.domain.player.ServerType;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>, PlayerRepositoryCustom {
    Optional<Player> findByServerAndNickname(ServerType server, String nickname);
}
