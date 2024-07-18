package developx.langrisser.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "before_names")
public class BeforeName {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "before_name_id")
    private Long id;

    @Column(length = 30)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public BeforeName(String name, User user) {
        this.name = name;
        this.user = user;
    }
}
