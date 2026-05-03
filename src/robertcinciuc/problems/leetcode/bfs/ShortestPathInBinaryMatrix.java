package robertcinciuc.problems.leetcode.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {

    public static class Pos{
        public final int i;
        public final int j;

        public Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        Queue<Pos> queue = new ArrayDeque<>();

        if(grid[0][0] == 1){
            return -1;
        }

        grid[0][0] = -1;
        queue.add(new Pos(0, 0));
        while (queue.size() > 0){
            Pos current = queue.remove();
            checkNeighbors(current.i, current.j, grid, queue);
        }

        int n = grid.length;
        if(grid[n - 1][n - 1] == 0 || grid[n - 1][n - 1] == 1){
            return -1;
        }
        return -1 * grid[n -1][n - 1];
    }

    private void checkNeighbors(int i, int j, int[][] grid, Queue<Pos> queue){
        if(i > 0 && j > 0 && grid[i - 1][j - 1] == 0){
            queue.add(new Pos(i - 1, j - 1));
            grid[i - 1][j - 1] = grid[i][j] - 1;
        }
        if(i > 0 && grid[i - 1][j] == 0){
            queue.add(new Pos(i - 1, j));
            grid[i - 1][j] = grid[i][j] - 1;
        }
        if(i > 0 && j < grid.length - 1 && grid[i - 1][j + 1] == 0){
            queue.add(new Pos(i - 1, j + 1));
            grid[i - 1][j + 1] = grid[i][j] - 1;
        }

        if(j > 0 && grid[i][j - 1] == 0){
            queue.add(new Pos(i, j - 1));
            grid[i][j - 1] = grid[i][j] - 1;
        }
        if(j < grid.length - 1 && grid[i][j + 1] == 0){
            queue.add(new Pos(i, j + 1));
            grid[i][j + 1] = grid[i][j] - 1;
        }

        if(i < grid.length - 1 && j > 0 && grid[i + 1][j - 1] == 0){
            queue.add(new Pos(i + 1, j - 1));
            grid[i + 1][j - 1] = grid[i][j] - 1;
        }
        if(i < grid.length - 1 && grid[i + 1][j] == 0){
            queue.add(new Pos(i + 1, j));
            grid[i + 1][j] = grid[i][j] - 1;
        }
        if(i < grid.length - 1 && j < grid.length - 1 && grid[i + 1][j + 1] == 0){
            queue.add(new Pos(i + 1, j + 1));
            grid[i + 1][j + 1] = grid[i][j] - 1;
        }
    }

    public static void main(String[] args){
        ShortestPathInBinaryMatrix sol = new ShortestPathInBinaryMatrix();
        System.out.println(sol.shortestPathBinaryMatrix(new int[][]{{0, 0, 0}, {1, 1, 0}, {1, 1, 0}}));
        System.out.println(sol.shortestPathBinaryMatrix(new int[][]{{1, 0, 0}, {1, 1, 0}, {1, 1, 0}}));
    }
}
