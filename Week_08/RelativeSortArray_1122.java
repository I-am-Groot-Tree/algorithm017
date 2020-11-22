public class RelativeSortArray_1122 {
    /**
     * 解题思路：
     * 1、利用数组统计arr1中元素出现的个数，nums[i]即为元素i出现的个数
     * 2、遍历arr2，将arr2中出现的元素按顺序插入
     * 3、遍历剩余数组，将元素升序输出(下标本身就是升序的)
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length];
        int[] nums = new int[1001];
        //遍历arr1，统计元素个数
        for (int i : arr1) {
            nums[i]++;
        }
        //遍历arr2，处理arr1中已出现的元素
        int index = 0;
        for (int i : arr2) {
            while (nums[i] > 0) {
                result[index++] = i;
                nums[i]--;
            }
        }
        //遍历剩余元素，处理arr2中未出现的元素
        //由于数组下标就是有序的,所以不需要再排序
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0) {
                result[index++] = i;
                nums[i]--;
            }
        }
        return result;
    }
}
