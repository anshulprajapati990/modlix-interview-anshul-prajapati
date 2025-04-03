package com.test.maze.controller;

import com.test.maze.controller.service.MazeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MazeController {
    @Autowired
    private MazeService mazeService;

    @GetMapping("/api/maze")
    public String generateMaze(@RequestParam int n, @RequestParam int m) {
        return mazeService.generateMaze(n, m);
    }
}
