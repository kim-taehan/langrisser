package developx.langrisser.user.service;

import developx.langrisser.user.User;
import developx.langrisser.user.repository.UserRepository;
import developx.langrisser.web.request.UsersRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.data.domain.Pageable.ofSize;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserFinder {

    public static final String PERCENT = "%";
    private final UserRepository userRepository;

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    public Page<User> findUsers(UsersRequest request) {
          return userRepository.findByUserNameNameLikeAndServer(request.userName(), request.server(), ofSize(10));
    }
}
