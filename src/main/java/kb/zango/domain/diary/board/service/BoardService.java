package kb.zango.domain.diary.board.service;

import java.util.List;
import kb.zango.domain.diary.board.dto.BoardListResponse;
import kb.zango.domain.diary.board.entity.Board;

public interface BoardService {

    void createBoard(Board board);
    Board getBoardById(Long boardId);
    List<BoardListResponse> getAllBoards();
    void updateBoard(Board board);
    void deleteBoard(Long boardId);
}
