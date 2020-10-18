import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution_47 {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        Deque<Integer> deque = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(nums, 0, deque, used);
        return result;
    }

    /**
     * 解题思路：为了便于寻找重复元素，需要先排序
     * 剪枝条件1：用过的元素不能再使用之外，又添加了一个新的剪枝条件，也就是我们考虑重复部分思考的结果
     * 剪枝条件2：当当前元素和前一个元素值相同（此处隐含这个元素的 index>0 ），并且前一个元素还没有被使用过的时候，我们要剪枝
     */
    public void dfs(int[] nums, int depth, Deque<Integer> deque, boolean[] used) {
        if (depth == nums.length) {
            List<Integer> list = new ArrayList<>(deque);
            result.add(list);
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            deque.addLast(nums[i]);
            used[i] = true;
            dfs(nums, depth + 1, deque, used);
            used[i] = false;
            deque.removeLast();
        }
    }
}
