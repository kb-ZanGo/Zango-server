package kb.zango.domain.transaction.entity;

import jakarta.persistence.*;
import kb.zango.domain.diary.feedBack.entity.FeedBackBoard;
import lombok.Data;

@Entity
@Data
@Table(name="transaction_history")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="feedBackId", nullable = false)
    private FeedBackBoard feedBackBoard;

    private int trType;
    private String trName;
    private long amount;
    private String trDay;
    private String trTime;

}
