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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

class Solution_589 {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
    /**
     * 递归：根，左，右
     *
     */
    List<Integer> list = new ArrayList<>();

    public List<Integer> preorder(Node root) {
        if (root == null) {
            return list;
        }
        //前序遍历：根、左、右
        list.add(root.val);
        for (Node child : root.children) {
            preorder(child);
        }
        return list;
    }

    /**
     * 迭代法
     * 解题思路：我们使用一个栈来帮助我们得到前序遍历，需要保证栈顶的节点就是我们当前遍历到的节点。
     * 1、我们首先把根节点入栈，因为根节点是前序遍历中的第一个节点。
     * 2、随后每次我们从栈顶取出一个节点，它是我们当前遍历到的节点
     * 3、并把所有子节点逆序推入栈中。例如 u 的子节点从左到右为 v1, v2, v3，那么推入栈的顺序应当为 v3, v2, v1，这样就保证了下一个遍历到的节点（即 u 的第一个子节点 v1）出现在栈顶的位置。
     */
    public List<Integer> preorder_01(Node root) {
        List<Integer> result = new ArrayList<>();
        LinkedList<Node> stack = new LinkedList<>();
        if (root == null) {
            return result;
        }
        //先压入根节点
        stack.add(root);

        while (!stack.isEmpty()) {
            Node first = stack.pollLast();
            result.add(Objects.requireNonNull(first).val);
            //将子节点倒序入栈
            Collections.reverse(root.children);
            stack.addAll(root.children);
        }
        return result;
    }
}
