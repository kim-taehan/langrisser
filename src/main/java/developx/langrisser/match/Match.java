package developx.langrisser.match;

import developx.langrisser.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "match_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Stage stage;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private WinOrLose winOrLose;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Orders orders;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private MatchType matchType;

    private LocalDateTime matchedDateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Match(Stage stage, WinOrLose winOrLose, Orders orders, MatchType matchType, LocalDateTime matchedDateTime, User user) {
        this.stage = stage;
        this.winOrLose = winOrLose;
        this.orders = orders;
        this.matchType = matchType;
        this.matchedDateTime = matchedDateTime;
        this.user = user;
    }
}
