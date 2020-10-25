public class Solution_45 {
    /**
     * 解题思路：
     * 1、在本次可到达最大范围内搜索是否可直接到达终点
     * 2、若无法直接到达终点，则在落脚点再次进行搜索则是最小次数
     */
    public int jump(int[] nums) {
        if (nums.length == 1) return 0;
        int reach = 0;
        int nextreach = nums[0];
        int step = 0;
        for (int i = 0; i < nums.length; i++) {
            nextreach = Math.max(i + nums[i], nextreach);
            if (nextreach >= nums.length - 1) return (step + 1);
            if (i == reach) {
                step++;
                reach = nextreach;
            }
        }
        return step;
    }
}
