package com.boardexample.boarddemo.controller;

import com.boardexample.boarddemo.repository.Board;
import com.boardexample.boarddemo.repository.UpdateBoardDto;
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
    public String board(Model model) {
        List<Board> boards = boardService.findAll();
        model.addAttribute("boards", boards);
        return "mainboard";
    }

    @GetMapping("/{boardId}")
    public String boards(@PathVariable Long boardId, Model model) {
        Board findBoard = boardService.findById(boardId).get();
        model.addAttribute("board", findBoard);
        return "board";
    }

    @GetMapping("/edit/{boardId}")
    public String editBoard(@PathVariable Long boardId, Model model) {
        Board board = boardService.findById(boardId).get();
        model.addAttribute("board", board);
        return "editboard";
    }

    @PostMapping("/edit/{boardId}")
    public String edit(@PathVariable Long boardId, @ModelAttribute UpdateBoardDto updateParam) {
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
}
