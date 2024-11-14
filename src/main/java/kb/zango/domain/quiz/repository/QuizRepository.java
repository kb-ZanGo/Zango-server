package kb.zango.domain.quiz.repository;

import kb.zango.domain.quiz.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findByGroupId(Long groupId);
}
