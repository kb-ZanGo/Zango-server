package kb.zango.domain.store.repository;

import kb.zango.domain.store.entity.UserStoreVisit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface UserStoreVisitRepository extends JpaRepository<UserStoreVisit, Long> {

    Optional<UserStoreVisit> findByUserIdAndStoreIdAndVisitDate(Long userId, Long storeId, LocalDate visitDate);
}
