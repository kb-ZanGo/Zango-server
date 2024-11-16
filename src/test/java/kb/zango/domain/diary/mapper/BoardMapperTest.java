package kb.zango.domain.diary.mapper;

import kb.zango.domain.diary.board.entity.Board;
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
        user.setUserId(11L);
        board.setUser(user);

        board.setTitle("Test Title");
        board.setContent("Test Content");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = LocalDateTime.now().format(formatter);

        LocalDateTime formattedDate = LocalDateTime.parse(formattedNow, formatter);

        board.setRegiDate(formattedDate);

        // insertBoard 호출
        boardMapper.insertBoard(board);

        // 삽입 후 확인 로직 (DB에서 삽입된 게시글 조회)
        Board insertedBoard = boardMapper.findBoardById(board.getBoardId());
        assertNotNull(insertedBoard);
    }
}
