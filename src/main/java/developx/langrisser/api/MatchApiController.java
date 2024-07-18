package developx.langrisser.api;

import developx.langrisser.api.request.MatchCreateRequest;
import developx.langrisser.match.service.MatchCreator;
import developx.langrisser.user.service.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/matches")
@RequiredArgsConstructor
public class MatchApiController {

    private final MatchCreator matchCreator;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody MatchCreateRequest request){
        matchCreator.create(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
