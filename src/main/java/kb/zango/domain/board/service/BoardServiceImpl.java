package kb.zango.domain.board.service;

import java.util.List;
import java.util.stream.Collectors;
import kb.zango.domain.board.dto.BoardListResponse;
import kb.zango.domain.board.entity.Board;
import kb.zango.domain.board.repository.BoardRepository;
import kb.zango.domain.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    @Override
    public void createBoard(Board board) {
        boardMapper.insertBoard(board);
    }

    @Override
    public Board getBoardById(Long boardId) {
        return boardMapper.findBoardById(boardId);
    }

    @Override
    public List<BoardListResponse> getAllBoards() {
        List<Board> boards = boardMapper.findAllBoards();

        return boards.stream()
                .map(board -> new BoardListResponse(
                        board.getBoardId(),
                        board.getSmallCategory(),
                        board.getTitle(),
                        board.getUser(),
                        board.getLikeCnt()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void updateBoard(Board board) {
        boardMapper.updateBoard(board);
    }

    @Override
    public void deleteBoard(Long boardId) {
        boardMapper.deleteBoard(boardId);
    }
}
