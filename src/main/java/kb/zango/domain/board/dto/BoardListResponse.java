package kb.zango.domain.board.dto;

import kb.zango.domain.diary.honeyTip.entity.SmallCategory;
import kb.zango.domain.users.entity.User;

public class BoardListResponse {

    private Long boardId;
    private SmallCategory smallCategory;
    private String title;
    private User user;
    private int likeCnt;

    public BoardListResponse(Long boardId, SmallCategory smallCategory, String title, User user, int likeCnt) {
        this.boardId = boardId;
        this.smallCategory = smallCategory;
        this.title = title;
        this.user = user;
        this.likeCnt = likeCnt;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public SmallCategory getSmallCategory() {
        return smallCategory;
    }

    public void setSmallCategory(SmallCategory smallCategory) {
        this.smallCategory = smallCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getLikeCnt() {
        return likeCnt;
    }

    public void setLikeCnt(int likeCnt) {
        this.likeCnt = likeCnt;
    }
}
