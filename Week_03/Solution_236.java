import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution_236 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 解题思路：
     * 若 root 是 p, q 的 最近公共祖先 ，则只可能为以下情况之一：
     * 1、p 和 q 在 root 的子树中，且分别在左、右子树中；
     * 2、p=root ，且 q 在 root 的左或右子树中；
     * 3、q=root ，且 p 在 root 的左或右子树中；
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //递归终止条件：如果当前节点为空或等于 p 或 q，则返回当前节点
        if (root == null || root == p || root == q) return root;
        //从根节点遍历，递归向左右子树查询节点信息
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //递归遍历左右子树，如果左右子树查到节点都不为空，则表明 p 和 q 分别在左右子树中，因此，当前节点即为最近公共祖先；
        if (left != null && right != null) return root;
        //如果左右子树其中一个不为空，则返回非空节点。
        return left != null ? left : right;
    }
}
