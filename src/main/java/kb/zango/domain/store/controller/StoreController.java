package kb.zango.domain.store.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import kb.zango.common.Result;
import kb.zango.domain.store.dto.LocationWithRadius;
import kb.zango.domain.store.dto.StoreResponse;
import kb.zango.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StoreController {

    private final StoreService storeService;

//    @GetMapping("/map")
//    public Result<List<StoreResponse>> getStore() {
//        return new Result<>(storeService.findStore());
//    }

    @GetMapping("/v2/map")
    public Result<List<StoreResponse>> getNearStoresV2(@ModelAttribute LocationWithRadius locationWithRadius) {
        List<StoreResponse> store = storeService.findStore(locationWithRadius);
        return new Result<>(store);
//        return Response.success(complexes);
    }
}
