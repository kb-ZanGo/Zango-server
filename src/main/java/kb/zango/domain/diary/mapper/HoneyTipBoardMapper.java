package kb.zango.domain.diary.mapper;

import java.util.List;
import kb.zango.domain.diary.honeyTip.entity.HoneyTipBoard;
import org.apache.ibatis.annotations.Mapper;

// 안씀

@Mapper
public interface HoneyTipBoardMapper {

    void insertHoneyTipBoard(HoneyTipBoard honeyTipBoard);

    HoneyTipBoard getHoneyTipBoard(Long id);

    void updateHoneyTipBoard(HoneyTipBoard honeyTipBoard);

    void deleteHoneyTipBoard(Long id);

    List<HoneyTipBoard> getAllHoneyTipBoards();

}