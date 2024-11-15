package kb.zango.domain.store.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kb.zango.domain.store.entity.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StoreRepository{

    @PersistenceContext
    private EntityManager em;

    public List<Store> findNearByStore(double latitude, double longitude,double radius) {
        String query = "SELECT s.*, "
                + "(6371 * ACOS(COS(RADIANS(:latitude)) "
                + "* COS(RADIANS(s.y)) "
                + "* COS(RADIANS(s.x) - RADIANS(:longitude)) "
                + "+ SIN(RADIANS(:latitude)) "
                + "* SIN(RADIANS(s.y)))) AS distance "
                + "FROM Store s "
                + "HAVING distance <= :radius "
                + "ORDER BY distance";


        return em.createNativeQuery(query,Store.class)
                .setParameter("latitude", latitude)
                .setParameter("longitude", longitude)
                .setParameter("radius", radius)
                .getResultList();

    }
}
