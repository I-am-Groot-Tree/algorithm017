import java.util.HashMap;
import java.util.Map;

public class Solution_105 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    Map<Integer, Integer> maps = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int length = inorder.length;
        //利用哈希表可以快速找到中序遍历中根节点位置
        for (int i = 0; i < inorder.length; i++) {
            maps.put(inorder[i], i);
        }
        return build(preorder, inorder, 0, length - 1, 0, length - 1);
    }

    public TreeNode build(int[] preOrder, int[] inOrder, int pre_left, int pre_right, int in_left, int in_right) {
        if (pre_left > pre_right) {
            return null;
        }
        //找到中序遍历中根节点的位置（前序遍历中第一个元素就是根节点）
        int in_rootIndex = maps.get(preOrder[pre_left]);
        //计算出左子树中节点的个数
        int left_tree_length = in_rootIndex - in_left;
        //先构造出根节点
        TreeNode root = new TreeNode(preOrder[pre_left]);
        //递归构造左子树
        //先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = build(preOrder, inOrder, pre_left + 1, pre_left + left_tree_length, in_left, in_rootIndex - 1);
        //递归构造右子树
        //先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = build(preOrder, inOrder, pre_left + 1 + left_tree_length, pre_right, in_rootIndex + 1, in_right);
        return root;
    }
}
