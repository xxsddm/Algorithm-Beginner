public class CompressedQuickUnion { // 路径压缩快速合并并查集(基于算法4)(LeetCode 695, 778, 1631题测试通过)
    private int count;
    private int[] parent;      // 当前索引 -> 相应父节点的索引
    private int[] size;

    public CompressedQuickUnion(int num) {
        count = num;
        parent = new int[num];
        size = new int[num];
        for (int i = 0; i < num; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int idx) {  // 找到节点所在集合的根节点
        while (idx != parent[idx]) {
            parent[idx] = parent[parent[idx]];  // 压缩路径(合并后查找高度大于1的节点则修改当前父节点指向, 直接指向根节点)
            idx = parent[idx];
        }

//         QuickUnion(未压缩路径版本)
//         while (idx != parent[idx]) {
//             idx = parent[idx];
//         }

        return idx;
    }

    public void union(int idx1, int idx2) {
        int set1 = find(idx1), set2 = find(idx2);   // 对根节点合并
        if (set1 != set2) {
            count--;
        }
        else {
            return;
        }
        if (size[set1] < size[set2]) {  // 小集合连接到大集合作为子树
            parent[set1] = set2;
            size[set2] += size[set1];
        }
        else {
            parent[set2] = set1;
            size[set1] += size[set2];
        }
    }

    public boolean connected(int idx1, int idx2) {
        return find(idx1) == find(idx2);
    }

    public int count() {
        return count;
    }

    public int size(int idx) {
        return size[find(idx)];
    }
}
