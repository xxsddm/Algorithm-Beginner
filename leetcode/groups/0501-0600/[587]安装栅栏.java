//在一个二维的花园中，有一些用 (x, y) 坐标表示的树。由于安装费用十分昂贵，你的任务是先用最短的绳子围起所有的树。只有当所有的树都被绳子包围时，花园才能
//围好栅栏。你需要找到正好位于栅栏边界上的树的坐标。 
//
// 
//
// 示例 1: 
//
// 输入: [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
//输出: [[1,1],[2,0],[4,2],[3,3],[2,4]]
//解释:
//
// 
//
// 示例 2: 
//
// 输入: [[1,2],[2,2],[4,2]]
//输出: [[1,2],[2,2],[4,2]]
//解释:
//
//即使树都在一条直线上，你也需要先用绳子包围它们。
// 
//
// 
//
// 注意: 
//
// 
// 所有的树应当被围在一起。你不能剪断绳子来包围树或者把树分成一组以上。 
// 输入的整数在 0 到 100 之间。 
// 花园至少有一棵树。 
// 所有树的坐标都是不同的。 
// 输入的点没有顺序。输出顺序也没有要求。 
// Related Topics 几何 数组 数学 👍 74 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] outerTrees(int[][] trees) {
        int length = trees.length;
        if (length <= 3) {
            return trees;
        }
        final double EPSILON = 1e-6;
        // 索引i -> trees[i]是否已加入栅栏圈
        boolean[] used = new boolean[length];
        int start = 0;
        int[] firstTree = trees[0];
        for (int i = 1; i < length; i++) {
            int[] tree = trees[i];
            if (tree[0] < firstTree[0]) {
                firstTree = tree;
                start = i;
            }
            else if (tree[0] == firstTree[0]) {
                if (tree[1] < firstTree[1]) {
                    firstTree = tree;
                    start = i;
                }
            }
        }

        // 隐式加入第一个点(左下角点一定加入)
        // 加入第二个点
        int[] benchmark = {0, -1};
        double maxCos = - 1;
        int minDist = Integer.MAX_VALUE, next = 0, count = 1;
        for (int i = 0; i < length; i++) {
            if (i == start) {
                continue;
            }
            int[] vector = {trees[i][0] - firstTree[0], trees[i][1] - firstTree[1]};
            double newCos = cos(benchmark, vector);
            if (Math.abs(newCos - maxCos) < EPSILON) {
                int dist = distSquare(firstTree, trees[i]);
                if (dist < minDist) {
                    minDist = dist;
                    next = i;
                }
            }
            else if (newCos > maxCos) {
                maxCos = newCos;
                minDist = distSquare(firstTree, trees[i]);
                next = i;
            }
        }
        used[next] = true;

        // 向量1:加入的倒数第二个点指向倒数第一个点; 向量2:倒数第一个点指向正在考虑的点
        // 加入后续点(选择向量夹角余弦值最小的,若余弦相同则选择最近点)
        int[] last = trees[next], secondLast = trees[start];
        while (!used[start]) {
            benchmark = new int[]{last[0] - secondLast[0], last[1] - secondLast[1]};
            maxCos = -1;
            minDist = Integer.MAX_VALUE;
            for (int i = 0; i < length; i++) {
                if (used[i]) {
                    continue;
                }
                int[] vector = {trees[i][0] - last[0], trees[i][1] - last[1]};
                double newCos = cos(benchmark, vector);
                if (Math.abs(newCos - maxCos) < EPSILON) {
                    int dist = distSquare(last, trees[i]);
                    if (dist < minDist) {
                        minDist = dist;
                        next = i;
                    }
                } else if (newCos > maxCos) {
                    maxCos = newCos;
                    minDist = distSquare(last, trees[i]);
                    next = i;
                }
            }
            secondLast = last;
            last = trees[next];
            used[next] = true;
            count++;
        }

        int idx = 0;
        int[][] ans = new int[count][];
        for (int i = 0; i < length; i++) {
            if (used[i]) {
                ans[idx++] = trees[i];
            }
        }

        return ans;
    }

    private double cos(int[] vector1, int[] vector2) {  // 计算向量夹角余弦值
        int multiply = vector1[0] * vector2[0] + vector1[1] * vector2[1];
        double mode1 = Math.sqrt(vector1[0] * vector1[0] + vector1[1] * vector1[1]);
        double mode2 = Math.sqrt(vector2[0] * vector2[0] + vector2[1] * vector2[1]);
        return multiply / (mode1 * mode2);
    }

    private int distSquare(int[] vector1, int[] vector2) {  // 计算两点距离的平方
        return (vector1[0] - vector2[0]) * (vector1[0] - vector2[0])
                + (vector1[1] - vector2[1]) * (vector1[1] - vector2[1]);
    }
}

//leetcode submit region end(Prohibit modification and deletion)
