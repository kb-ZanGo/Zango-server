package kb.zango.domain.diary.mapper;

import kb.zango.domain.diary.board.entity.Board;
import kb.zango.domain.diary.honeyTip.entity.BigCategory;
import kb.zango.domain.diary.honeyTip.entity.SmallCategory;
import kb.zango.domain.users.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BoardMapperTest {

    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void testInsertBoard() {
        Board board = new Board();

        User user = new User();
        user.setUserId(1L);
        board.setUser(user);

        board.setTitle("Test Title");
        board.setContent("Test Content");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = LocalDateTime.now().format(formatter);

        LocalDateTime formattedDate = LocalDateTime.parse(formattedNow, formatter);

        board.setRegiDate(formattedDate);

        SmallCategory smallCategory = new SmallCategory();
        smallCategory.setScId(2L);
        board.setSmallCategory(smallCategory);

        boardMapper.insertBoard(board);

        Board insertedBoard = boardMapper.findBoardById(board.getBoardId());
        assertNotNull(insertedBoard);
        assertEquals(board.getBoardId(), insertedBoard.getBoardId());
    }
}
