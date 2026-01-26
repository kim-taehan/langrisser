package developx.langrisser.application.match;

import developx.langrisser.application.match.dto.RegisterMatchCommand;
import developx.langrisser.application.match.provided.MatchRegister;
import developx.langrisser.application.match.required.MatchStore;
import developx.langrisser.application.player.required.PlayerStore;
import developx.langrisser.domain.match.Match;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchModifyService implements MatchRegister {

    private final MatchStore store;

    private final PlayerStore playerStore;

    @Override
    public void register(RegisterMatchCommand command) {
        store.save(command.toEntity());

        playerStore.updateLastMatchDate(command.opponentPlayerId(), command.matchDate());
    }

    @Override
    public void changeMemo(Long matchId, String memo) {
        Match match = store.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Match not found: " + matchId));
        match.updateMemo(memo);
    }

    @Override
    public void remove(Long matchId) {
        Match match = store.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Match result not found: " + matchId));
        store.remove(match);
    }
}
