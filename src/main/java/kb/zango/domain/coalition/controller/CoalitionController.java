package kb.zango.domain.coalition.controller;

import kb.zango.domain.coalition.dto.CardCoalitionResponse;
import kb.zango.domain.coalition.entity.Card;
import kb.zango.domain.coalition.entity.Coalition;
import kb.zango.domain.coalition.repository.CardQueryRepository;
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
    private final CardQueryRepository cardQueryRepository;

    @GetMapping("/api/coalition")
    public List<Coalition> getAllCoalition() {
        return coalitionService.findAll();
    }
//    @GetMapping("/api/coalition/{type}")
//    public List<Coalition> getCoalition(@PathVariable String type) {
//        return coalitionService.findCoalition(type);
//    }

    @GetMapping("/api/coalition/{cardId}")
    public List<CardCoalitionResponse> cardCoalition(@PathVariable Long cardId) {
        return cardQueryRepository.getCoalition(cardId);
    }

    @GetMapping("/api/card")
    public List<Card> getCards() {
        return coalitionService.findCards();
    }
}
