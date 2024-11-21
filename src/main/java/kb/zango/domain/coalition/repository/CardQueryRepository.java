package kb.zango.domain.coalition.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kb.zango.domain.coalition.dto.CardCoalitionResponse;
import kb.zango.domain.coalition.entity.Coalition;
import kb.zango.domain.coalition.entity.QCardCoalition;
import kb.zango.domain.coalition.entity.QCoalition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kb.zango.domain.coalition.entity.QCardCoalition.*;
import static kb.zango.domain.coalition.entity.QCoalition.*;

@Repository
@RequiredArgsConstructor
public class CardQueryRepository {

    private final JPAQueryFactory queryFactory;

    @PersistenceContext
    private EntityManager em;

    public List<CardCoalitionResponse> getCoalition(Long id) {

        return queryFactory.select(
                        Projections.constructor(CardCoalitionResponse.class,
                                coalition.lat,
                                coalition.lon,
                                coalition.address,
                                cardCoalition.card.id,
                                coalition.type,
                                coalition.name,
                                cardCoalition.content
                        )
                ).from(coalition)
                .join(cardCoalition)
                .on(coalition.type.eq(cardCoalition.type))
                .where(cardCoalition.card.id.eq(id))
                .fetch();
    }
}
