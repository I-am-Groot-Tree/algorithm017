import java.util.Arrays;
import java.util.Collections;

public class ReverseOnlyLetters_917 {
    /**
     * 双指针
     */
    public String reverseOnlyLetters(String S) {
        char[] result = new char[S.length()];
        char[] chars = S.toCharArray();
        int i = 0;
        int j = S.length() - 1;
        while (i < S.length() && j >= 0) {
            //都是字母就交换,特殊字符不换位置
            if (isAlphabet(chars[i]) && isAlphabet(chars[j])) {
                result[i] = chars[j];
                i++;
                j--;
            } else if (!isAlphabet(chars[i])) {
                result[i] = chars[i];
                i++;
            } else {
                result[j] = chars[j];
                j--;
            }
        }
        return String.valueOf(result);
    }

    private boolean isAlphabet(char s) {
        return (s >= 'a' && s <= 'z') || (s >= 'A' && s <= 'Z');
    }
}
