package kb.zango.domain.store.controller;

import kb.zango.domain.store.service.UserStoreVisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserStoreVisitController {

    private final UserStoreVisitService userStoreVisitService;

    @PostMapping("/store/{storeId}")
    public void visitStore(@PathVariable Long storeId, @RequestBody Long userId) {
        userStoreVisitService.visitStore(userId, storeId);
    }
}
