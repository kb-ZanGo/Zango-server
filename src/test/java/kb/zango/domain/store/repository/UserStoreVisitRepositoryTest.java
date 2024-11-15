package kb.zango.domain.store.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserStoreVisitRepositoryTest {

    @Autowired
    UserStoreVisitRepository userStoreVisitRepository;

    @DisplayName("")
    @Test
    void 쿼리테스트() throws Exception{
        //given
        userStoreVisitRepository.findByUserIdAndStoreIdAndVisitDate(1L, 1L, LocalDate.now());

        //when

        //then

     }
}