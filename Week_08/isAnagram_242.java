import java.util.Arrays;

public class isAnagram_242 {
    /**
     * 排序法
     * 解题思路：比较排序后字符是否相等
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] s1 = s.toCharArray();
        char[] t1 = t.toCharArray();
        Arrays.sort(s1);
        Arrays.sort(t1);
        return Arrays.equals(s1,t1);
    }

    /**
     * 哈希映射
     * 解题思路：
     * 1、初始化 26 个字母哈希表，遍历字符串 s 和 t
     * 2、s 负责在对应位置增加，t 负责在对应位置减少
     * 3、如果哈希表的值都为 0，则二者是字母异位词
     */
    public boolean isAnagram_1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] temp = new int[26];
        for (int i = 0; i < s.length(); i++) {
            temp[s.charAt(i) - 'a']++;
            temp[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (temp[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
