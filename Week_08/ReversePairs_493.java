import java.util.Arrays;

public class ReversePairs_493 {
    /**
     * 解题思路：归并排序
     */
    int result = 0;
    public int reversePairs(int[] nums) {
        mergeSort(nums,0,nums.length-1);
        return result;
    }
    public void mergeSort(int[] array, int left, int right) {
        if (right <= left) {
            return;
        }
        int mid = (left + right) >> 1;
        //左边数组单独排序
        mergeSort(array, left, mid);
        //右边数组单独排序
        mergeSort(array, mid + 1, right);
        int count = 0;
        int i = left, j = mid + 1;
        while (i <= mid) {
            if (j <= right && (long)array[i] > (long)2*array[j]) {
                j++;
                count++;
            } else {
                i++;
                result += count;
            }
        }
        Arrays.sort(array, left, right + 1);
    }
}
