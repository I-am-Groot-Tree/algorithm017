import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolveNQueens_51 {
    /**
     * 解题思路：回溯
     * 1、先填满棋盘
     * 2、从上往下递归遍历每个位置是否可以放皇后
     * 3、终止条件为遍历完整个棋盘
     * 4、因为是从上往下遍历，所以只需要看上面、左上角、右上角是否有皇后即可
     */
    List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        if (n == 0) {
            return null;
        }
        //初始化棋盘
        char[][] board = new char[n][n];
        for (char[] c : board) {
            Arrays.fill(c, '.');
        }
        dfs(board, 0);
        return result;
    }

    /**
     * 路径：board 中小于 row 的那些行都已经成功放置了皇后
     * 选择列表：第 row 行的所有列都是放置皇后的选择
     * 结束条件：row 超过 board 的最后一行
     */
    private void dfs(char[][] board, int row) {
        //终止条件：遍历到最后一行
        if (row == board.length) {
            result.add(charToString(board));
            return;
        }
        int col = board[0].length;
        //遍历每一列
        for (int i = 0; i < col; i++) {
            //判断是否可以放皇后
            if (!valid(board, row, i)) {
                continue;
            }
            //尝试放入皇后
            board[row][i] = 'Q';
            //下探下一层，行数+1
            dfs(board, row + 1);
            //失败撤回选择
            board[row][i] = '.';
        }
    }

    public List<String> charToString(char[][] chars) {
        List<String> list = new ArrayList<>();
        for (char[] c : chars) {
            list.add(String.valueOf(c));
        }
        return list;
    }

    private boolean valid(char[][] board, int row, int col) {
        int n = board.length;
        //判断当前列有没有皇后
        for (int i = 0; i < n; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        //判断左上角有没有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        //判断右上角有没有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
}
