package developx.langrisser.user.repository;

import developx.langrisser.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserQueryDslRepository {
    List<User> findByNameLike(String name);
}
