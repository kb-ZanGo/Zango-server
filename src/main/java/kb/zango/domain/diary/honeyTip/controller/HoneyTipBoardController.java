package kb.zango.domain.diary.honeyTip.controller;

import kb.zango.domain.diary.honeyTip.dto.HoneyTipBoardRequest;
import kb.zango.domain.diary.honeyTip.dto.HoneyTipBoardResponse;
import kb.zango.domain.diary.honeyTip.service.HoneyTipBoardService;
import kb.zango.domain.diary.honeyTip.entity.HoneyTipBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/honeytip")
@RequiredArgsConstructor
public class HoneyTipBoardController {

    private final HoneyTipBoardService honeyTipBoardService;

    @PostMapping
    public ResponseEntity<String> createHoneyTipBoard(@RequestBody HoneyTipBoardRequest request) {
        try {
            honeyTipBoardService.insertHoneyTipBoard(request);
            return ResponseEntity.ok("꿀팁 등록 성공");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("꿀팁 등록 실패");
        }
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<String> updateHoneyTipBoard(@PathVariable Long boardId, @RequestBody HoneyTipBoardRequest request) {
        try {
            request.setBoardId(boardId);
            honeyTipBoardService.updateHoneyTipBoard(request);
            return ResponseEntity.ok("꿀팁 수정 성공");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("꿀팁 수정 실패");
        }
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<HoneyTipBoardResponse> getHoneyTipBoard(@PathVariable Long boardId) {
        try {
            HoneyTipBoardResponse response = honeyTipBoardService.getHoneyTipBoard(boardId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<HoneyTipBoardResponse>> getAllHoneyTipBoards() {
        try {
            List<HoneyTipBoardResponse> responseList = honeyTipBoardService.getAllHoneyTipBoards();
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> deleteHoneyTipBoard(@PathVariable Long boardId) {
        try {
            honeyTipBoardService.deleteHoneyTipBoard(boardId);
            return ResponseEntity.ok("꿀팁 삭제 성공");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("꿀팁 삭제 실패");
        }
    }
}
