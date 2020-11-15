public class Trie_208 {

    class TrieNode {
        private TrieNode[] linked;
        private final int R = 26;
        boolean isEnd;

        public TrieNode() {
            linked = new TrieNode[R];
        }

        public boolean containKey(char c) {
            return linked[c - 'a'] != null;
        }

        public TrieNode get(char c) {
            return linked[c - 'a'];
        }

        public void put(char c, TrieNode node) {
            linked[c - 'a'] = node;
        }

        public void setEnd() {
            this.isEnd = true;
        }

        public boolean getIsEnd() {
            return isEnd;
        }
    }

    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public Trie_208() {
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
            //下探到下一层节点
            node = node.get(cur);
        }
        //注意设置终止符号
        node.setEnd();
    }

    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char cur = word.charAt(i);
            if (node.containKey(cur)) {
                //下探到下一层节点
                node = node.get(cur);
            } else {
                return null;
            }
        }
        return node;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        //搜索到终止符号才算结束
        return node != null && node.getIsEnd();
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        //只要搜索到就算前缀
        return node != null;
    }
}
