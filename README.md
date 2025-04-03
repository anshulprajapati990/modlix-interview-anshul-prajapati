# Pi Search and Maze Generator

A Spring Boot application solving two coding challenges:
1. **Peeking Inside Pi**: Search for numeric sequences in π digits and generate π dynamically.
2. **Maze Generator**: Create a random, solvable maze with a single path.

Developed with Java 17 and Spring Boot, showcasing RESTful APIs, algorithmic design, and precision arithmetic.

## Prerequisites
- **Java**: 17+
- **Maven**: For building and dependency management
- **Pi Digits Files** :
  - `pi_million.txt` (1M digits)
  - `pi_billion.txt` (1B digits)


## Setup Instructions
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/anshulprajapati990/modlix-interview-anshul-prajapati.git
   cd pi-search-maze
Add Pi Files (Optional):
Place pi_million.txt in src/main/resources/.
pi_billion.txt is fetched dynamically if not provided (due to size).
Build and Run:

mvn clean install
mvn spring-boot:run
Runs on http://localhost:8080.

**Problem 1: Peeking Inside Pi**

Problem Statement
Given a numeric string, find its first occurrence in the first 1 million or 1 billion digits of π. Return the 0-based index or -1 if not found. Bonus: Generate π digits.

Implementation
Search: Streams π digits from files or URLs using BufferedReader.
Generator: Uses Nilakantha series with BigDecimal for precision.
API Endpoints
Search 1M Digits:

GET /api/pi/search/million?sequence=14159
Output: 1 (index of "14159")
Search 1B Digits:

GET /api/pi/search/billion?sequence=14159
Output: 1 or -1
Generate Pi (Bonus):

GET /api/pi/generate?digits=10
Output: 3.1415926535
Constraints
Input: Numeric string of any length
Range: 1M or 1B digits
Example

Input: sequence=14159
Output (1M): 1


**Problem 2: Maze Generator**

Problem Statement
Generate an N × M maze with walls (#), paths (.), start (S), and exit (E). Ensure one solvable path from S to E.

Implementation
Algorithm: Recursive backtracking (DFS) for a perfect maze.
Layout: S at (0,1), E at (N-1, M-2), border of #, inner paths.
API Endpoint
Generate Maze:

GET /api/maze?n=7&m=7
Output:

#######
#S...##
#.###.#
#.#...#
#.###.#
#....E#
#######
Constraints
N, M: Odd, 5 ≤ N, M ≤ 101
Single solution via DFS
No external libraries
Example
Input: n=7, m=7
Output: (as above)
