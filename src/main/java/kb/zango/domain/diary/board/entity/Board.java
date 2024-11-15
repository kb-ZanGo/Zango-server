package kb.zango.domain.diary.board.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import kb.zango.domain.users.entity.User;
import lombok.Data;

@Entity
@Data
public class Board {

    @Id
    private Long boardId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name="user_id", nullable=false)
    private User user;
//
//    private int board_type;
//    private String scId;
//    private String title;
//    private String content;
//    private Timestamp regiDate;
//    private Timestamp updateDate;
//    private int likeCnt;
//    private int commentCnt;
//    private int view_cnt;
}