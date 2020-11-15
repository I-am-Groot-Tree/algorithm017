import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindWords_212 {
    /**
     * DFS+字典树
     * 解题思路：
     * 1、先将单词放入字典树中
     * 2、遍历二维数组根据前缀找单词
     */
    class Solution {
        int[] x = {-1, 1, 0, 0};
        int[] y = {0, 0, 1, -1};
        private final char USED = '#';
        Set<String> result = new HashSet<>();

        public List<String> findWords(char[][] board, String[] words) {
            //构建字典树
            Trie trie = new Trie();
            for (String word : words) {
                trie.insert(word);
            }
            //遍历二维数组
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (trie.root.containKey(board[i][j])) {
                        dfs(board, i, j, trie.root);
                    }
                }
            }
            return new ArrayList<>(result);
        }

        private void dfs(char[][] board, int i, int j, Trie.TrieNode root) {
            //获取子节点状态，判断其是否有子节点
            Trie.TrieNode cur = root.get(board[i][j]);
            if (cur == null) {
                return;
            }
            //找到单词返回
            if (cur.getIsEnd()) {
                result.add(cur.val);
                //找到单词后，修改字典树内叶子节点状态为false，防止出现重复单词
                cur.isEnd = false;
            }
            char tmp = board[i][j];
            //修改节点状态，防止重复访问
            board[i][j] = USED;
            //四联通遍历
            for (int index = 0; index < 4; index++) {
                int newi = i + x[index];
                int newj = j + y[index];
                //边界条件判断以及是否已经访问
                if (newi < 0 || newj < 0 || newi >= board.length || newj >= board[0].length || board[newi][newj] == USED) {
                    continue;
                }
                if (cur.containKey(board[newi][newj])) {
                    dfs(board, newi, newj, cur);
                }
            }
            //最后修改节点状态为未访问状态
            board[i][j] = tmp;
        }

        class Trie {
            class TrieNode {
                TrieNode[] linked;
                final int R = 26;
                boolean isEnd;
                String val;

                TrieNode() {
                    linked = new TrieNode[R];
                }

                boolean containKey(char c) {
                    return linked[c - 'a'] != null;
                }

                TrieNode get(char c) {
                    return linked[c - 'a'];
                }

                void put(char c, TrieNode node) {
                    linked[c - 'a'] = node;
                }

                void setEnd() {
                    isEnd = true;
                }

                boolean getIsEnd() {
                    return isEnd;
                }
            }

            TrieNode root;

            /**
             * Initialize your data structure here.
             */
            public Trie() {
                root = new TrieNode();
            }

            /**
             * Inserts a word into the trie.
             */
            public void insert(String word) {
                TrieNode node = root;
                for (int i = 0; i < word.length(); i++) {
                    char cur = word.charAt(i);
                    if (!node.containKey(cur)) {
                        node.put(cur, new TrieNode());
                    }
                    node = node.get(cur);
                }
                node.setEnd();
                node.val = word;
            }
        }
    }
}
