package developx.langrisser.adapter.persistence;

import developx.langrisser.application.player.dto.FindPlayerQuery;
import developx.langrisser.application.player.required.PlayerStore;
import developx.langrisser.domain.player.Player;
import developx.langrisser.domain.player.ServerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaPlayerStore implements PlayerStore {

    private final PlayerRepository repository;

    @Override
    public Player save(Player player) {
        return repository.save(player);
    }

    @Override
    public Optional<Player> findById(Long playerId) {
        return repository.findById(playerId);
    }

    @Override
    public List<Player> findAll(FindPlayerQuery request) {
        return repository.findAll(request);
    }

    @Override
    public void updateLastMatchDate(Long playerId, LocalDate localDate) {
        this.findById(playerId).ifPresent(player -> player.updateLastMatchDate(localDate));
    }

    @Override
    public Optional<Player> findByServerAndNickname(ServerType serverType, String nickname) {
        return repository.findByServerAndNickname(serverType, nickname);
    }

    @Override
    public List<Player> findCandidates(ServerType server, String nickname, List<String> oldNicknames) {
        return repository.findCandidates(server, nickname, oldNicknames);
    }

//    @Override
//    public Optional<Player> findCandidatePlayer(ServerType serverType, String nickname, List<String> tiers, List<String> oldNickname) {
//
//        List<Player> byServerAndNickname = repository.findByServerAndNickname(serverType, nickname);
//        if (byServerAndNickname.size() == 1) {
//            return Optional.of(byServerAndNickname.get(0));
//        }
//        return Optional.empty();
//    }

}
