package kb.zango.domain.diary.honeyTip.entity;

import kb.zango.domain.diary.board.entity.Board;

public class HoneyTipBoard {
    private Board board;

    // 공구 => null 체크
    private int groupId;
    private String groupName;
    private String referenceSite;
    private int peopleLimit;
    private String kakaoLink;
}
