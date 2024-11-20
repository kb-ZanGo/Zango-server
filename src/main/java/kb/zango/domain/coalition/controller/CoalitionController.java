package kb.zango.domain.coalition.controller;

import kb.zango.domain.coalition.entity.Coalition;
import kb.zango.domain.coalition.service.CoalitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CoalitionController {

    private final CoalitionService coalitionService;

    @GetMapping("/api/coalition/{type}")
    public List<Coalition> getCoalition(@PathVariable String type) {
        return coalitionService.findCoalition(type);
    }
}
