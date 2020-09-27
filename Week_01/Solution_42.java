//给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
//
// 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
//
//
//
// 示例 1:
//
// 给定数组 nums = [1,1,2],
//
//函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
//
//你不需要考虑数组中超出新长度后面的元素。
//
// 示例 2:
//
// 给定 nums = [0,0,1,1,1,2,2,3,3,4],
//
//函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
//
//你不需要考虑数组中超出新长度后面的元素。
//
//
//
//
// 说明:
//
// 为什么返回数值是整数，但输出的答案是数组呢?
//
// 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
//
// 你可以想象内部操作如下:
//
// // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
//int len = removeDuplicates(nums);
//
//// 在函数里修改输入数组对于调用者是可见的。
//// 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
//for (int i = 0; i < len; i++) {
//    print(nums[i]);
//}
//
// Related Topics 数组 双指针
// 👍 1652 👎 0

class Solution_42 {
    /**
     * 解题思路：
     * 先找到最高的柱子的下标，把“木桶”分为两部分。分别遍历“左木桶”和“右木桶”找到两边接的雨水。
     *
     * 1、两指针一开始在同一位置，a遍历左半部分桶。
     * 2、如果a的高度小于b的高度，则能接水，water = water + (b的高度 - a的高度)
     * 3、如果a的高度大于b的高度，说明他们之间没有凹槽（有也已经计算进water了），则b=a
     * 4、循环结束，左半部分的水就已经计算完成了
     * 5、右半部分的遍历，和左半部分几乎一样，就是倒序遍历而已，也是让a指针先走。
     *
     */
    public int trap(int[] height) {
        if (height.length < 2) {
            return 0;
        }
        //找出最高的一根柱子
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > max) {
                max = height[i];
                maxIndex = i;
            }
        }
        int water = 0;
        //求左边木桶所接雨水
        int b = 0;
        for (int a = 0; a <= maxIndex; a++) {
            if (height[a] < height[b]) {
                water = water + height[b] - height[a];
            } else {
                b = a;
            }
        }

        //求右边木桶所接雨水
        b = height.length - 1;
        for (int a = height.length - 1; a >= maxIndex; a--) {
            if (height[a] < height[b]) {
                water = water + height[b] - height[a];
            } else {
                b = a;
            }
        }

        return water;
    }
}
