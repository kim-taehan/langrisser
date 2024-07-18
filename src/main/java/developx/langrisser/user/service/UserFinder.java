package developx.langrisser.user.service;

import developx.langrisser.user.User;
import developx.langrisser.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserFinder {

    public static final String PERCENT = "%";
    private final UserRepository userRepository;

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    public List<User> findLikeUserName(String userName) {
        return userRepository.findByNameLike(PERCENT + userName + PERCENT);
    }
}
