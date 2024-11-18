package kb.zango.domain.board.entity;

import jakarta.persistence.*;
import kb.zango.domain.users.entity.User;
import lombok.Data;

@Entity
@Data
public class Board {

    @Id
    private Long boardId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name="user_id", nullable=false)
    private User user;
}
