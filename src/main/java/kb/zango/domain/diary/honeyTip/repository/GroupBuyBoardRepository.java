package kb.zango.domain.diary.honeyTip.repository;

import kb.zango.domain.diary.honeyTip.entity.GroupBuyBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupBuyBoardRepository extends JpaRepository<GroupBuyBoard, Long> {

    GroupBuyBoard findByBoardId(Long boardId);
}
