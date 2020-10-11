import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution_347 {
    /**
     * 最小堆
     * 解题思路：
     * 1、算出数组中各个元素出现次数，映射放入哈希表中
     * 2、维护一个元素数目为 k 的最小堆
     * 3、每次都将新的元素与堆顶元素（堆中频率最小的元素）进行比较
     * 4、如果新的元素的频率比堆顶端的元素大，则弹出堆顶端的元素，将新的元素添加进堆中
     * 5、最终，堆中的 k 个元素即为前 k 个高频元素
     *
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> maps = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (maps.containsKey(nums[i])) {
                int count = maps.get(nums[i]);
                maps.put(nums[i], ++count);
            } else {
                maps.put(nums[i], 1);
            }
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(maps::get));
        for (int i : maps.keySet()) {
            if (queue.size() < k) {
                queue.add(i);
            } else if (maps.get(i) > maps.get(queue.peek())) {
                queue.poll();
                queue.add(i);
            }
        }
        int[] result = new int[k];
        for (int i = 0; i < k && !queue.isEmpty(); i++) {
            result[i] = queue.poll();
        }
        return result;
    }
}
