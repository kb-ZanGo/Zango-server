package kb.zango.domain.store.service;

import kb.zango.domain.store.entity.Store;
import kb.zango.domain.store.entity.UserStoreVisit;
import kb.zango.domain.store.repository.StoreRepository;
import kb.zango.domain.store.repository.UserStoreVisitRepository;
import kb.zango.domain.users.entity.User;
import kb.zango.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserStoreVisitService {

    private final UserStoreVisitRepository userStoreVisitRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    public void visitStore(Long userId, Long storeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Store store = storeRepository.findById(storeId);
        //check if user already visited 3 different store in a day using userStoreVisitRepository.findByUserIdAndVisitDate
//        userStoreVisitRepository.findByUserIdAndVisitDate(userId, LocalDate.now())
//                .stream()
//                .map(UserStoreVisit::getStore)
//                .count();
        //check if user already visited the store in a day using userStoreVisitRepository.findByUserIdAndStoreIdAndVisitDate
        userStoreVisitRepository.findByUserUserIdAndStoreIdAndVisitDate(userId, storeId, LocalDate.now())
                .ifPresentOrElse(
                        userStoreVisit -> {
                            throw new RuntimeException("Already visited");
                        },
                        () -> {
                            userStoreVisitRepository.save(new UserStoreVisit(user,store));
                            user.addQuizParticipationCount();
                        }
                );
    }
}
