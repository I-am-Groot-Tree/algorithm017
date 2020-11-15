#学习笔记             
###字典树：              
* 定义：字典树，又成单词查找树或键树，是一种树形结构。典型应用是用于统计和排序大量的字符串（但不仅限于字符串），所以经常被搜索引擎系统用于文本词频统计。
* 优点：最大限度地减少无谓的字符串比较，查询效率比哈希表高
* 基本性质：
  1. 节点本身不存完整单词
  2. 从根节点到某一节点，路径上经过的字符连接起来，为该节点对应的字符串
  3. 每个节点的所有子节点路径代表的字符都不相同  
* 核心思想：空间换时间  
  利用字符串的公共前缀来降低查询时间的开销以达到提高效率的目的
* 代码模板：

        class Trie {
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
                public Trie() {
                    root = new TrieNode();
                }
    
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
    
                public boolean search(String word) {
                    TrieNode node = searchPrefix(word);
                    //搜索到终止符号才算结束
                    return node != null && node.getIsEnd();
                }
    
                public boolean startsWith(String prefix) {
                    TrieNode node = searchPrefix(prefix);
                    //只要搜索到就算前缀
                    return node != null;
                }
            }
----------------------------------------------------------------------
###并查集：              
* 基本操作：
  1. makeSet(s)：简历一个新的并查集，期中包含s个单元素集合。
  2. unionSet(x,y)：把元素x和元素y所在的集合合并，要求x和y所在的集合不相交，如果相交则不合并。
  3. find(x)：找到元素x所在的集合的代表，该操作也可以用于判断两个元素是否位于同一个集合，只要将它们各自的代表比较一下就可以了。
  
* 适用场景（主要是解决图论中「动态连通性」问题）：
  1. 组团、配对问题
  2. Group or not？ 
  
* 关键点：
  1. 用 parent 数组记录每个节点的父节点，相当于指向父节点的指针，所以 parent 数组内实际存储着一个森林（若干棵多叉树）。
  2. 用 size 数组记录着每棵树的重量，目的是让 union 后树依然拥有平衡性，而不会退化成链表，影响操作效率。
  3. 在 find 函数中进行路径压缩，保证任意树的高度保持在常数，使得 union 和 connected API 时间复杂度为 O(1)。
* 代码模板：

        public class UnionFind {
            // 记录连通分量
            private int count = 0;
            // 节点 x 的根节点是 parent[x]
            private int[] parent;
            // 新增一个数组记录树的“重量”
            private int[] size;
        
            /* 构造函数，n 为图的节点总数 */
            public UnionFind(int n) {
                // 一开始互不连通
                count = n;
                // 父节点指针初始指向自己
                parent = new int[n];
                size = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                    size[i] = 1;
                }
            }
        
            public int find(int p) {
                while (parent[p] != p) {
                    //路径压缩
                    parent[p] = parent[parent[p]];
                    p = parent[p];
                }
                return p;
            }
        
            public boolean connected(int p, int q) {
                int rootP = find(p);
                int rootQ = find(q);
                return rootP == rootQ;
            }
        
            public void union(int p, int q) {
                //找根节点
                int rootP = find(p);
                int rootQ = find(q);
                if (rootP == rootQ) {
                    return;
                }
                // 将两棵树合并为一棵
                // 小树接到大树下面，较平衡
                if (size[rootP] > size[rootQ]) {
                    parent[rootQ] = rootP;
                    size[rootP] += size[rootQ];
                } else {
                    parent[rootP] = rootQ;
                    size[rootQ] += size[rootP];
                }
                count--;
            }
        
            public int count() {
                return count;
            }
        }

-----------------------------------------------------------
###二叉搜索树                          
* 定义：二叉搜索树，也称有序二叉树、排序二叉树，是指一颗空树或者具有下列性质的二叉树：
    1. 左子树上所有节点的值均小于它的根节点的值
    2. 右子树上所有节点的值均大于它的根节点的值
    3. 以此类推：左右子树也分别为二叉搜索树
* 中序遍历：升序排列
* 保证性能的关键：保证二维维度（左右子树结点平衡）
-----------------
###AVL树                        
1. 平衡二叉搜索树
2. Balance Factor（平衡因子）：是它的左子树的高度减去它的右子树的高度（有时相反）。
  balance factor = {-1,0,1}
3. 通过旋转操作来进行平衡（四种）：
  - 左旋：
  子树形态：右右子树->左旋
  - 右旋：
  子树形态：左左子树->右旋
  - 左右旋：
  子树形态：左右子树->左右旋
  - 右左旋
  子树形态：右左子树->右左旋

不足：结点需要存储额外信息、且调整次数频繁
-----------------
###红黑树                       
* 定义：红黑树是一种近似平衡的二叉搜索树，它能够确保任何一个节点的左右子树的高度差小于两倍。
    1. 每个结点要么是红色，要么是黑色
    2. 根节点是黑色
    3. 每个叶子结点（NIL结点，空结点）是黑色的
    4. 不能有相邻接的两个红色结点
    5. 从任一结点到其每个叶子的所有路径都包含相同数目的黑色结点
------------------
###回溯算法框架
* 解决一个回溯问题，实际上就是一个决策树的遍历过程。你只需要思考 3 个问题：
  - 路径：也就是已经做出的选择。
  - 选择列表：也就是你当前可以做的选择。
  - 结束条件：也就是到达决策树底层，无法再做选择的条件。 
  
 * 代码模板：

        result = []
        def backtrack(路径, 选择列表):
        if 满足结束条件:
        result.add(路径)
        return
        for 选择 in 选择列表:
        做选择
        backtrack(路径, 选择列表)
        撤销选择