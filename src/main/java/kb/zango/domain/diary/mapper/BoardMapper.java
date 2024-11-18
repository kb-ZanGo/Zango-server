package kb.zango.domain.diary.mapper;

import java.util.List;
import kb.zango.domain.diary.board.entity.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardMapper {
//    @Options(useGeneratedKeys = true, keyProperty = "boardId", keyColumn = "board_id")
    void insertBoard(Board board);

    Board findBoardById(Long boardId);

    List<Board> findAllBoards();

    void updateBoard(Board board);

    void deleteBoard(Long boardId);
}
