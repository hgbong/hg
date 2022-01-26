package com.example.hg.controller;

import com.example.hg.model.Board;
import com.example.hg.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;

    @GetMapping
    public List<Board> listBoards() {
        List<Board> xx = boardRepository.findByName("testname");

        return xx;
    }
}
