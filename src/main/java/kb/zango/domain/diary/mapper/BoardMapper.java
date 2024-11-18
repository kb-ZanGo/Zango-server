package kb.zango.domain.diary.mapper;

import java.util.List;
import kb.zango.domain.board.entity.Board;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

    void insertBoard(Board board);
    Board findBoardById(Long boardId);
    List<Board> findAllBoards();
    void updateBoard(Board board);
    void deleteBoard(Long boardId);
}
