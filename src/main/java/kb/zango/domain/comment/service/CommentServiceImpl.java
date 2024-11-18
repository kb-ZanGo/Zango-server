package kb.zango.domain.comment.service;

import kb.zango.domain.board.entity.Board;
import kb.zango.domain.board.repository.BoardRepository;
import kb.zango.domain.comment.dto.CreateCommentRequestDTO;
import kb.zango.domain.comment.dto.CommentResponseDTO;
import kb.zango.domain.comment.dto.UpdateCommentRequestDTO;
import kb.zango.domain.comment.entity.Comment;
import kb.zango.domain.comment.repository.CommentRepository;
import kb.zango.domain.users.entity.User;
import kb.zango.domain.users.repository.UserRepository;
import kb.zango.global.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;


    @Override
    public CommentResponseDTO createComment(CreateCommentRequestDTO comment) {

        // 해당 게시물, 유저 존재하는지 확인
        Board board = boardRepository.findById(comment.getBoardId()).orElseThrow(() -> new ResourceNotFoundException("해당하는 게시물을 찾을 수 없습니다: " + comment.getBoardId()));
        User user = userRepository.findById(comment.getUserId()).orElseThrow(() -> new ResourceNotFoundException("해당하는 유저를 찾을 수 없습니다: " + comment.getUserId()));

        // newComment
        Comment newComment = new Comment();
        newComment.setBoard(board);
        newComment.setUser(user);

        // 해당 게시물이 대댓글인지 확인
        if(comment.getParentCommentId() != null){
            // 부모 댓글이 존재하는지 확인
            Comment parentComment = commentRepository.findById(comment.getParentCommentId()).orElseThrow(()-> new ResourceNotFoundException("해당하는 부모 댓글을 찾을 수 없습니다: " + comment.getParentCommentId()));
            newComment.setParentComment(parentComment);
        }
        newComment.setContent(comment.getContent());

        Comment savedComment = commentRepository.save(newComment);


        // 이부분 다시 고려(BoardDTO 관련)
        return new CommentResponseDTO(
                savedComment.getCommentId(),
                savedComment.getBoard().getBoardId(),
                savedComment.getUser().getUsername(),
                savedComment.getParentComment() != null ? savedComment.getParentComment().getCommentId() : null,
                savedComment.getContent(),
                savedComment.getRegiDate(),
                savedComment.getLikeCnt()
        );
    }

    @Override
    public CommentResponseDTO updateComment(Long commentId, UpdateCommentRequestDTO comment) {
        // 댓글을 클릭해서 해당 댓글의 내용을 변경한 상황 -> 다시 요청
        // 클라이언트에서 넘어오는 요청은 해당 댓글 id, boardid 이미 존재하고 내용만 달라지는 상황
        Optional<Comment> curComment = commentRepository.findById(commentId);
        if(curComment.isPresent()){
            Comment updatedComment = curComment.get();
            updatedComment.setContent(comment.getContent());
            Comment savedComment = commentRepository.save(updatedComment);

            return new CommentResponseDTO(
                    savedComment.getCommentId(),
                    savedComment.getBoard().getBoardId(),
                    savedComment.getUser().getUsername(),
                    savedComment.getParentComment() != null ? savedComment.getParentComment().getCommentId() : null,
                    savedComment.getContent(),
                    savedComment.getRegiDate(),
                    savedComment.getLikeCnt()
            );
        }
        else{
            // 댓글이 정상적으로 수정되지 않은 오류
            throw new ResourceNotFoundException("수정할 댓글을 찾지 못했습니다: " + commentId);
        }
    }

    // 필요
    @Override
    public void deleteComment(Long commentId) {
        Comment deleteComment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Service_deleteComment_지울 댓글 id가 존재하지 않습니다."+ commentId));
        commentRepository.delete(deleteComment);
    }

    // 부모댓글 조회
    @Override
    public List<CommentResponseDTO> findCommentByBoardId(Long boardId) {

        // 해당 게시물이 존재하는지 확인 => 왜 안먹히지
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new ResourceNotFoundException("CommentService_findAllByBoardId_해당하는 게시물 id가 존재하지 않습니다." + boardId));
        log.info("findCommentByBoardId: {}", board);

        List<Comment> comments = commentRepository.findCommentByBoardId(boardId);

        return comments.stream().map(comment -> new CommentResponseDTO(
                comment.getCommentId(),
                comment.getBoard().getBoardId(),
                comment.getUser().getUsername(),
                comment.getParentComment() != null ? comment.getParentComment().getCommentId() : null,
                comment.getContent(),
                comment.getRegiDate(),
                comment.getLikeCnt()
        )).collect(Collectors.toList());
    }

    @Override
    public List<CommentResponseDTO> findReCommentByCommentId(Long parentCommentId) {
        // 해당 댓글이 존재하는지 확인
        Comment parentComment = commentRepository.findById(parentCommentId).orElseThrow(() -> new ResourceNotFoundException("CommentService_findReCommentByCommentId_해당하는 부모 댓글이 존재하지 않습니다. " + parentCommentId));



        List<Comment> ReComments = commentRepository.findReCommentByCommentId(parentCommentId);

        return ReComments.stream().map(
                reComment -> new CommentResponseDTO(
                        reComment.getCommentId(),
                        reComment.getBoard().getBoardId(),
                        reComment.getUser().getUsername(),
                        reComment.getParentComment() != null ? reComment.getParentComment().getCommentId() : null,
                        reComment.getContent(),
                        reComment.getRegiDate(),
                        reComment.getLikeCnt()
                )).collect(Collectors.toList());
    }

    @Override
    public void likeComment(Long commentId) {
        Comment curComment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Service_likeComment_해당하는 댓글을 찾지 못했습니다: " + commentId));
        int likeCnt = curComment.getLikeCnt();
        curComment.setLikeCnt(++likeCnt);

        commentRepository.save(curComment);
    }

    @Override
    public void unlikeComment(Long commentId) {
        Comment curComment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("해당하는 댓글을 찾지 못했습니다: " + commentId));
        int likeCnt = curComment.getLikeCnt();
        curComment.setLikeCnt(--likeCnt);

        commentRepository.save(curComment);
    }
}
