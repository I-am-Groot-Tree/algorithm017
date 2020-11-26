import java.util.Arrays;

public class IsMatch_44 {
    /**
     * 解题思路：
     * DP状态定义：dp[i][j] 表示字符串 s 的前 i 个字符和模式 p 的前 j 个字符是否能匹配
     * 动态转移方程：
     * 1、s[i]​ 与 p[j]​ 相同或者 p[j]​ 是问号:dp[i][j] = dp[i - 1][j - 1]
     * 2、p[j]​ 是星号（是否使用*）：dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
     * 初始化：
     * 1、dp[0][0]=True，即当字符串 s 和模式 p 均为空时，匹配成功；
     * 2、dp[i][0] = False，即空模式无法匹配非空字符串；
     * 3、dp[0][j]需要分情况讨论：因为星号才能匹配空字符串，所以只有当模式 p 的前 j 个字符均为星号时，dp[0][j] 才为真。
     */
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        //第0个字符代表两个为空串
        boolean[][] dp = new boolean[m + 1][n + 1];
        //两个空串可以完全匹配
        dp[0][0] = true;
        //当s为空串时,p必须都是*才能匹配
        for (int i = 1; i <= n; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //只有当s[i]==p[j]相等或p[j]为?时，要看前一位是否匹配
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    //当p[j]为*时，要看是否使用*
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}
