import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution_46 {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        Deque<Integer> deque = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        dfs(nums, 0, deque, used);
        return result;
    }

    public void dfs(int[] nums, int depth, Deque<Integer> deque, boolean[] used) {
        //depth记录递归树的深度
        //递归的终止条件是：一个排列中的数字已经选够了
        if (depth == nums.length) {
            result.add(new ArrayList<>(deque));
            return;
        }
        //在当前层中还未被选择的数中选择一个元素作为下一个位置的元素
        for (int i = 0; i < nums.length; i++) {
            //used数组记录这个数有没有被选过
            if (!used[i]) {
                deque.addLast(nums[i]);
                used[i] = true;
                //去下一层找未被选择的元素
                dfs(nums, depth + 1, deque, used);
                //回溯:回到上一层的状态
                used[i] = false;
                deque.removeLast();
            }
        }
    }
}
