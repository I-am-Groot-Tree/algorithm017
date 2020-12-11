# 毕业总结
## 学习心得
这70天算法训练营的学习使我收获颇丰，曾经的我对于算法是那么的恐惧，经过这段时间的学习我对于
算法的整体知识脉络已经有了一个清晰的了解，也学习到了“五毒神掌”这样的绝技，不过俗话说的好：
“师傅领进门，修行在个人”，算法不是可以一蹴而就，要靠不断的积累修炼内功最终才能成为一个高手。
非常感谢这段时间覃超老师的教导，带我迈进了这扇大门，给我指明了前进的方向，我会坚定不移的继续
走下去，期待明年能收获一个满意的答卷。
## 五毒神掌
* 刷题第一遍：5分钟：读题+思考
直接看解法：注意！多解法，比较解法优劣
背诵、默写好的解法

* 刷题第二遍
马上自己写->LeetCode提交
多种解法比较、体会->优化

* 刷题第三遍
过了一天后，再重复做题
不同解法的熟练程度->专项练习

* 刷题第四遍
过了一周：反复回来练习相同题目

* 刷题第五遍
面试前一周恢复性训练
## 递归代码模板：
    public void recursion(int level, int param) {
        //recursion terminator 递归终结条件
        if (level > MAX_LEVEL) {
            // process_result
            return;
        }
        //drill down 下探到下一层
        recursion(level + 1, newParam);
        //reverse the current level status if needed 清理当前层
    }
递归思维要点:
1. 不要人肉进行递归
2. 找到最近最简方法，将其拆解成可重复解决的问题(重复子问题)
3. 数学归纳法思维

## 二叉堆
1. 二叉堆一般都通过"数组"来实现
2. 假设"第一个元素"在数组中的索引为0的话，则父节点和子节点的位置关系如下：
    * 根节点(顶堆元素)是：a[0]
    * 索引为i的左孩子的索引是(2*i+1);
    * 索引为i的右孩子的索引是(2*i+2);
    * 索引为i的父节点的索引是floor((i-1)/2)；
3. 插入操作：
先插入到尾部，再和他的父亲节点比较，若大于父亲节点则替换
4. 删除操作：
先插入到头部，再和左右孩子比较，用更大的替换自己

## 分治代码模板
    public void divide_conquer(problem,param1,param2,..) {
        //recursion terminator 递归终结条件
        if problem is None:
            print_result
            return;
        //prepare data 准备数据
        data = prepare_data(problem);
        subproblem = split_problem(problem,data);
        //conquer subproblems 分解子问题
        subresult1 = divide_conquer(problem[0],p1,...);
        subresult2 = divide_conquer(problem[1],p1,...);
        subresult3 = divide_conquer(problem[1],p1,...);
        ...
        //process and generate the final result 聚合结果
        result = process_result(subresult1,subresult2,subresult3,...);
        //revert the current level status if needed 清理当前层
    }
    
## 深度优先代码模板：
    def dfs(node):
        if node in visited:
            # already visited
            return;
        visited.add(node)
        
        #process current node
        #...#logic here
        dfs(node.left);
        dfs(node.right);
-----------------
## 广度优先代码模板：
    def BFS(graph,start,end):
        queue = [];
        queue.append([start])
        visited.add(start)
        
        while queue:
            node = queue.pop();
            visited.add(node);
            
            process(node);
            nodes = generate_related_nodes(node);
            queue.push(nodes);
        #other processing work
-----------------
## 二分查找（时间复杂度：O(logN)）
1. 目标函数单调性
2. 存在上下界
3. 能够通过索引访问
#### 代码模板
    left,right = 0,length(array)-1;
    while left<=right:
        mid = (left+right)/2
        if array[mid]==target:
            #find the target!
            break or return result
        else if array[mid] < target:
            left = mid + 1;
            else
            right = mid - 1;
---------------------------------
## 贪心算法（不能回退）
- 当下做局部最优判断，从而希望全局最优
-----------------------------------------
## 动态规划（最优判断+回退）
* 保存以前的运算结果，并根据以前的结果对当前进行选择
* 动态规划和递归或者分治没有根本上的区别（关键看有无最优的子结构）   
* 共性：找到重复子问题   
* 差异性：最优子结构、中途可以淘汰次优解
1. 最优子结构 opt[n] = best_of(opt[n-1],opt[n-2],.....)
2. 储存中间状态：opt[i]
3. 递推公式（状态转移方程或DP方程）
### 小结
* 打破自己的思维惯性，形成机器思维
* 理解复杂逻辑的关键
* 动态规划题目思考步骤：
    - 最重要的是先找到状态dp代表什么含义
    - 再明确 dp 动态转移方程的定义
    - 最后寻找状态之间的关系
## 字典树：              
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
## 并查集：              
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
## 二叉搜索树                          
* 定义：二叉搜索树，也称有序二叉树、排序二叉树，是指一颗空树或者具有下列性质的二叉树：
    1. 左子树上所有节点的值均小于它的根节点的值
    2. 右子树上所有节点的值均大于它的根节点的值
    3. 以此类推：左右子树也分别为二叉搜索树
* 中序遍历：升序排列
* 保证性能的关键：保证二维维度（左右子树结点平衡）
-----------------
## AVL树                        
1. 平衡二叉搜索树
2. Balance Factor（平衡因子）：是它的左子树的高度减去它的右子树的高度（有时相反）。
  balance factor = {-1,0,1}
3. 不足：结点需要存储额外信息、且调整次数频繁
4. 通过旋转操作来进行平衡（四种）：
  - 左旋：
  子树形态：右右子树->左旋
  - 右旋：
  子树形态：左左子树->右旋
  - 左右旋：
  子树形态：左右子树->左右旋
  - 右左旋
  子树形态：右左子树->右左旋
---------------------------------------
## 红黑树                       
* 定义：红黑树是一种近似平衡的二叉搜索树，它能够确保任何一个节点的左右子树的高度差小于两倍。
    1. 每个结点要么是红色，要么是黑色
    2. 根节点是黑色
    3. 每个叶子结点（NIL结点，空结点）是黑色的
    4. 不能有相邻接的两个红色结点
    5. 从任一结点到其每个叶子的所有路径都包含相同数目的黑色结点
------------------
## 回溯算法框架
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
---------------------------------------------
## 布隆过滤器：              
* 定义：一个很长的二进制向量和一系列随机映射函数。布隆过滤器可以用于检索一个元素是否在一个集合中。
* 优点：空间效率和查询时间都远远超过一般的算法
* 缺点：有一定的误识别率和删除困难
----------------------------------------------------------------------
## LRU Cache：              
* 代码模板：

        class LRUCache extends LinkedHashMap<Integer, Integer>{
            private int capacity;
            
            public LRUCache(int capacity) {
                super(capacity, 0.75F, true);
                this.capacity = capacity;
            }
        
            public int get(int key) {
                return super.getOrDefault(key, -1);
            }
        
            public void put(int key, int value) {
                super.put(key, value);
            }
        
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity; 
            }
        }

-----------------------------------------------------------
## 排序算法：
* 选择排序：

        public static void sort(int[] array) {
            for (int i = 0; i < array.length; i++) {
                int minIndex = i;
                //把当前遍历的数与后面所有的数作比较,找出最小的数的下标
                for (int j = i + 1; j < array.length; j++) {
                    if (array[j] < array[minIndex]) {
                        minIndex = j;
                    }
                }
                if (i != minIndex) {
                    int temp = array[i];
                    array[i] = array[minIndex];
                    array[minIndex] = temp;
                }
            }
        }
* 希尔排序：

            public static void sort(int[] array) {
                //遍历所有步长
                for (int d = array.length / 2; d > 0; d /= 2) {
                    //遍历所有元素
                    for (int i = d; i < array.length; i++) {
                        //遍历本组元素
                        for (int j = i - d; j >= 0; j -= d) {
                            if (array[j] > array[j + d]) {
                                int temp = array[j];
                                array[j] = array[j + d];
                                array[j + d] = temp;
                            }
                        }
                    }
                }
            }
* 插入排序：

            public static void sort(int[] array) {
                    //从第二个开始遍历所有数组
                    for (int i = 1; i < array.length; i++) {
                        //如果小于前一个数字
                        if (array[i] < array[i - 1]) {
                            int temp = array[i];
                            int j;
                            //遍历当前数字前面的所有数字，找到合适位置插入
                            for (j = i - 1; j >= 0 && temp < array[j]; j--) {
                                array[j + 1] = array[j];
                            }
                            array[j + 1] = temp;
                        }
                    }
                }
* 冒泡排序：

            public static void bubble(int[] array) {
                    for (int i = 0; i < array.length - 1; i++) {
                        for (int j = 0; j < array.length - 1 - i; j++) {
                            if (array[j] < array[j + 1]) {
                                int temp = array[j];
                                array[j] = array[j + 1];
                                array[j + 1] = temp;
                            }
                        }
                    }
                }
* 快速排序：

            public static void sort(int[] array, int start, int end) {
                    if (start < end) {
                        //把数组中的第0个数字作为标准数
                        int stard = array[start];
                        //记录需要排序的下标
                        int low = start;
                        int high = end;
                        while (low < high) {
                            //如果标准数比右边的数字小
                            while (low < high && stard <= array[high]) {
                                high--;
                            }
                            array[low] = array[high];
                            //如果标准数比左边的数字大
                            while (low < high && array[low] <= stard) {
                                low++;
                            }
                            array[high] = array[low];
                        }
                        array[low] = stard;
                        sort(array, start, low);
                        sort(array, low + 1, end);
                    }
                }
* 归并排序：

                 public void mergeSort(int[] array, int left, int right) {
                    if (right <= left) {
                        return;
                    }
                    int mid = (left + right) >> 1;
                    //左边数组单独排序
                    mergeSort(array, left, mid);
                    //右边数组单独排序
                    mergeSort(array, mid + 1, right);
                    //合并
                    merge(array, left, mid, right);
                }
                
                public void merge(int[] array, int left, int mid, int right) {
                    //临时数组
                    int[] temp = new int[right - left + 1];
                    //分为两个数组比较大小,小的元素加入临时数组
                    int i = left, j = mid + 1, p = 0;
                    while (i <= mid && j <= right) {
                        temp[p++] = array[i] <= array[j] ? array[i++] : array[j++];
                    }
                    //处理左边数组剩余元素,按顺序放入临时数组
                    while (i <= mid) {
                        temp[p++] = array[i++];
                    }
                    while (j <= right) {
                        temp[p++] = array[j++];
                    }
                    //临时数组拷贝回原数组
                    System.arraycopy(temp, 0, array, left, temp.length);
                }