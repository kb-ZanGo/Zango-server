package kb.zango.domain.users.entity;

import jakarta.persistence.*;
import kb.zango.domain.diary.board.entity.Board;
import kb.zango.domain.comment.domain.Comment;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String connectId;
    private Long point;

    @OneToMany(mappedBy = "user")
    private List<Board> boards;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
}
