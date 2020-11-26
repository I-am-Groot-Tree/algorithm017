import java.util.Arrays;
import java.util.Collections;

public class ReverseWords_151 {
    public String reverseWords(String s) {
        String[] strs = s.trim().split(" +");
        Collections.reverse(Arrays.asList(strs));
        return String.join(" ", strs);
    }
}
