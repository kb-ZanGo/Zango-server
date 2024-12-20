package kb.zango.domain.store.service;

import kb.zango.domain.store.dto.LocationWithRadius;
import kb.zango.domain.store.dto.StoreResponse;
import kb.zango.domain.store.repository.StoreRepository;
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

    public List<StoreResponse> findStore(LocationWithRadius location) {
        return storeRepository.findNearByStore(location.getLat(), location.getLon(), location.getRadius())
                .stream().map(StoreResponse::new)
                .collect(Collectors.toList());
    }

}
