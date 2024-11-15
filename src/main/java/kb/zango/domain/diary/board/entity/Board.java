package kb.zango.domain.diary.board.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import kb.zango.domain.diary.honeyTip.entity.SmallCategory;
import kb.zango.domain.users.entity.User;
import lombok.Data;

@Entity
@Data
public class Board {

    @Id
    private Long boardId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @Column(nullable = false)
    private int board_type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scId", nullable = false)
    private SmallCategory smallCategory;

    private String title;

    private String content;

    private LocalDateTime regiDate;

    private LocalDateTime updateDate;

    private int likeCnt;
    private int commentCnt;
    private int view_cnt;

    @PrePersist
    protected void initialBoard() {
        this.regiDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void updateBoard() {
        this.updateDate = LocalDateTime.now();
    }
}