#学习笔记
###本周主要联系递归相关题目，其中分治及回溯算法也是特殊的递归，只是在递归的最后一步进行结果的收集，回溯要有一种归去来兮的感觉。
####递归代码模板：
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
-----------------
递归思维要点
1. 不要人肉进行递归
2. 找到最近最简方法，将其拆解成可重复解决的问题(重复子问题)
3. 数学归纳法思维
----------------------
####分治代码模板
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