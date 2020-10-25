#学习笔记
###本周主要学习了深度优先遍历(DFS)及广度优先遍历(BFS),最重要的是要熟练的记下来代码模板，行程肌肉记忆
###对于二分查找，一定要牢记适用场景
####深度优先代码模板：
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
####递归写法：
    visited = set();
    def dfs(node,visited):
        #terminator
        if node in visited:
        return;
        
        visited.add(node);
        
        #process current node here.
        ...
        for next_node in node.children():
            if not next_node in visited:
                dfs(next node,visited);
-----------------
####广度优先代码模板：
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
####二分查找（时间复杂度：O(logN)）
###适用场景
1. 目标函数单调性
2. 存在上下界
3. 能够通过索引访问
###代码模板
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
####贪心算法（不能回退）：当下做局部最优判断，从而希望全局最优
####回溯：能够回退
####动态规划（最优判断+回退）：保存以前的运算结果，并根据以前的结果对当前进行选择