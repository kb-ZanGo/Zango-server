package kb.zango.domain.comment.repository;

import kb.zango.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 부모 댓글 가져오기
    @Query(value = "SELECT c FROM Comment c WHERE c.parentComment IS NULL AND c.board.boardId= :boardId order by c.regiDate")
    List<Comment> findCommentByBoardId(@Param("boardId") Long boardId);

    // 대댓글 가져오기
    @Query(value = "SELECT c FROM Comment c WHERE c.parentComment.commentId = :parentCommentId ORDER BY c.regiDate")
    List<Comment> findReCommentByCommentId(@Param("parentCommentId") Long parentCommentId);
}
