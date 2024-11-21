package kb.zango.domain.diary.honeyTip.service;

import kb.zango.domain.diary.honeyTip.entity.GroupBuyBoard;

public interface GroupBuyService {

    void createGroupBuy(GroupBuyBoard groupBuy);  // 공동구매 데이터 생성
    GroupBuyBoard getGroupBuyByBoardId(Long boardId);  // 공동구매 데이터 조회
    void updateGroupBuy(GroupBuyBoard groupBuy);  // 공동구매 데이터 수정
    void deleteGroupBuy(Long boardId);  // 공동구매 데이터 삭제
}
