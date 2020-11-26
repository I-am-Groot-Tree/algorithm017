import java.util.Arrays;

public class LongestPalindrome_5 {
    /**
     * 解题思路：
     * 定义状态：dp[i][j] 表示子串 s[i..j] 是否为回文子串，这里子串 s[i..j] 定义为左闭右闭区间，可以取到 s[i] 和 s[j]。
     * 状态转移方程：dp[i][j] = (s[i] == s[j]) and dp[i + 1][j - 1]
     * 如果第i和j个字符相等：
     * 1、3个字符及以下肯定是回文子串。a、ab、aba
     * 2、去掉头尾两边依然是回文子串
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        //单个字符就是回文子串
        if (len < 2) {
            return s;
        }
        int begin = 0;
        int maxLen = 1;
        boolean[][] dp = new boolean[len][len];
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                //如果第i和j个字符相等：
                //1、3个字符及以下肯定是回文子串
                //2、去掉两边依然是回文子串
                if (s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                }
                //记录最大回文子串长度
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}
