package developx.langrisser.match.repository;

import developx.langrisser.match.Match;
import developx.langrisser.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long>, MatchQueryDslRepository {

}
