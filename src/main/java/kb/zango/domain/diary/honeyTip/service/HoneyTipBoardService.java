package kb.zango.domain.diary.honeyTip.service;

import kb.zango.domain.diary.honeyTip.dto.HoneyTipBoardRequest;
import kb.zango.domain.diary.honeyTip.dto.HoneyTipBoardResponse;
import java.util.List;

public interface HoneyTipBoardService {

    Long insertHoneyTipBoard(HoneyTipBoardRequest request);  // 꿀팁 게시글 생성
    HoneyTipBoardResponse getHoneyTipBoard(Long boardId);    // 꿀팁 게시글 조회
    void updateHoneyTipBoard(HoneyTipBoardRequest request);  // 꿀팁 게시글 수정
    void deleteHoneyTipBoard(Long boardId);                  // 꿀팁 게시글 삭제
    List<HoneyTipBoardResponse> getAllHoneyTipBoards(Long bigCategoryId);      // 전체 꿀팁 게시글 목록 조회
}
