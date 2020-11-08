public class MaximalSquare_221 {
    /**
     * 解题思路：
     *  dp(i,j) 表示以 (i,j) 为右下角，且只包含 1 的正方形的边长最大值
     *  DP方程：
     *  dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int maxSide = 0;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //只有右下角等于1才计算最大边长
                if (matrix[i][j] == '1') {
                    //边界都为1
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        //右下角最大边长取决于左边、上边、左上的最小值
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }
}
