package kb.zango.store.controller;

import kb.zango.store.common.Result;
import kb.zango.store.dto.StoreResponse;
import kb.zango.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/map")
    public Result<List<StoreResponse>> getStore() {
        return new Result<>(storeService.findStore());
    }
}
