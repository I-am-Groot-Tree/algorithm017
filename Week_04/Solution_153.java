public class Solution_153 {
    /**
     * 解题思路：
     * 二分查找找交换点
     */
    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int l = 0, r = nums.length - 1;
        if (nums[r] > nums[0]) {
            return nums[0];
        }
        while (l <= r) {
            //防止超过int的范围
            int mid = l + (r - l) / 2;
            //因为是升序数组所以mid>mid+1是交换点
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            //因为是升序数组所以mid-1>mid是交换点
            if (nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }
            if (nums[mid] >= nums[0]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }
}
