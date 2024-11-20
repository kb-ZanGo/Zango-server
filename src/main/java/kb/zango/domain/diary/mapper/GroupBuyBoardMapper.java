package kb.zango.domain.diary.mapper;

import kb.zango.domain.diary.honeyTip.entity.GroupBuyBoard;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupBuyBoardMapper {

    void insertGroupBuy(GroupBuyBoard groupBuy);

    GroupBuyBoard findGroupBuyByBoardId(Long boardId);

    void updateGroupBuy(GroupBuyBoard groupBuy);

    void deleteGroupBuy(Long boardId);
}
