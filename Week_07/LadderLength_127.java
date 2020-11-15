import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class LadderLength_127 {

    /**
     * 双向BFS
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 第 1 步：先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
        Set<String> dictionaries = new HashSet<>(wordList);
        if (dictionaries.size() == 0 || !dictionaries.contains(endWord)) {
            return 0;
        }
        // 第 2 步：已经访问过的 word 添加到 visited 哈希表里
        Set<String> visited = new HashSet<>();
        // 分别用左边和右边扩散的哈希表代替单向 BFS 里的队列，它们在双向 BFS 的过程中交替使用
        Set<String> beginVisited = new HashSet<>();
        beginVisited.add(beginWord);
        Set<String> endVisited = new HashSet<>();
        endVisited.add(endWord);

        int count = 1;
        // 第 3 步：执行双向 BFS，左右交替扩散的步数之和为所求
        while (!beginVisited.isEmpty() && !endVisited.isEmpty()) {
            // 优先选择小的哈希表进行扩散，考虑到的情况更少
            if (beginVisited.size() > endVisited.size()) {
                Set<String> tmp = beginVisited;
                beginVisited = endVisited;
                endVisited = tmp;
            }
            // 逻辑到这里，保证 beginVisited 是相对较小的集合，nextLevelVisited 在扩散完成以后，会成为新的 beginVisited
            Set<String> nextLevelVisited = new HashSet<>();
            for (String cur : beginVisited) {
                if (find(cur, endVisited, dictionaries, visited, nextLevelVisited)) {
                    return count + 1;
                }
            }
            // 原来的 beginVisited 废弃，从 nextLevelVisited 开始新的双向 BFS
            beginVisited = nextLevelVisited;
            count++;
        }
        return 0;
    }

    private boolean find(String cur, Set<String> endVisited, Set<String> dictionaries, Set<String> visited, Set<String> nextLevelVisited) {
        char[] curChars = cur.toCharArray();
        for (int i = 0; i < curChars.length; i++) {
            char temp = curChars[i];
            for (char k = 'a'; k <= 'z'; k++) {
                if (curChars[i] == k) {
                    continue;
                }
                curChars[i] = k;
                String curWord = String.valueOf(curChars);
                if (dictionaries.contains(curWord)) {
                    if (endVisited.contains(curWord)) {
                        return true;
                    }
                    if (!visited.contains(curWord)) {
                        nextLevelVisited.add(curWord);
                        visited.add(curWord);
                    }
                }
            }
            curChars[i] = temp;
        }
        return false;
    }
}
