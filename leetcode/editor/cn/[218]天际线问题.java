//城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。给你所有建筑物的位置和高度，请返回由这些建筑物形成的 天际线 。 
//
// 每个建筑物的几何信息由数组 buildings 表示，其中三元组 buildings[i] = [lefti, righti, heighti] 表示： 
//
//
// 
// lefti 是第 i 座建筑物左边缘的 x 坐标。 
// righti 是第 i 座建筑物右边缘的 x 坐标。 
// heighti 是第 i 座建筑物的高度。 
// 
//
// 天际线 应该表示为由 “关键点” 组成的列表，格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标 进行 排序 。关键点是水平线段的左端点。
//列表中最后一个点是最右侧建筑物的终点，y 坐标始终为 0 ，仅用于标记天际线的终点。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。 
//
// 注意：输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答
//案；三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...] 
//
// 
//
// 示例 1： 
//
// 
//输入：buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
//输出：[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
//解释：
//图 A 显示输入的所有建筑物的位置和高度，
//图 B 显示由这些建筑物形成的天际线。图 B 中的红点表示输出列表中的关键点。 
//
// 示例 2： 
//
// 
//输入：buildings = [[0,2,3],[2,5,3]]
//输出：[[0,3],[5,0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= buildings.length <= 104 
// 0 <= lefti < righti <= 231 - 1 
// 1 <= heighti <= 231 - 1 
// buildings 按 lefti 非递减排序 
// 
// Related Topics 树状数组 线段树 数组 分治 有序集合 扫描线 堆（优先队列） 
// 👍 513 👎 0


import java.util.*;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> getSkyline(int[][] bs) {
        List<List<Integer>> ans = new ArrayList<>();
        List<int[]> pos_height = new ArrayList<>();
        HashMap<Integer, Integer> counter = new HashMap<>();
        PriorityQueue<Integer> container = new PriorityQueue<>();
        counter.put(0, 1);
        container.add(0);

        for (int[] building: bs) {
            pos_height.add(new int[] {building[0], - building[2]});
            pos_height.add(new int[] {building[1], building[2]});
        }

        pos_height.sort((o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            else {
                return o1[1] - o2[1];
            }
        });
        for (int[] pair: pos_height) {
            int pos = pair[0], height = pair[1];
            if (height < 0) {
                counter.put(-height, counter.getOrDefault(-height, 0) + 1);
                container.add(height);
            }
            else {
                counter.put(height, counter.get(height) - 1);
            }
            while (counter.get(-container.peek()) == 0) {
                container.remove();
            }
            int highest = -container.peek();
            List<Integer> temp = new ArrayList<>(2);
            temp.add(pos);
            temp.add(highest);
            if (!ans.isEmpty()) {
                if (highest != ans.get(ans.size() - 1).get(1)) {
                    ans.add(temp);
                }
            }
            else {
                ans.add(temp);
            }
        }
        return ans;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class PQ<Key> implements Iterable<Key> {        // 测试优先队列PQ
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
    public List<List<Integer>> getSkyline(int[][] bs) {
        List<List<Integer>> ans = new ArrayList<>();
        List<int[]> pos_height = new ArrayList<>();
        HashMap<Integer, Integer> counter = new HashMap<>();
        PQ<Integer> container = new PQ<>();
        counter.put(0, 1);
        container.add(0);

        for (int[] building: bs) {
            pos_height.add(new int[] {building[0], - building[2]});
            pos_height.add(new int[] {building[1], building[2]});
        }

        pos_height.sort((o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            else {
                return o1[1] - o2[1];
            }
        });
        for (int[] pair: pos_height) {
            int pos = pair[0], height = pair[1];
            if (height < 0) {
                counter.put(-height, counter.getOrDefault(-height, 0) + 1);
                container.add(height);
            }
            else {
                counter.put(height, counter.get(height) - 1);
            }
            while (counter.get(-container.peek()) == 0) {
                container.poll();
            }
            int highest = -container.peek();
            List<Integer> temp = new ArrayList<>(2);
            temp.add(pos);
            temp.add(highest);
            if (!ans.isEmpty()) {
                if (highest != ans.get(ans.size() - 1).get(1)) {
                    ans.add(temp);
                }
            }
            else {
                ans.add(temp);
            }
        }
        return ans;
    }
}
