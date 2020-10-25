import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solution_860 {
    /**
     * 解题思路：
     * 贪心算法
     * 模拟场景，考虑每种情况应如何找零
     * 先找面额大的零钱
     */
    public boolean lemonadeChange(int[] bills) {
        Map<Integer, Integer> maps = new HashMap<>();
        int five = 0, ten = 0;
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                ++five;
            }
            if (bills[i] == 10) {
                ++ten;
                if (five > 0) {
                    five--;
                } else {
                    return false;
                }
            }
            if (bills[i] == 20) {
                if (ten > 0 && five > 0) {
                    ten--;
                    five--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
