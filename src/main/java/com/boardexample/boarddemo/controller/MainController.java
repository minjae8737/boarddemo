package com.boardexample.boarddemo.controller;

import com.boardexample.boarddemo.domain.Board;
import com.boardexample.boarddemo.domain.BoardSearchDto;
import com.boardexample.boarddemo.domain.UpdateBoardDto;
import com.boardexample.boarddemo.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class MainController {

    private final BoardService boardService;

    @GetMapping
    public String board(@ModelAttribute(name = "boardSearchDto") BoardSearchDto boardSearchDto,
                        @RequestParam(name = "page", defaultValue = "0") long page,
                        Model model) {
        int boardSize = 5; // 한 페이지에 보여줄 board 개수

        log.info("---start board---");
        log.info("boardSearchDto searchWord ={}, type={}", boardSearchDto.getSearchWord(), boardSearchDto.getSearchType());

        List<Board> boards = boardService.findBoards(boardSearchDto);

        int boardCount = boards.size();  // board 총 개수
        //totalPages : 페이지 총 개수
        int totalPages = ((float) boardCount % (float) boardSize == 0f) ? boardCount / boardSize : boardCount / boardSize + 1;
        //endBoardIndex : 마지막 페이지에서 board개수가 boardSize보다 작으면 boardCount 같으면 (int) (page + 1) * boardSize
        int endBoardIndex = Math.min(boardCount, (int) (page + 1) * boardSize);

        boards = boards.subList((int) page * boardSize, endBoardIndex);  //현재 page에 표시할 board 리스트

        log.info("boardCount={} totalPages={}", boardCount, totalPages);
        log.info("(float) boardCount / (float) boardSize == 0.0 = {}}", (float) boardCount % (float) boardSize);

        model.addAttribute("boards", boards);
        model.addAttribute("page", page);
        model.addAttribute("totalPages", totalPages);

        return "mainboard";
    }

    @GetMapping("/{boardId}")
    public String boards(@PathVariable(name = "boardId") long boardId, Model model) {
        log.info("start boards");
        Board findBoard = boardService.findById(boardId).get();
        log.info("end boards");
        model.addAttribute("board", findBoard);
        return "board";
    }

    @GetMapping("/edit/{boardId}")
    public String editBoard(@PathVariable(name = "boardId") long boardId, Model model) {
        log.info("start editBoard()");
        Board board = boardService.findById(boardId).get();
        log.info("board id={}, board title={}, board content={}", board.getId(), board.getTitle(), board.getContent());
        model.addAttribute("board", board);
        return "editboard";
    }

    @PostMapping("/edit/{boardId}")
    public String edit(@PathVariable(name = "boardId") long boardId, @ModelAttribute UpdateBoardDto updateParam) {
        log.info("updateParam title={}, content={}", updateParam.getTitle(), updateParam.getContent());
        boardService.update(boardId, updateParam);
        return "redirect:/board/{boardId}";
    }

    @GetMapping("/write")
    public String writeBoard() {
        return "writeboard";
    }

    @PostMapping("/write")
    public String write(@ModelAttribute Board board, RedirectAttributes redirectAttributes) {
        Board savedBoard = boardService.save(board);
        redirectAttributes.addAttribute("boardId", savedBoard.getId());
        return "redirect:/board/{boardId}";
    }

    @PostMapping("/delete")
    public String deleteBoard(@RequestParam(name = "boardId") long boardId) {
        boardService.deleteById(boardId);
        return "redirect:/board";
    }
}
