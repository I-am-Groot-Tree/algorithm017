import java.util.Arrays;
import java.util.Collections;

public class ReverseWords_557 {
    public String reverseWords(String s) {
        String[] strs = s.trim().split(" +");
        StringBuilder result = new StringBuilder();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            StringBuilder sb = new StringBuilder();
            String reverse = sb.append(chars).reverse().toString();
            result.append(" ");
            result.append(reverse);
        }
        return result.toString().trim();
    }
}
