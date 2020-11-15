public class FindCircleNum_547 {
    /**
     * 并查集
     * 解题思路：
     * 1、记住并查集的应用场景
     * 2、熟练写出并查集模板
     * 3、互为朋友时连接在一起
     */
    public int findCircleNum(int[][] M) {
        int m = M.length;
        UnionFind unionFind = new UnionFind(m);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < i; j++) {
                if (M[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.count();
    }
    public class UnionFind {
        // 记录连通分量
        private int count = 0;
        // 节点 x 的根节点是 parent[x]
        private int[] parent;
        // 新增一个数组记录树的“重量”
        private int[] size;
        /* 构造函数，n 为图的节点总数 */
        public UnionFind(int n) {
            // 一开始互不连通
            count = n;
            // 父节点指针初始指向自己
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        public int find(int p) {
            while (parent[p] != p) {
                //路径压缩
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }
        public boolean connected(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            return rootP == rootQ;
        }
        public void union(int p, int q) {
            //找根节点
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) {
                return;
            }
            // 将两棵树合并为一棵
            // 小树接到大树下面，较平衡
            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            } else {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
            count--;
        }
        public int count() {
            return count;
        }
    }
}
