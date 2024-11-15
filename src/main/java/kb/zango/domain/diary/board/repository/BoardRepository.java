package kb.zango.domain.diary.board.repository;

import kb.zango.domain.diary.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
