package com.example.hg;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/boards")
public class BoardController {

    @GetMapping(value = "")
    public String listBoards() {
        return "test";
    }
}
