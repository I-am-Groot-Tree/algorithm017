import java.util.Arrays;
import java.util.Comparator;

public class Merge_56 {
    /**
     * 解题思路：
     * 我们将列表中的区间按照左端点升序排序。然后我们将第一个区间加入 merged 数组中，并按顺序依次考虑之后的每个区间：
     * 1、如果当前区间的左端点在数组 merged 中最后一个区间的右端点之后，那么它们不会重合，我们可以直接将这个区间加入数组 merged 的末尾；
     * 2、否则，它们重合，我们需要用当前区间的右端点更新数组 merged 中最后一个区间的右端点，将其置为二者的较大值。
     */
    public int[][] merge(int[][] intervals) {
        //按区间左端点排序
        Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));
        int[][] res = new int[intervals.length][2];
        int index = -1;
        for (int[] interval : intervals) {
            //如果结果集是空的，先把最小的加入
            //如果当前区间的左端点大于结果集中最后一个区间的右端点，证明不重合，直接加入结果集中
            if (index == -1 || interval[0] > res[index][1]) {
                res[++index] = interval;
            } else {
                //合并区间，右端点取更大的
                res[index][1] = Math.max(interval[1], res[index][1]);
            }
        }
        return Arrays.copyOf(res, index + 1);
    }
}
