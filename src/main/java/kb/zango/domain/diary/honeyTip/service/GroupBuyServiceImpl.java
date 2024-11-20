package kb.zango.domain.diary.honeyTip.service;

import kb.zango.domain.diary.honeyTip.entity.GroupBuyBoard;
import kb.zango.domain.diary.mapper.GroupBuyBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupBuyServiceImpl implements GroupBuyService {

    private final GroupBuyBoardMapper groupBuyMapper;

    @Autowired
    public GroupBuyServiceImpl(GroupBuyBoardMapper groupBuyMapper) {
        this.groupBuyMapper = groupBuyMapper;
    }

    @Override
    public void createGroupBuy(GroupBuyBoard groupBuy) {
        groupBuyMapper.insertGroupBuy(groupBuy);
    }

    @Override
    public GroupBuyBoard getGroupBuyByBoardId(Long boardId) {
        return groupBuyMapper.findGroupBuyByBoardId(boardId);
    }

    @Override
    public void updateGroupBuy(GroupBuyBoard groupBuy) {
        groupBuyMapper.updateGroupBuy(groupBuy);
    }

    @Override
    public void deleteGroupBuy(Long boardId) {
        groupBuyMapper.deleteGroupBuy(boardId);
    }
}
