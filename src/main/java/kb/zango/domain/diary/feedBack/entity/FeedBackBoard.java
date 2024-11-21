package kb.zango.domain.diary.feedBack.entity;

import jakarta.persistence.*;
import kb.zango.domain.board.entity.Board;
import kb.zango.domain.transaction.entity.Transaction;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class FeedBackBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedBackId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "feedBackBoard", cascade = CascadeType.REMOVE)
    List<Transaction>  transactions;
}
