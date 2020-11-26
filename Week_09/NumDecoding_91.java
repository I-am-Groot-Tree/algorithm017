public class NumDecoding_91 {
    /**
     * 解题思路：
     * dp[i] 表示str[0...i]的解码总数
     * DP方程
     * 当 s[i]=0 时，不能单独解码，dp[i] = dp[i-2]
     * 当 s[i-1]=1 or s[i-1]=2 时，可单独解码也可联合解码，dp[i] = dp[i-1] + dp[i-2]。
     */
    public int numDecodings(String s) {
        char[] c = s.toCharArray();
        int pre = 1;
        //第一个数字如果是0无法破解
        int cur = c[0] == '0' ? 0 : 1;
        for (int i = 1; i < c.length; i++) {
            int tmp = cur;
            if (c[i] == '0') {
                //如果当前数字是0，只有前一个数字是1或2才能破解，不能单独破解
                if (c[i - 1] == '1' || c[i - 1] == '2') {
                    //只能两个数字一起破解
                    cur = pre;
                } else {
                    return 0;
                }
                //如果当前不是0,前一个数字只能是1或者21-26才能破解
            } else if (c[i - 1] == '1' || (c[i - 1] == '2' && c[i] >= '1' && c[i] <= '6')) {
                //单独破解+联合破解
                cur += pre;
            }
            pre = tmp;
        }
        return cur;
    }
}
