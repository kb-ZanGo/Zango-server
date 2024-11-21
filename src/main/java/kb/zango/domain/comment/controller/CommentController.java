package kb.zango.domain.comment.controller;

import kb.zango.domain.comment.dto.CommentResponseDTO;
import kb.zango.domain.comment.dto.CreateCommentRequestDTO;
import kb.zango.domain.comment.dto.UpdateCommentRequestDTO;
import kb.zango.domain.comment.repository.CommentRepository;
import kb.zango.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;

    @PostMapping
    public ResponseEntity<CommentResponseDTO> createComment(@RequestBody CreateCommentRequestDTO requestDTO) {
        CommentResponseDTO responseDTO = commentService.createComment(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDTO> updateComment(@PathVariable Long commentId, @RequestBody UpdateCommentRequestDTO requestDTO) {
        CommentResponseDTO responseDTO = commentService.updateComment(commentId, requestDTO);
        return ResponseEntity.ok(responseDTO); // 200
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build(); // 204
    }

    @PostMapping("/{commentId}/like")
    public ResponseEntity<Void> likeComment(@PathVariable Long commentId) {
        commentService.likeComment(commentId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{commentId}/unlike")
    public ResponseEntity<Void> unlikeComment(@PathVariable Long commentId) {
        commentService.unlikeComment(commentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<List<CommentResponseDTO>> getAllComments(@PathVariable Long boardId) {
        List<CommentResponseDTO> commentsByBoardId = commentService.findCommentByBoardId(boardId);

        return ResponseEntity.ok(commentsByBoardId);
    }

    @GetMapping("/{parentCommentId}/replies")
    public ResponseEntity<List<CommentResponseDTO>> getAllReCommentsByCommentId(@PathVariable Long parentCommentId) {
        List<CommentResponseDTO> reCommentByCommentId = commentService.findReCommentByCommentId(parentCommentId);

        return ResponseEntity.ok(reCommentByCommentId);
    }

}
