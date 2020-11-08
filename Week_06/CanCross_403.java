public class CanCross_403 {
    /**
     * 解题思路：
     * 状态dp[i][k] 表示能否由前面的某一个石头 j 通过跳 k 步到达当前这个石头 i
     * k 步是 i 石头和 j 石头之间的距离
     * 对于第 i 个石头来说，上一个石头的步数就必须是这个 k - 1 || k || k + 1
     * 状态转移方程：dp[i][k] = dp[j][k - 1] || dp[j][k] || dp[j][k + 1]
     */
    public boolean canCross(int[] stones) {
        //第一步只能跳1个单位
        if (stones[1] != 1) {
            return false;
        }
        int len = stones.length;
        boolean[][] dp = new boolean[len][len + 1];
        dp[0][0] = true;
        for (int i = 1; i < len; i++) {
            //遍历 i 前面的石头
            for (int j = 0; j < i; j++) {
                int k = stones[i] - stones[j];
                //因为第i个石子最大只能跳i+1步，所以第j个石子最大只能通过i步跳过来
                if (k <= i) {
                    dp[i][k] = dp[j][k] || dp[j][k + 1] || dp[j][k - 1];
                    if (i == len - 1 && dp[i][k]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
