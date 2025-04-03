package com.test.maze.controller.service.impl;

import com.test.maze.controller.service.MazeService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MazeServiceImpl implements MazeService {
    private char[][] maze;
    private Random random;

    @Override
    public String generateMaze(int n, int m) {
        // Validate input: N and M must be odd and within constraints
        if (n < 5 || m < 5 || n > 101 || m > 101 || n % 2 == 0 || m % 2 == 0) {
            throw new IllegalArgumentException("N and M must be odd numbers between 5 and 101");
        }

        maze = new char[n][m];
        random = new Random();

        // Initialize maze with walls
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                maze[i][j] = '#';
            }
        }

        // Generate maze starting from (1,1)
        generateMazeWithDFS(1, 1);

        // Set entrance (S) and exit (E)
        maze[0][1] = 'S'; // Top-left entrance
        maze[n - 1][m - 2] = 'E'; // Bottom-right exit

        // Convert maze to string
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append(maze[i]).append("\n");
        }
        return result.toString();
    }

    private void generateMazeWithDFS(int row, int col) {
        maze[row][col] = '.'; // Mark current cell as path

        // Directions: up, right, down, left
        int[][] directions = {{-2, 0}, {0, 2}, {2, 0}, {0, -2}};
        shuffleArray(directions); // Randomize direction order

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            // Check if new position is within bounds and unvisited (wall)
            if (isValid(newRow, newCol) && maze[newRow][newCol] == '#') {
                maze[row + dir[0] / 2][col + dir[1] / 2] = '.';
                generateMazeWithDFS(newRow, newCol);
            }
        }
    }

    private boolean isValid(int row, int col) {
        return row > 0 && row < maze.length - 1 && col > 0 && col < maze[0].length - 1;
    }

    private void shuffleArray(int[][] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int[] temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}
