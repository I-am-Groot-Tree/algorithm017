import java.util.Arrays;

public class LengthOfLIS_300 {
    /**
     * 解题思路：
     * 状态定义：dp[i] 的值代表 nums 前 i 个数字的最长子序列长度。
     * 转移方程：当 nums[i]>nums[j] 时: dp[i] = max(dp[i], dp[j] + 1) for j in [0, i)。
     * 初始状态：dp[i] 所有元素置 1，含义是每个元素都至少可以单独成为子序列，此时长度都为 1。
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int result = 1;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}
