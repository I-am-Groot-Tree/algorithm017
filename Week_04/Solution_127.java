import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Solution_127 {
    /**
     * 解题思路：
     * 广度优先遍历
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //先将字典放入哈希表，便于判断某个单词是否在字典中里
        Set<String> dictionaries = new HashSet<>(wordList);
        //如果字典里没有endWord直接返回
        if (dictionaries.size() == 0 || !dictionaries.contains(endWord)) {
            return 0;
        }
        //去除开始单词
        dictionaries.remove(beginWord);
        //记录已访问的单词
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        //广度优先模板
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        //开始广度优先，包含起点，因此初始化为1
        int step = 1;
        while (!queue.isEmpty()) {
            //要先计算出队列大小，因为遍历过程中会改变
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                String currentWord = queue.poll();
                //如果改变一个字母后能得到endWords，返回结果step+1
                if (findWords(currentWord, endWord, queue, visited, dictionaries)) {
                    return step + 1;
                }
            }
            //当前层遍历结束,step + 1
            step++;
        }
        //无法得到endWord
        return 0;
    }

    private boolean findWords(String begin, String endWord, Queue<String> queue, Set<String> visited, Set<String> dictionaries) {
        char[] beginChars = begin.toCharArray();
        //遍历每个字母
        for (int i = 0; i < beginChars.length; i++) {
            //记录当前字母，遍历后需还原
            char currentChar = beginChars[i];
            //枚举26个字母的所有可能
            for (char k = 'a'; k <= 'z'; k++) {
                //跳过本身
                if (k == currentChar) {
                    continue;
                }
                //替换字母
                beginChars[i] = k;
                //当前单词
                String currentWord = String.valueOf(beginChars);
                //字典里需要存在
                if (dictionaries.contains(currentWord)) {
                    //找到endWord结束
                    if (currentWord.equals(endWord)) {
                        return true;
                    }
                    //单词没有被访问过，放入队列中
                    if (!visited.contains(currentWord)) {
                        queue.add(currentWord);
                        // 注意：添加到队列以后，必须马上标记为已经访问，否则会出现重复
                        visited.add(currentWord);
                    }
                }
            }
            //还原单词
            beginChars[i] = currentChar;
        }
        return false;
    }
}
