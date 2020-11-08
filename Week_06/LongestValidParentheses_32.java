public class LongestValidParentheses_32 {
    /**
     * 解题思路：
     * 1、判断1-dp[i-1]-1是否为"(",若是则基础长度为2
     * 2、内部最长有效括号为dp[i-1]
     * 3、外部链接在一起的最长有效括号为dp[i-dp[i-1]-2]
     */
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        int max = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    //注意边界条件
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    //注意边界条件
                    dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0);
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }
}
