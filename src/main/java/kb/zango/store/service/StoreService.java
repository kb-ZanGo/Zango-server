package kb.zango.store.service;

import kb.zango.store.domain.Store;
import kb.zango.store.dto.StoreResponse;
import kb.zango.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {
    private final StoreRepository storeRepository;

    public List<StoreResponse> findStore() {
        return storeRepository.findAll()
                .stream().map(StoreResponse::new)
                .collect(Collectors.toList());
    }

}
