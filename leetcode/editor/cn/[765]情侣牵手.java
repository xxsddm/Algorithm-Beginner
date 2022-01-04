//N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。 一次交换可选择任意两人，让他们站起来交
//换座位。 
//
// 人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2, 2N-1)
//。 
//
// 这些情侣的初始座位 row[i] 是由最初始坐在第 i 个座位上的人决定的。 
//
// 示例 1: 
//
// 
//输入: row = [0, 2, 1, 3]
//输出: 1
//解释: 我们只需要交换row[1]和row[2]的位置即可。
// 
//
// 示例 2: 
//
// 
//输入: row = [3, 2, 0, 1]
//输出: 0
//解释: 无需交换座位，所有的情侣都已经可以手牵手了。
// 
//
// 说明: 
//
// 
// len(row) 是偶数且数值在 [4, 60]范围内。 
// 可以保证row 是序列 0...len(row)-1 的一个全排列。 
// 
// Related Topics 贪心 深度优先搜索 广度优先搜索 并查集 图 👍 281 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int[] UF;
    int[] size;

    public int minSwapsCouples(int[] row) { // 并查集
        int numCP = row.length >> 1, count = 0;    // 共有numCP=length/2对
        UF = new int[numCP];
        size = new int[numCP];
        for (int i = 0; i < numCP; i++) {   // 初始默认正确连接
            UF[i] = i;
        }
        for (int i = 1; i <= numCP; i++) {  // 用row数据更新连接情况(num对应CP索引为num/2-1)
            union(row[(i << 1) - 2] >> 1, row[(i << 1) - 1] >> 1);
        }
        for (int i = 0; i < numCP; i++) {
            if (i != find(i)) { // 计算(错误的CP对数-1), 即需修改的次数
                count++;
            }
        }
        return count;
    }

    private void union(int idx1, int idx2) {
        int set1 = find(idx1), set2 = find(idx2);
        if (set1 == set2) {
            return;
        }
        if (size[set1] < size[set2]) {
            UF[set1] = set2;
            size[set2] += size[set1];
        }
        else {
            UF[set2] = set1;
            size[set1] += size[set2];
        }
    }

    private int find(int idx) {
        while (UF[idx] != idx) {
            UF[idx] = UF[UF[idx]];
            idx = UF[idx];
        }
        return idx;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
