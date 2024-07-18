package developx.langrisser.match.service;

import developx.langrisser.api.request.MatchCreateRequest;
import developx.langrisser.match.Match;
import developx.langrisser.match.MatchRepository;
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
    public void create(MatchCreateRequest request) {
        User user = userFinder.findById(request.userId());
        Match save = matchRepository.save(request.toEntity(user));
        if (save.getId() < 0) {
            throw new IllegalStateException();
        }
    }

}
