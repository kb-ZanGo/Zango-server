package kb.zango.domain.comment.entity;

import jakarta.persistence.*;
import kb.zango.domain.board.entity.Board;
import kb.zango.domain.users.entity.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id", nullable = true)
    private Comment parentComment;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, updatable = false)
    private LocalDateTime regiDate;

    private LocalDateTime updateDate;

    @Column(nullable = false)
    private int likeCnt = 0;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> childComments = new ArrayList<>();

    // JPA에서 timestamp 관리
    @PrePersist
    protected void initialComment(){
        this.regiDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void updateComment(){
        this.updateDate = LocalDateTime.now();
    }
}
