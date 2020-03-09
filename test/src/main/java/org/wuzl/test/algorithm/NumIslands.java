package org.wuzl.test.algorithm;

public class NumIslands {
    public static void main(String[] args) {
        NumIslands obj = new NumIslands();
        // System.out.println(obj.numIslands(new char[][] { //
        // { '1', '1', '1', '1', '0' }, //
        // { '1', '1', '0', '1', '0' }, //
        // { '1', '1', '0', '0', '0' }, //
        // { '0', '0', '0', '0', '0' } }));
        // System.out.println(obj.numIslands(new char[][] { //
        // { '1', '1', '0', '0', '0' }, //
        // { '1', '1', '0', '0', '0' }, //
        // { '0', '0', '1', '0', '0' }, //
        // { '0', '0', '0', '1', '1' } }));
        // System.out.println(obj.numIslands(new char[][] { //
        // { '1', '0', '1', '1', '0', '1', '1' } }));
        // System.out.println(obj.numIslands(new char[][] { //
        // { '1', '1', '1', '1', '0' }, //
        // { '1', '1', '0', '1', '0' }, //
        // { '1', '1', '0', '0', '0' }, //
        // { '0', '0', '0', '0', '0' } }));
        System.out.println(obj.numIslands(new char[][] { //
                { '1', '1', '1' }, //
                { '0', '1', '0' }, //
                { '1', '1', '1' } }));
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int count = 0;
        int row = grid.length;
        int column = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int row, int column) {
        int rowCount = grid.length;
        int columnCount = grid[0].length;
        grid[row][column] = 0;
        if (row - 1 >= 0 && grid[row - 1][column] == '1') {
            dfs(grid, row - 1, column);
        }
        if (row + 1 < rowCount && grid[row + 1][column] == '1') {
            dfs(grid, row + 1, column);
        }
        if (column - 1 >= 0 && grid[row][column - 1] == '1') {
            dfs(grid, row, column - 1);
        }
        if (column + 1 < columnCount && grid[row][column + 1] == '1') {
            dfs(grid, row, column + 1);
        }
    }
}
