package kb.zango.domain.diary.feedBack.repository;

import kb.zango.domain.diary.feedBack.entity.FeedBackBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBackBoard, Long> {

    @Query("SELECT f.feedBackId FROM FeedBackBoard f WHERE f.board.boardId = :boardId")
    Optional<Long> findFeedBackIdByBoardId(@Param("boardId") Long boardId);

    @Query("SELECT f FROM FeedBackBoard f WHERE f.feedBackId = :feedBackId")
    Optional<FeedBackBoard> findByFeedBackId(@Param("feedBackId")Long feedBackId);
}
