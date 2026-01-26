package developx.langrisser.domain.player;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(
        name = "player",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_player_nickname_server",
                        columnNames = {"nickname", "server"}
                )
        }
)
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Slf4j
@Builder
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ServerType server;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "crystal_count", nullable = false)),
            @AttributeOverride(name = "rank", column = @Column(name = "crystal_rank"))
    })
    private MetricRank crystal;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "summit_score", nullable = false)),
            @AttributeOverride(name = "rank", column = @Column(name = "summit_rank"))
    })
    private MetricRank summit;

    @OneToMany(
            mappedBy = "player",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PlayerNicknameHistory> nicknameHistories = new ArrayList<>();

    @OneToMany(
            mappedBy = "player",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PlayerSummitRank> summitRanks = new ArrayList<>();

    private String comment;

    private LocalDate lastMatchDate;

    @Column(nullable = false)
    private boolean hasSpecialNickname;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private SeasonTier tier;

    protected Player(String nickname, ServerType server) {
        this.nickname = nickname;
        this.server = server;
        this.crystal = MetricRank.initial();
        this.summit = MetricRank.initial();
        this.nicknameHistories = new ArrayList<>();
        this.comment = "";
        this.hasSpecialNickname = containsSpecialChar(nickname);
    }

    public static Player of(String nickname, ServerType server, MetricRank crystal, MetricRank summit) {
        return Player.builder()
                .nickname(nickname)
                .server(server)
                .crystal(crystal)
                .summit(summit)
                .nicknameHistories(new ArrayList<>())
                .hasSpecialNickname(containsSpecialChar(nickname))
                .summitRanks(new ArrayList<>())
                .build();

    }

    public static Player create(String nickname, ServerType server) {
        return new Player(nickname, server);
    }

    public void changeNickname(String newNickname) {

        if(newNickname.equals(this.nickname)){
            throw new IllegalArgumentException("New nickname is the same as the current nickname.");
        }
        addPreviousNicknames(this.nickname);
        this.nickname = newNickname;
        this.hasSpecialNickname = containsSpecialChar(nickname);
    }

    public void addPreviousNicknames(String nickname) {
        List<String> oldNicknames = nicknameHistories.stream()
                .map(PlayerNicknameHistory::getNickname)
                .toList();

        if (!oldNicknames.contains(nickname)) {
            this.nicknameHistories.add( PlayerNicknameHistory.of(this, nickname));
        }
    }

    public void updateComment(String comment) {
        this.comment = comment;
    }

    public void updateLastMatchDate(LocalDate lastMatchDate) {
        this.lastMatchDate = lastMatchDate;
    }

    private static final Pattern ALLOWED_NICKNAME_PATTERN =
            Pattern.compile("^[가-힣a-zA-Z0-9]+$");

    private static boolean containsSpecialChar(String nickname) {
        if (nickname == null) {
            return true;
        }

        // 공백 제거
        String normalized = nickname.replaceAll("\\s+", "");

        // 허용된 문자만 있는지 검사
        return !ALLOWED_NICKNAME_PATTERN.matcher(normalized).matches();
    }

    public void addSummitRankIfAbsent(String season, SeasonTier tier) {
        this.summitRanks.stream()
                .filter(r -> r.getSeason().equals(season))
                .findFirst()
                .ifPresentOrElse(
                        r -> log.debug("Summit rank already exists: {}", r),
                        () -> {
                            this.summitRanks.add(
                                    PlayerSummitRank.of(this, season, tier)
                            );
                            updateBestTier(tier);
                        }
                );
    }

    private void updateBestTier(SeasonTier newTier) {
        if (this.tier == null || newTier.isBetterThan(this.tier)) {
            this.tier = newTier;
        }
    }

    public void updateInfo(String nickname, String cristal, String cristalRank, String point, String pointRank) {
        this.nickname = nickname;
        this.crystal = MetricRank.of(cristal, cristalRank);
        this.summit = MetricRank.of(point, pointRank);
    }
}
