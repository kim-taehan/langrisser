package developx.langrisser.application.player.provided;

import developx.langrisser.application.player.dto.RegisterPlayerResult;
import developx.langrisser.application.player.dto.RegisterPlayerCommand;

public interface PlayerRegister {

    RegisterPlayerResult register(RegisterPlayerCommand request);

    void changeNickname(Long playerId, String nickname);

    void updateComment(Long id, String comment);
}
