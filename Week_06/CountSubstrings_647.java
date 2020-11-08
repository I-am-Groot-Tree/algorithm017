public class CountSubstrings_647 {
    /**
     * 状态：dp[i][j] 表示字符串s在[i,j]区间的子串是否是一个回文串。
     * 状态转移方程：当 s[i] == s[j] && (j - i < 2 || dp[i + 1][j - 1]) 时，dp[i][j]=true，否则为false
     * 解题思路：
     * 1、当只有一个字符时，比如 a 自然是一个回文串。
     * 2、当有两个字符时，如果是相等的，比如 aa，也是一个回文串。
     * 3、当有三个及以上字符时，
     *    比如 ababa 这个字符记作串 1，把两边的 a 去掉，也就是 bab 记作串 2，可以看出只要串2是一个回文串，那么左右各多了一个 a 的串 1 必定也是回文串。
     *    所以当 s[i]==s[j] 时，自然要看 dp[i+1][j-1] 是不是一个回文串
     */
    public int countSubstrings(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int count = 0;
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    count++;
                }
            }
        }
        return count;
    }
}
