public class ClimbStairs_70 {
    /**
     * 记忆化递归
     * 解题思路：将计算过的数值缓存在数组中，防止重复计算
     */
    public int climbStairs(int n) {
        int[] cache = new int[n + 1];
        return cacheStairs(n, cache);
    }

    private int cacheStairs(int n, int[] cache) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (cache[n] == 0) {
            cache[n] = cacheStairs(n - 1, cache) + cacheStairs(n - 2, cache);
        }
        return cache[n];
    }

    /**
     * 动态规划
     * 解题思路：将爬楼梯问题拆解，最后一个台阶只能是一次1阶或者一次2阶上去
     * 斐波那契数列
     * F(n) = F(n-1)+F(n-2)
     */
    public int climbStairs_1(int n) {
        int t[] = new int[n + 1];
        t[0] = 1;
        t[1] = 1;
        for (int i = 2; i <= n; i++) {
            t[i] = t[i - 1] + t[i - 2];
        }
        return t[n];
    }

    /**
     * 滚动数组法
     * 0,0,1->0,1,1->1,1,2
     */
    int p = 0;
    int q = 0;
    int r = 1;

    public int climbStairs_2(int n) {
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
}
