package kb.zango.domain.comment.service;

import kb.zango.domain.comment.dto.CreateCommentRequestDTO;
import kb.zango.domain.comment.dto.CommentResponseDTO;
import kb.zango.domain.comment.dto.UpdateCommentRequestDTO;

import java.util.List;

public interface CommentService {

     CommentResponseDTO createComment(CreateCommentRequestDTO comment);

     CommentResponseDTO updateComment(Long commentId, UpdateCommentRequestDTO comment);

     void deleteComment(Long CommentId);

     // 댓글만 조회
     List<CommentResponseDTO> findCommentByBoardId(Long boardId);

     // 대댓글 조회
     List<CommentResponseDTO> findReCommentByCommentId(Long parentCommentId);

     void likeComment(Long commentId);

     void unlikeComment(Long commentId);

}