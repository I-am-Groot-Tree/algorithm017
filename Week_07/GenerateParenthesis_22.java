import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis_22 {
    /**
     * 解题思路：
     * 1、括号就两种情况，左括号或者右括号（递归逻辑）
     * 2、左括号随便加，只要不超过限制
     * 3、右括号之前必须有左括号，添加右括号之前判断右个数<左个数
     */
    List<String> list = new ArrayList<>();

    private List<String> generateParenthesis(int n) {
        generate(0, 0, n, "");
        return list;
    }

    private void generate(int left, int right, int n, String s) {
        if (left == n && right == n) {
            list.add(s);
            return;
        }
        if (left < n) {
            generate(left + 1, right, n, s + "(");
        }
        if (right < left) {
            generate(left, right + 1, n, s + ")");
        }
    }
}
