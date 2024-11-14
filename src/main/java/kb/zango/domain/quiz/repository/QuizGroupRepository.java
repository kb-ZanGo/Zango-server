package kb.zango.domain.quiz.repository;

import kb.zango.domain.quiz.entity.QuizGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizGroupRepository extends JpaRepository<QuizGroup, Long> {
    List<QuizGroup> findByIsUsedFalse();
}
