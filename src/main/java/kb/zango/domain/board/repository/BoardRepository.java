package kb.zango.domain.board.repository;

import kb.zango.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {

    // boardId로 board찾기
    @Query("select b from Board b where b.boardId = :boardId")
    Board findByBoardId(Long boardId);

    @Modifying
    @Query("delete from Board b where b.boardId = :boardId")
    void deleteByBoardId(Long boardId);
    @Query("select b from Board b join fetch b.smallCategory where b.boardId = :id")
    Board findByBoardIdWithCategory(Long id);
}
