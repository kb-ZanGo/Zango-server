package kb.zango.domain.diary.mapper;

import kb.zango.domain.diary.board.entity.Board;
import kb.zango.domain.users.entity.User;
import kb.zango.domain.diary.honeyTip.entity.SmallCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BoardMapperTest {

    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void testInsertBoard() {
        Board board = new Board();
        board.setTitle("Test Title");
        board.setContent("Test Content");
        // 기타 필드 설정

        // insertBoard 호출
        boardMapper.insertBoard(board);

        // 삽입 후 확인 로직 (예: DB에서 삽입된 게시글 조회)
        Board insertedBoard = boardMapper.findBoardById(board.getBoardId());
        assertNotNull(insertedBoard);
    }

//    @Test
//    @Transactional // 테스트가 끝난 후 롤백하여 DB를 원래 상태로 복원
//    public void testInsertBoard() {
//        // User 객체 생성 (테스트용 사용자)
//        User user = new User();
//        user.setUserId(1L); // 실제 DB에서 사용될 ID를 지정합니다.
//
//        // SmallCategory 객체 생성 (테스트용 카테고리)
//        SmallCategory smallCategory = new SmallCategory();
//        smallCategory.setScId(1L); // 실제 DB에서 사용될 카테고리 ID를 지정합니다.
//
//        // Board 객체 생성
//        Board board = new Board();
//        board.setBoardId(null); // boardId는 Auto Increment로 설정되어 있어 null로 두면 자동 생성됩니다.
//        board.setUser(user);
//        board.setSmallCategory(smallCategory);
//        board.setBoard_type(1); // 기본 값 사용
//        board.setTitle("Test Title");
//        board.setContent("This is a test content.");
//        board.setLikeCnt(0);
//        board.setCommentCnt(0);
//        board.setView_cnt(0);
//        board.setRegiDate(LocalDateTime.now()); // 현재 시간으로 설정
//
//        // 게시글 삽입
//        boardMapper.insertBoard(board);
//
//        // 삽입된 게시글을 DB에서 조회하여 검증
//        Board insertedBoard = boardMapper.findBoardById(board.getBoardId());
//
//        // 결과 검증
//        assertNotNull(insertedBoard);
//        assertEquals("Test Title", insertedBoard.getTitle());
//        assertEquals("This is a test content.", insertedBoard.getContent());
//        assertEquals(0, insertedBoard.getLikeCnt());
//        assertEquals(0, insertedBoard.getCommentCnt());
//        assertEquals(0, insertedBoard.getView_cnt());
//        assertNotNull(insertedBoard.getRegiDate()); // regiDate가 null이 아님을 확인
//    }
}
