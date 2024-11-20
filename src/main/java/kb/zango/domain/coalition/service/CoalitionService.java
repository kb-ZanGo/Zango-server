package kb.zango.domain.coalition.service;

import kb.zango.domain.coalition.entity.Coalition;
import kb.zango.domain.coalition.repository.CoalitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoalitionService {

    private final CoalitionRepository coalitionRepository;

    public List<Coalition> findAll() {
        return coalitionRepository.findAll();
    }
    public List<Coalition> findCoalition(String type) {
        return coalitionRepository.findByType(type);
    }
}
