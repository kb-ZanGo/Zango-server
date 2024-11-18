package kb.zango.domain.users.entity;

import jakarta.persistence.*;

import kb.zango.domain.board.entity.Board;

import kb.zango.domain.comment.entity.Comment;

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
    private Integer quizParticipationCount;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Board> boards;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    public void addPoint(Long point) {
        this.point += point;
    }

    public void addQuizParticipationCount() {
        this.quizParticipationCount++;
    }

}
