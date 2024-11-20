package kb.zango.domain.diary.honeyTip.dto;

import java.time.LocalDateTime;
import kb.zango.domain.diary.honeyTip.entity.SmallCategory;
import kb.zango.domain.users.entity.User;
import lombok.Data;

@Data
public class HoneyTipBoardRequest {

    private Long boardId;
    private String title;
    private String content;
//    private SmallCategory smallCategory;
//    private User user;
    private Long sc_id;
    private Long user_id;
    private LocalDateTime regiDate;
    private LocalDateTime updateDate;

    private String groupBuyItem; // 공구품목
    private String referenceSite; // 참고사이트
    private int peopleLimit; // 모집인원
    private String kakaoLink; // 옾챗링크

    private boolean isGroupBuy;

    public HoneyTipBoardRequest(Long boardId, String title, String content, Long sc_id, Long user_id,
                                boolean isGroupBuy, String groupBuyItem, String referenceSite, int peopleLimit, String kakaoLink) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.sc_id = sc_id;
        this.user_id = user_id;
        this.isGroupBuy = isGroupBuy;

        if (isGroupBuy) {
            this.groupBuyItem = groupBuyItem;
            this.referenceSite = referenceSite;
            this.peopleLimit = peopleLimit;
            this.kakaoLink = kakaoLink;
        }
    }

    public HoneyTipBoardRequest() {

    }
}
