package kb.zango.domain.diary.honeyTip.dto;

import java.time.LocalDateTime;
import kb.zango.domain.board.entity.Board;
import kb.zango.domain.diary.honeyTip.entity.GroupBuyBoard;
import kb.zango.domain.diary.honeyTip.entity.HoneyTipBoard;
import kb.zango.domain.diary.honeyTip.entity.SmallCategory;
import kb.zango.domain.users.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HoneyTipBoardResponse {

    private Long boardId;
    private String title;
    private String content;
    private String smallCategoryName;
    private String userName;
    private int likeCnt;
    private int commentCnt;
    private LocalDateTime regiDate;
    private LocalDateTime updateDate;

    private String groupBuyItem;
    private String referenceSite;
    private int peopleLimit;
    private String kakaoLink;


    public static HoneyTipBoardResponse HoneyTipWithGroupBuy(Board board, GroupBuyBoard groupBuyBoard) {
        HoneyTipBoardResponse honeyWithGroupBuy = NormalHoneyTip(board);
        honeyWithGroupBuy.setGroupBuyItem(groupBuyBoard.getGroupBuyItem());
        honeyWithGroupBuy.setReferenceSite(groupBuyBoard.getReferenceSite());
        honeyWithGroupBuy.setPeopleLimit(groupBuyBoard.getPeopleLimit());
        honeyWithGroupBuy.setKakaoLink(groupBuyBoard.getKakaoLink());
        return honeyWithGroupBuy;
    }

    public static HoneyTipBoardResponse NormalHoneyTip(Board board) {
        HoneyTipBoardResponse honeyTipBoardResponse = new HoneyTipBoardResponse();
        honeyTipBoardResponse.setBoardId(board.getBoardId());
        honeyTipBoardResponse.setTitle(board.getTitle());
        honeyTipBoardResponse.setContent(board.getContent());
        honeyTipBoardResponse.setSmallCategoryName(board.getSmallCategory().getName());
        honeyTipBoardResponse.setUserName(board.getUser().getUsername());
        honeyTipBoardResponse.setLikeCnt(board.getLikeCnt());
        honeyTipBoardResponse.setCommentCnt(board.getCommentCnt());
        honeyTipBoardResponse.setRegiDate(board.getRegiDate());
        honeyTipBoardResponse.setUpdateDate(board.getUpdateDate());
        return honeyTipBoardResponse;
    }

}
