public class IsPowerOfTwo_231 {
    /**
     * 解题思路：
     * 若 n = 2^x且 x 为自然数（即 n 为 2 的幂），则一定满足以下条件：
     * 恒有 n & (n - 1) == 0，这是因为：
     * n 二进制最高位为 1，其余所有位为 0；
     * n - 1 二进制最高位为 0，其余所有位为 1；
     * 一定满足 n > 0。
     * 因此，通过 n > 0 且 n & (n - 1) == 0 即可判定是否满足 n = 2^x
     */
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n&(n-1))==0;
    }
}
