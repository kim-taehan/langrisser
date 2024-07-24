package developx.langrisser.match.service;

import developx.langrisser.infrastructure.date.LocalDateFormatter;
import developx.langrisser.match.Match;
import developx.langrisser.match.repository.MatchRepository;
import developx.langrisser.match.service.dto.MatchesData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MatchFinder {

    private final MatchRepository matchRepository;

    private final LocalDateFormatter localDateFormatter;

    public List<MatchesData> matches(){
        List<Match> matches = matchRepository.findAll();
        return matches.stream()
                .map(match -> MatchesData.fromMatch(match, localDateFormatter))
                .toList();
    }
}
