package developx.langrisser.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 30)
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private Server server;

    private int pointRank;

    private int point;

    private int cristalRank;

    private int cristal;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<BeforeName> beforeNames = new ArrayList<>();

    @Builder
    public User(String name, Server server, int pointRank, int point, int cristalRank, int cristal, Collection<String> beforeNames) {
        this.name = name;
        this.server = server;
        this.pointRank = pointRank;
        this.point = point;
        this.cristalRank = cristalRank;
        this.cristal = cristal;

        if(!Objects.isNull(beforeNames)){
            this.beforeNames.addAll(beforeNames.stream()
                    .filter(beforeName -> !name.equals(beforeName))
                    .map(beforeName -> BeforeName.builder().user(this).name(beforeName).build())
                    .toList()
            );
        }
    }
}
