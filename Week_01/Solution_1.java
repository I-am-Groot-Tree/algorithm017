import java.util.HashMap;

//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
//
//
//
// 示例:
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
//
// Related Topics 数组 哈希表
// 👍 9219 👎 0
class Solution_1 {
    /**
     * 暴力法
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }

    /**
     * 哈希法
     * 解题思路：一种更有效的方法来检查数组中是否存在目标元素，哈希表正是为此目的而构建的，它支持以近似恒定的时间进行快速查找
     */
    public int[] twoSum_1(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>(2);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int s = target - nums[i];
            if (map.containsKey(s) && map.get(s) != i) {
                return new int[]{i, map.get(s)};
            }
        }
        return null;
    }


}
