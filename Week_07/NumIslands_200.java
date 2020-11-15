public class NumIslands_200 {
    /**
     * 深度优先遍历
     * 解题思路：以起始节点开始进行深度优先搜索。在深度优先搜索的过程中，每个搜索到的 1 都会被重新标记为 0
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //遍历每个格子,如果当前值为1,将周围相邻格子都变成0,数量+1
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        //边界判断
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == '0') {
            return;
        }
    }

    /**
     * 并查集
     * 解题思路：
     * 并查集中维护连通分量的个数，在遍历的过程中：
     * 1、相邻的陆地（只需要向右看和向下看）合并，只要发生过合并，岛屿的数量就减少 1；
     * 2、在遍历的过程中，同时记录空地的数量；
     * 3、并查集中连通分量的个数 - 空地的个数，就是岛屿数量。
     */
    private int rows;
    private int cols;

    public int numIslands_1(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        rows = grid.length;
        cols = grid[0].length;
        int space = 0;
        UnionFind unionFind = new UnionFind(rows * cols);
        int[][] dic = {{1, 0}, {0, 1}};
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '0') {
                    space++;
                } else {
                    for (int[] direction : dic) {
                        int newi = i + direction[0];
                        int newj = j + direction[1];
                        if (newi < 0 || newj < 0 || newi >= rows || newj >= cols || grid[newi][newj] == '0') {
                            continue;
                        }
                        unionFind.union(getIndex(i, j), getIndex(newi, newj));
                    }
                }
            }
        }
        return unionFind.getCount() - space;
    }

    public int getIndex(int i, int j) {
        return i * cols + j;
    }

    public class UnionFind {
        private int count = 0;
        private int[] parent;
        private int[] size;

        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) {
                return;
            }
            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            } else {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
            count--;
        }

        public int getCount() {
            return count;
        }
    }
}
