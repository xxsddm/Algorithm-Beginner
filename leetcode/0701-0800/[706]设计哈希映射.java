//不使用任何内建的哈希表库设计一个哈希映射（HashMap）。 
//
// 实现 MyHashMap 类： 
//
// 
// MyHashMap() 用空映射初始化对象 
// void put(int key, int value) 向 HashMap 插入一个键值对 (key, value) 。如果 key 已经存在于映射中，
//则更新其对应的值 value 。 
// int get(int key) 返回特定的 key 所映射的 value ；如果映射中不包含 key 的映射，返回 -1 。 
// void remove(key) 如果映射中存在 key 的映射，则移除 key 和它所对应的 value 。 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
//[[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
//输出：
//[null, null, null, 1, -1, null, 1, null, -1]
//
//解释：
//MyHashMap myHashMap = new MyHashMap();
//myHashMap.put(1, 1); // myHashMap 现在为 [[1,1]]
//myHashMap.put(2, 2); // myHashMap 现在为 [[1,1], [2,2]]
//myHashMap.get(1);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,2]]
//myHashMap.get(3);    // 返回 -1（未找到），myHashMap 现在为 [[1,1], [2,2]]
//myHashMap.put(2, 1); // myHashMap 现在为 [[1,1], [2,1]]（更新已有的值）
//myHashMap.get(2);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,1]]
//myHashMap.remove(2); // 删除键为 2 的数据，myHashMap 现在为 [[1,1]]
//myHashMap.get(2);    // 返回 -1（未找到），myHashMap 现在为 [[1,1]]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= key, value <= 10⁶ 
// 最多调用 10⁴ 次 put、get 和 remove 方法 
// 
//
// 
//
// 进阶：你能否不使用内置的 HashMap 库解决此问题？ 
// Related Topics 设计 数组 哈希表 链表 哈希函数 👍 228 👎 0

import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class MyHashMap {
    static float capRatio;
    int size;
    LinkedList<int[]>[] container;

    /** Initialize your data structure here. */
    public MyHashMap() {
        capRatio = 0.8F;
        size = 0;
        // 注意不可直接构建泛型数组
        container = (LinkedList<int[]>[]) new LinkedList[1024];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int temp = Integer.hashCode(key);
        int loc = temp % container.length;
        if (container[loc] == null) {
            container[loc] = new LinkedList<>();
        }
        for (int[] pair: container[loc]) {
            if (pair[0] == temp) {
                pair[1] = value;
                return;
            }
        }
        size++;
        container[loc].add(new int[] {temp, value});
        if (size >= (int) (capRatio * container.length)) {  // 考虑扩容
            LinkedList<int[]>[] tempContainer =
                    (LinkedList<int[]>[]) new LinkedList[container.length * 2];
            for (LinkedList<int[]> sublist: container) {
                if (sublist == null) {
                    continue;
                }
                for (int[] pair: sublist) {
                    int hash = Integer.hashCode(pair[0]);
                    int temploc = hash % (2 * container.length);
                    if (tempContainer[temploc] == null) {
                        tempContainer[temploc] = new LinkedList<>();
                    }
                    tempContainer[temploc].add(pair);
                }
            }
            container = tempContainer;
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int temp = Integer.hashCode(key);
        int loc = temp % container.length;
        if (container[loc] == null) {
            return - 1;
        }
        for (int[] pair: container[loc]) {
            if (pair[0] == temp) {
                return pair[1];
            }
        }
        return - 1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int temp = Integer.hashCode(key);
        int loc = temp % container.length;
        int[] target = null;
        if (container[loc] == null) {
            return;
        }
        for (int[] pair: container[loc]) {
            if (pair[0] == temp) {
                target = pair;
                break;
            }
        }
        if (target != null) {
            container[loc].remove(target);
            size--;
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */

//leetcode submit region end(Prohibit modification and deletion)
