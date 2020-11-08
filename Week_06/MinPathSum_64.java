public class MinPathSum_64 {
    /**
     * 解题思路：
     * dp[i][j] 表示从左上角出发到 (i,j) 位置的最小路径和
     * DP方程
     * 当 i>0 且 j=0 时，dp[i][0]=dp[i-1][0]+grid[i][0]
     * 当 i=0 且 j>时，dp[0][j]=dp[0][j−1]+grid[0][j]。
     * 当 i>0且 j>0时，dp[i][j]=min(dp[i−1][j],dp[i][j−1])+grid[i][j]。
     */
    public int minPathSum(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {
                    //左边界的最优解是：上边格子的最优解+当前格子的数字
                    grid[i][j] = grid[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    //右边界的最优解是：左边格子的最优解+当前格子的数字
                    grid[i][j] = grid[i - 1][j] + grid[i][j];
                } else {
                    //不在边界处的最优解是：左边格子最优解和上边格子最优解的最小值+当前格子的数字
                    grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
                }
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }
}
