import java.util.HashMap;
import java.util.Map;

public class Solution_122 {
    /**
     * 解题思路：
     * 贪心算法
     * 解题思路：只要第二天价格比前一天高就交易，利润最大化
     */
    public int maxProfit(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                result += prices[i] - prices[i - 1];
            }
        }
        return result;
    }
}
