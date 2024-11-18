package kb.zango.domain.transaction.entity;

import jakarta.persistence.*;
import kb.zango.domain.board.entity.Board;
import lombok.Data;

@Entity
@Data
@Table(name="transaction_history")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id", nullable = false)
    private Board board;

    private int trType;
    private String trName;
    private long amount;
    private String trDay;
    private String trTime;

}
