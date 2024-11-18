package kb.zango.domain.store.entity;

import jakarta.persistence.*;
import kb.zango.domain.users.entity.User;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
public class UserStoreVisit {

    public UserStoreVisit(User user, Store store) {
        this.user = user;
        this.store = store;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    private LocalDate visitDate;

    @PrePersist
    protected void onCreate() {
        this.visitDate = LocalDate.now();
    }
}
