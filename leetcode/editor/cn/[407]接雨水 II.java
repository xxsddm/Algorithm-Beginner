//给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
//输出: 4
//解释: 下雨后，雨水将会被上图蓝色的方块中。总的接雨水量为1+2+1=4。
// 
//
// 示例 2: 
//
// 
//
// 
//输入: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
//输出: 10
// 
//
// 
//
// 提示: 
//
// 
// m == heightMap.length 
// n == heightMap[i].length 
// 1 <= m, n <= 200 
// 0 <= heightMap[i][j] <= 2 * 10⁴ 
// 
//
// 
// Related Topics 广度优先搜索 数组 矩阵 堆（优先队列） 👍 373 👎 0


import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int trapRainWater(int[][] heights) {
        int width = heights.length;
        int length = heights[0].length;
        // 用一个used数组来标记该位置是否被访问过
        boolean[][] used = new boolean[width][length];
        // 优先队列中存放三元组 [x,y,h] 坐标和高度
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

        // 放入最外圈
        for (int i = 0; i < length - 1; i++) {
            pq.add(new int[] {0, i, heights[0][i]});
            used[0][i] = true;
        }
        for (int i = 0; i < width - 1; i++) {
            pq.add(new int[] {i, length - 1, heights[i][length - 1]});
            used[i][length - 1] = true;
        }
        for (int i = 1; i < length; i++) {
            pq.add(new int[] {width - 1, i, heights[width - 1][i]});
            used[width - 1][i] = true;
        }
        for (int i = 1; i < width; i++) {
            pq.add(new int[] {i, 0, heights[i][0]});
            used[i][0] = true;
        }

        int ans = 0;
        // 方向数组，把dx和dy压缩成一维来做
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!pq.isEmpty()) {
            // 圈中最小的高度作为桶高
            int[] poll = pq.poll();
            // 看一下周围四个方向，没访问过的话能不能往里灌水
            for (int k = 0; k < 4; k++) {
                int row = poll[0] + dirs[k];
                int column = poll[1] + dirs[k + 1];
                // 如果位置合法且没访问过
                if (row >= 0 && row < width && column >= 0 && column < length && !used[row][column]) {
                    // 如果外围这一圈中最小的比当前这个还高，那就说明能往里面灌水啊
                    if (poll[2] > heights[row][column]) {
                        ans += poll[2] - heights[row][column];
                    }
                    // 如果灌水则高度为灌水后的高度，如果没灌水即为该点原本高度
                    pq.add(new int[]{row, column, Math.max(heights[row][column], poll[2])});
                    used[row][column] = true;
                }
            }
        }
        return ans;
    }
}

// https://leetcode-cn.com/problems/trapping-rain-water-ii/solution/you-xian-dui-lie-de-si-lu-jie-jue-jie-yu-shui-ii-b/
//leetcode submit region end(Prohibit modification and deletion)


class PQ<Key> implements Iterable<Key> {    // 测试优先队列PQ
    private int size;
    private Key[] container;
    private final Comparator<Key> comparator;

    public PQ() {
        this(16, null);
    }

    public PQ(int n) {
        this(n, null);
    }

    public PQ(Comparator<Key> compare) {
        this(16, compare);
    }

    public PQ(int n, Comparator<Key> compare) {
        size = 0;
        container = (Key[]) new Object[n];
        comparator = compare;
    }

    public void add(Key key) {  // 增
        if (size == container.length) {
            resize(container.length * 2);
        }
        container[size] = key;
        up(size++);
    }

    public Key poll() { // 删(同时查)
        if (isEmpty()) {
            return null;
        }
        Key ans = container[0];
        exchange(0, size - 1);
        container[size-- - 1] = null;
        down(0);
        if (size * 2 < container.length) {
            resize((int) (size * 1.2));
        }
        return ans;
    }

    public void delete(Key key) {   // 删(直接删除)
        int idx = - 1;
        for (int i = 0; i < size; i++) {
            if (compare(container[i], key) == 0) {
                idx = i;
                break;
            }
        }
        if (idx == - 1) {
            return;
        }
        exchange(idx, size - 1);
        container[size-- - 1] = null;
        up(idx);
        down(idx);
        if (size * 2 < container.length) {
            resize((int) (size * 1.2));
        }
    }

    public void change(Key from, Key to) {  // 改
        for (int i = 0; i < size; i++) {
            if (compare(container[i], from) == 0) {
                container[i] = to;
                up(i);
                down(i);
                return;
            }
        }
    }

    public void idxChange(int idx, Key key, boolean move) {  // 改(根据索引)
        assert idx >= 0 && idx < size;
        container[idx] = key;
        if (move) {
            up(idx);
            down(idx);
        }
    }

    public Key peek() { // 查
        if (size == 0) {
            return null;
        }
        return container[0];
    }

    public boolean containsKey(Key key) {   // 查(返回是否存在)
        for (Key item: container) {
            if (compare(item, key) == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void up(int idx) {  // 小于父节点, 上移
        // 取相同值时退出循环
        while (idx > 0 && compare(container[idx], container[((idx + 1) >> 1) - 1]) < 0) {
            exchange(idx, ((idx + 1) >> 1) - 1);
            idx = ((idx + 1) >> 1) - 1;
        }
    }

    private void down(int idx) {    // 大于子节点, 下移
        while (((idx + 1) << 1) - 1 < size) {
            if (compare(container[((idx + 1) << 1) - 1], container[idx]) < 0) {
                if (((idx + 1) << 1) < size && compare(container[(idx + 1) << 1], container[((idx+ 1) << 1) - 1]) < 0) {
                    exchange(idx, (idx + 1) << 1);
                    idx = (idx + 1) << 1;
                }
                else {
                    exchange(idx, ((idx + 1) << 1) - 1);
                    idx = ((idx + 1) << 1) - 1;
                }
            }
            else if ((idx + 1) << 1 < size && compare(container[(idx + 1) << 1], container[idx]) < 0) {
                exchange(idx, (idx + 1) << 1);
                idx = (idx + 1) << 1;
            }
            else {
                break;
            }
        }
    }

    private void exchange(int i, int j) {
        Key temp = container[i];
        container[i] = container[j];
        container[j] = temp;
    }

    private void resize(int newcapacity) {  // 重置container容量
        assert newcapacity >= size;
        Key[] temp = (Key[]) new Object[newcapacity];
        for (int i = 0; i < size; i++) {
            temp[i] = container[i];
        }
        container = temp;
    }

    private int compare(Key key1, Key key2) {
        if (comparator != null) {
            return comparator.compare(key1, key2);
        }
        return ((Comparable<Key>) key1).compareTo(key2);
    }

    @Override
    public Iterator<Key> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<Key> {
        private final PQ<Key> temp;

        private MyIterator() {
            temp = new PQ<>(size, comparator);
            for (int i = 0; i < size; i++) {
                temp.idxChange(i, container[i], false);
            }
        }

        public boolean hasNext() {
            return !temp.isEmpty();
        }

        public Key next() {
            if (temp.isEmpty()) {
                return null;
            }
            return temp.poll();
        }

        public void remove() {}
    }
}

class Solution {
    public int trapRainWater(int[][] heights) {
        int width = heights.length;
        int length = heights[0].length;
        // 用一个used数组来标记该位置是否被访问过
        boolean[][] used = new boolean[width][length];
        // 优先队列中存放三元组 [x,y,h] 坐标和高度
        PQ<int[]> pq = new PQ<>((o1, o2) -> o1[2] - o2[2]);

        // 放入最外圈
        for (int i = 0; i < length - 1; i++) {
            pq.add(new int[] {0, i, heights[0][i]});
            used[0][i] = true;
        }
        for (int i = 0; i < width - 1; i++) {
            pq.add(new int[] {i, length - 1, heights[i][length - 1]});
            used[i][length - 1] = true;
        }
        for (int i = 1; i < length; i++) {
            pq.add(new int[] {width - 1, i, heights[width - 1][i]});
            used[width - 1][i] = true;
        }
        for (int i = 1; i < width; i++) {
            pq.add(new int[] {i, 0, heights[i][0]});
            used[i][0] = true;
        }

        int ans = 0;
        // 方向数组，把dx和dy压缩成一维来做
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!pq.isEmpty()) {
            // 圈中最小的高度作为桶高
            int[] poll = pq.poll();
            // 看一下周围四个方向，没访问过的话能不能往里灌水
            for (int k = 0; k < 4; k++) {
                int row = poll[0] + dirs[k];
                int column = poll[1] + dirs[k + 1];
                // 如果位置合法且没访问过
                if (row >= 0 && row < width && column >= 0 && column < length && !used[row][column]) {
                    // 如果外围这一圈中最小的比当前这个还高，那就说明能往里面灌水啊
                    if (poll[2] > heights[row][column]) {
                        ans += poll[2] - heights[row][column];
                    }
                    // 如果灌水则高度为灌水后的高度，如果没灌水即为该点原本高度
                    pq.add(new int[]{row, column, Math.max(heights[row][column], poll[2])});
                    used[row][column] = true;
                }
            }
        }
        return ans;
    }
}
