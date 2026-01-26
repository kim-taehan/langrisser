package developx.langrisser.application.player;

import developx.langrisser.application.player.dto.RegisterPlayerResult;
import developx.langrisser.application.player.provided.PlayerRegister;
import developx.langrisser.application.player.required.PlayerStore;
import developx.langrisser.domain.player.Player;
import developx.langrisser.application.player.dto.RegisterPlayerCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayerModifyService implements PlayerRegister {

    private final PlayerStore store;

    public RegisterPlayerResult register(RegisterPlayerCommand request) {
        Player player = store.save(
                Player.create(request.nickname(), request.server())
        );
        return RegisterPlayerResult.from(player);
    }

    @Override
    public void changeNickname(Long playerId, String nickname) {

        Player player = store.findById(playerId)
                .orElseThrow(() -> new IllegalArgumentException("Player not found: " + playerId));

        player.changeNickname(nickname);

    }

    @Override
    public void updateComment(Long playerId, String comment) {
        Player player = store.findById(playerId)
                .orElseThrow(() -> new IllegalArgumentException("Player not found: " + playerId));

        player.updateComment(comment);
    }
}
