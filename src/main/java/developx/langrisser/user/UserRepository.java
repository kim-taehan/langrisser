package developx.langrisser.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameLike(String name);

//    List<User> findByNameLikeAndServer(String s, Server server);
}
