package kb.zango.domain.diary.honeyTip.entity;

import kb.zango.domain.board.entity.Board;
import kb.zango.domain.users.entity.User;

public class HoneyTipBoard {

//    private Long id;
    private Board board;
    private GroupBuyBoard groupBuyBoard;

    // 기본
    public HoneyTipBoard(Board board) {
        this.board = board;
    }

////    public Long getId() {
////        return id;
////    }
////
//    public void setId(Long id) {
//        this.id = id;
//    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public GroupBuyBoard getGroupBuyBoard() {
        return groupBuyBoard;
    }

    public void setGroupBuyBoard(GroupBuyBoard groupBuyBoard) {
        this.groupBuyBoard = groupBuyBoard;
    }

    // 공동구매
    public HoneyTipBoard(Board board, GroupBuyBoard groupBuyBoard) {
        this.board = board;
        this.groupBuyBoard = groupBuyBoard;
    }

}