public class QuickFind {    // 快速查找并查集(合并很慢需要遍历整个数组)(基于算法4)
    private final int[] root;   // 当前索引 -> 相应根节点的索引
    private int count;

    public QuickFind(int num) {
        root = new int[num];
        count = num;
        for (int i = 0; i < num; i++) {
            root[i] = i;
        }
    }

    public int find(int idx) {
        return root[idx];
    }

    public void union(int idx1, int idx2) {
        int set1 = find(idx1), set2 = find(idx2);
        if (set1 != set2) {
            count--;
        }
        else {
            return;
        }
        for (int i = 0; i < root.length; i++) {
            if (root[i] == set2) {
                root[i] = set1;
            }
        }
    }

    public boolean connected(int idx1, int idx2) {
        return find(idx1) == find(idx2);
    }

    public int count() {
        return count;
    }
}
