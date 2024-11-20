package kb.zango.domain.diary.honeyTip.controller;

import kb.zango.domain.diary.honeyTip.entity.GroupBuyBoard;
import kb.zango.domain.diary.honeyTip.service.GroupBuyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groupbuy")
@RequiredArgsConstructor
public class GroupBuyBoardController {

    private final GroupBuyService groupBuyService;

    @PostMapping
    public ResponseEntity<String> createGroupBuy(@RequestBody GroupBuyBoard groupBuy) {
        try {
            groupBuyService.createGroupBuy(groupBuy);
            return ResponseEntity.ok("공구 등록 성공");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("공구 등록 실패");
        }
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<GroupBuyBoard> getGroupBuyByBoardId(@PathVariable Long boardId) {
        try {
            GroupBuyBoard groupBuy = groupBuyService.getGroupBuyByBoardId(boardId);
            if (groupBuy != null) {
                return ResponseEntity.ok(groupBuy);
            } else {
                return ResponseEntity.status(404).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping("/{groupBuyId}")
    public ResponseEntity<String> updateGroupBuy(@PathVariable Long groupBuyId, @RequestBody GroupBuyBoard groupBuy) {
        try {
            groupBuy.setGroupBuyId(groupBuyId);
            groupBuyService.updateGroupBuy(groupBuy);
            return ResponseEntity.ok("공구 수정 성공");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("공구 수정 실패");
        }
    }

    @DeleteMapping("/{groupBuyId}")
    public ResponseEntity<String> deleteGroupBuy(@PathVariable Long groupBuyId) {
        try {
            groupBuyService.deleteGroupBuy(groupBuyId);
            return ResponseEntity.ok("공구 삭제 성공");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("공구 삭제 실패");
        }
    }
}
