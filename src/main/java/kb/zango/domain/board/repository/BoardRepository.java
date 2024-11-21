package kb.zango.domain.board.repository;

import java.util.List;
import kb.zango.domain.board.entity.Board;
import org.apache.ibatis.annotations.Param;
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

    @Query("select b from Board b "
            + "join fetch b.smallCategory s "
            + "join fetch s.bigCategory bc "
            + "where bc.bcId = :bigCategoryId "
            + "order by b.regiDate desc ")
    List<Board> findAllHoney(Long bigCategoryId);


    // 조회수 증가
    @Modifying
    @Query("UPDATE Board b SET b.view_cnt = b.view_cnt + 1 WHERE b.boardId = :boardId")
    void incrementViewCnt(@Param("boardId") Long boardId);

    // 꿀팁기준 조회수 상위 5개
    @Query("SELECT b FROM Board b ORDER BY b.view_cnt DESC LIMIT 5")
    List<Board> getPopularBoard();
}
