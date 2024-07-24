package developx.langrisser.match.service;

import developx.langrisser.match.Match;
import developx.langrisser.match.repository.MatchRepository;
import developx.langrisser.user.User;
import developx.langrisser.user.service.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchCreator {

    private final MatchRepository matchRepository;
    private final UserFinder userFinder;



    @Transactional
    public void register(Long userId, Match match) {
        User user = userFinder.findById(userId);
        match.setUser(user);
        Match save = matchRepository.save(match);
        if (save.getId() < 0) {
            throw new IllegalStateException();
        }
    }
}
