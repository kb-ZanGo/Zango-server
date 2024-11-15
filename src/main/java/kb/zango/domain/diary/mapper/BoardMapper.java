package kb.zango.domain.diary.mapper;

import kb.zango.domain.diary.board.entity.Board;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

//@Mapper
public interface BoardMapper {

    void insertBoard(Board board);

//    Board findBoardById(Long boardId);
//
//    List<Board> findAllBoards();
//
//    void updateBoard(Board board);
//
//    void deleteBoard(Long boardId);
}
