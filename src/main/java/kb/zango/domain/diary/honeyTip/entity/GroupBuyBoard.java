package kb.zango.domain.diary.honeyTip.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "groupbuy")
public class GroupBuyBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupBuyId;
    private Long boardId;
    private String groupBuyItem;
    private String referenceSite;
    private Integer peopleLimit;
    private String kakaoLink;

    public GroupBuyBoard(Long groupBuyId, Long boardId, String groupBuyItem, String referenceSite, Integer peopleLimit,
                         String kakaoLink) {
        this.groupBuyId = groupBuyId;
        this.boardId = boardId;
        this.groupBuyItem = groupBuyItem;
        this.referenceSite = referenceSite;
        this.peopleLimit = peopleLimit;
        this.kakaoLink = kakaoLink;
    }

    public Long getGroupBuyId() {
        return groupBuyId;
    }

    public void setGroupBuyId(Long groupBuyId) {
        this.groupBuyId = groupBuyId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public String getGroupBuyItem() {
        return groupBuyItem;
    }

    public void setGroupBuyItem(String groupBuyItem) {
        this.groupBuyItem = groupBuyItem;
    }

    public String getReferenceSite() {
        return referenceSite;
    }

    public void setReferenceSite(String referenceSite) {
        this.referenceSite = referenceSite;
    }

    public Integer getPeopleLimit() {
        return peopleLimit;
    }

    public void setPeopleLimit(Integer peopleLimit) {
        this.peopleLimit = peopleLimit;
    }

    public String getKakaoLink() {
        return kakaoLink;
    }

    public void setKakaoLink(String kakaoLink) {
        this.kakaoLink = kakaoLink;
    }
}

