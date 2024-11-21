package kb.zango.domain.store.controller;

import kb.zango.common.Result;
import kb.zango.domain.store.dto.LocationWithRadius;
import kb.zango.domain.store.dto.StoreResponse;
import kb.zango.domain.store.service.StoreService;
import kb.zango.domain.store.service.UserStoreVisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StoreController {

    private final StoreService storeService;
    private final UserStoreVisitService userStoreVisitService;

    @GetMapping("/v2/map")
    public Result<List<StoreResponse>> getNearStoresV2(@ModelAttribute LocationWithRadius locationWithRadius) {
        List<StoreResponse> store = storeService.findStore(locationWithRadius);
        return new Result<>(store);
    }
}
