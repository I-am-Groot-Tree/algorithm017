public class Solution_55 {
    /**
     * 解题思路：
     * 贪心算法
     * 遍历每个位置，计算所能到达的最大距离，如果最大距离大于等于末尾位置则能到达
     */
    public boolean canJump(int[] nums) {
        int maxJump = 0;
        for (int i = 0; i < nums.length; i++) {
            //要先判断当前位置是否可以到达
            if (i <= maxJump) {
                //计算基于当前位置的最大距离
                maxJump = Math.max(maxJump, i + nums[i]);
                if (maxJump >= nums.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
