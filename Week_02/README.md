学习笔记
HashMap总结:
1、HashMap不是线程安全的数据结构
2、HashMap 的性能表现非常依赖于哈希码的有效性：
①equals 相等，hashCode 一定要相等
②重写了 hashCode 也要重写 equals
③hashCode 需要保持一致性，状态改变返回的哈希值仍然要一致。
3、源码分析
它可以看作是数组(Node<K,V>[] table)和链表结合组成的复合结构，数组被分为一个个桶，通过哈希值决定了键值对在这个数组的寻址；哈希值相同的键值对，则以链表形式存储。
put
1、如果表格是 null，resize 方法会负责初始化它，这从 tab = resize() 可以看出。
2、resize 方法兼顾两个职责，创建初始存储表格，或者在容量不满足需求的时候，进行扩容（resize）。
if (++size > threshold) resize();

3、具体键值对在哈希表中的位置（数组 index）取决于下面的位运算：
i = (n - 1) & hash

仔细观察哈希值的源头，我们会发现，它并不是 key 本身的 hashCode，而是来自于 HashMap 内部的另外一个 hash 方法。注意，为什么这里需要将高位数据移位到低位进行异或运算呢？这是因为有些数据计算出的哈希值差异主要在高位，而 HashMap 里的哈希寻址是忽略容量以上的高位的，那么这种处理就可以有效避免类似情况下的哈希碰撞。
static final int hash(Object kye) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>>16);
}

4、链表结构（这里叫 bin），会在达到一定门限值时，发生树化。
本质上这是个安全问题。因为在元素放置过程中，如果一个对象哈希冲突，都被放置到同一个桶里，则会形成一个链表，我们知道链表查询是线性的，会严重影响存取的性能。

递归代码模板：
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
递归思维要点
1、不要人肉进行递归
2、找到最近最简方法，将其拆解成可重复解决的问题(重复子问题)
3、数学归纳法思维

二叉堆

1、二叉堆一般都通过"数组"来实现
2、假设"第一个元素"在数组中的索引为0的话，则父节点和子节点的位置关系如下：
根节点(顶堆元素)是：a[0]
①索引为i的左孩子的索引是(2*i+1);
②索引为i的右孩子的索引是(2*i+2);
③索引为i的父节点的索引是floor((i-1)/2)；
3、插入操作：
先插入到尾部，再和他的父亲节点比较，若大于父亲节点则替换
4、删除操作：
先插入到头部，再和左右孩子比较，用更大的替换自己
