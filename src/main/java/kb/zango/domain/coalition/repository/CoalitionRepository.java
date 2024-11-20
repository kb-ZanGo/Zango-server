package kb.zango.domain.coalition.repository;

import kb.zango.domain.coalition.entity.Coalition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoalitionRepository extends JpaRepository<Coalition, Long> {

    List<Coalition> findByType(String type);
}
