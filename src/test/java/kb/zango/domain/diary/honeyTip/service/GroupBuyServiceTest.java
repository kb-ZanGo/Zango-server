//package kb.zango.domain.diary.honeyTip.service;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import kb.zango.domain.diary.honeyTip.entity.GroupBuyBoard;
//import kb.zango.domain.diary.mapper.GroupBuyBoardMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.transaction.annotation.Transactional;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@Transactional
//public class GroupBuyServiceTest {
//
//    @Autowired
//    private GroupBuyService groupBuyService;
//
//    @Autowired
//    private GroupBuyBoardMapper groupBuyBoardMapper;
//
//    private GroupBuyBoard groupBuyRequest;
//
//    @BeforeEach
//    public void setUp() {
//        groupBuyRequest = new GroupBuyBoard(
//                null,
//                1L,
//                "Test Item",
//                "Test Site",
//                10,
//                "kakao url"
//        );
//    }
//
//    @Test
//    public void testInsertGroupBuy() {
//        groupBuyService.createGroupBuy(groupBuyRequest);
//
//        GroupBuyBoard groupBuy = groupBuyBoardMapper.findGroupBuyByBoardId(groupBuyRequest.getBoardId());
//        assertNotNull(groupBuy);
//        assertEquals("Test Item", groupBuy.getGroupBuyItem());
//    }
//
//    @Test
//    public void testUpdateGroupBuy() {
//        groupBuyService.createGroupBuy(groupBuyRequest);
//
//        groupBuyRequest.setGroupBuyItem("Updated Item");
//        groupBuyService.updateGroupBuy(groupBuyRequest);
//
//        GroupBuyBoard updatedGroupBuy = groupBuyBoardMapper.findGroupBuyByBoardId(groupBuyRequest.getBoardId());
//        assertEquals("Updated Item", updatedGroupBuy.getGroupBuyItem());
//    }
//
//    @Test
//    public void testDeleteGroupBuy() {
//        groupBuyService.createGroupBuy(groupBuyRequest);
//
//        groupBuyService.deleteGroupBuy(groupBuyRequest.getBoardId());
//
//        GroupBuyBoard deletedGroupBuy = groupBuyBoardMapper.findGroupBuyByBoardId(groupBuyRequest.getBoardId());
//        assertNull(deletedGroupBuy);
//    }
//}