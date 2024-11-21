package kb.zango.domain.diary.honeyTip.repository;

import kb.zango.domain.diary.honeyTip.entity.SmallCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SmallCategoryRepository extends JpaRepository<SmallCategory, Long> {

    @Query("Select c from SmallCategory c join fetch c.bigCategory where c.scId = :id")
    SmallCategory findByIdWithBigCategory(Long id);
}
