package developx.langrisser.application.match.provided;

import developx.langrisser.application.match.dto.RegisterMatchCommand;

public interface MatchRegister {

    void register(RegisterMatchCommand command);

    void changeMemo(Long matchId, String memo);

    void remove(Long matchId);
}