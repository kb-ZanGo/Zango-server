package kb.zango.domain.board.repository;

import kb.zango.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("select b from Board b join fetch b.smallCategory where b.boardId = :id")
    Board findByBoardIdWithCategory(Long id);
}
