package kb.zango.domain.transaction.repository;

import kb.zango.domain.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // 특정 게시물 아이디로 거래내역 조회
    @Query("SELECT t FROM Transaction t WHERE t.feedBackBoard.feedBackId = :feedBackId")
    List<Transaction> findTransactionsByFeedBackId(Long feedBackId);

    // 특정 게시판 ID와 특정 날짜에 해당하는 거래 내역을 조회
    @Query("SELECT t FROM Transaction t WHERE t.feedBackBoard.feedBackId = :feedBackId AND t.trDay = :trDay")
    List<Transaction> findByBoard_BoardIdAndTrDay(@Param("feedBackId") Long feedBackId, @Param("trDay")String trDay);
}
