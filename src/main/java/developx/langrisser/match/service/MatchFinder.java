package developx.langrisser.match.service;

import developx.langrisser.infrastructure.date.LocalDateFormatter;
import developx.langrisser.match.Match;
import developx.langrisser.match.repository.MatchRepository;
import developx.langrisser.match.service.dto.MatchesData;
import developx.langrisser.web.request.MatchesRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MatchFinder {

    private final MatchRepository matchRepository;

    private final LocalDateFormatter localDateFormatter;

    public Page<MatchesData> matches(MatchesRequest request){
        Page<Match> matches = matchRepository.findByCond(request, Pageable.ofSize(10));
        return matches.map(match -> MatchesData.fromMatch(match, localDateFormatter));
    }
}
