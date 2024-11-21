package kb.zango.domain.diary.feedBack.repository;

import kb.zango.domain.diary.feedBack.dto.HomeListFeedBackDTO;
import kb.zango.domain.diary.feedBack.entity.FeedBackBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBackBoard, Long> {

    @Query("SELECT f.feedBackId FROM FeedBackBoard f WHERE f.board.boardId = :boardId")
    Optional<Long> findFeedBackIdByBoardId(@Param("boardId") Long boardId);

    @Query("SELECT f FROM FeedBackBoard f WHERE f.feedBackId = :feedBackId")
    Optional<FeedBackBoard> findByFeedBackId(@Param("feedBackId")Long feedBackId);

    // 홈 화면 게시물 리스트 조회
    @Query("SELECT f FROM FeedBackBoard f ORDER BY f.board.regiDate DESC")
    List<FeedBackBoard> getFeedBackList();

    // 피드백 게시판 좋아요 기능
    @Modifying
    @Transactional
    @Query("UPDATE FeedBackBoard f SET f.board.likeCnt = f.board.likeCnt + 1 WHERE f.board.boardId = :boardId")
    void incrementLikeCntFeedBack(@Param("boardId") Long boardId);
}
