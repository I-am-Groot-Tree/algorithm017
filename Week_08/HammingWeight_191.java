public class HammingWeight_191 {
    /**
     * 解题思路：
     * 1、将n、n-1做与运算，会将最后一位1变为0
     * 2、做几次运算变为0就有几个1
     */
    public int hammingWeight(int n) {
        int sum = 0;
        while(n!=0){
            sum++;
            n&=(n-1);
        }
        return sum;
    }
}
