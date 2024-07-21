package developx.langrisser.match.service;

import developx.langrisser.match.Match;
import developx.langrisser.match.MatchRepository;
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

    public List<MatchesData> matches(){
        List<Match> matches = matchRepository.findAll();
        return matches.stream()
                .map(MatchesData::fromMatch)
                .toList();
    }
}
