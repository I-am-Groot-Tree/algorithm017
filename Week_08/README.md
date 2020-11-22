#学习笔记             
###布隆过滤器：              
* 定义：一个很长的二进制向量和一系列随机映射函数。布隆过滤器可以用于检索一个元素是否在一个集合中。
* 优点：空间效率和查询时间都远远超过一般的算法
* 缺点：有一定的误识别率和删除困难
----------------------------------------------------------------------
###LRU Cache：              
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
###排序算法：
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