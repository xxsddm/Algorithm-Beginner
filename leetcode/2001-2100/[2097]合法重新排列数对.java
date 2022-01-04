//给你一个下标从 0 开始的二维整数数组 pairs ，其中 pairs[i] = [starti, endi] 。如果 pairs 的一个重新排列，满足对每
//一个下标 i （ 1 <= i < pairs.length ）都有 endi-1 == starti ，那么我们就认为这个重新排列是 pairs 的一个 合法
//重新排列 。 
//
// 请你返回 任意一个 pairs 的合法重新排列。 
//
// 注意：数据保证至少存在一个 pairs 的合法重新排列。 
//
// 
//
// 示例 1： 
//
// 
//输入：pairs = [[5,1],[4,5],[11,9],[9,4]]
//输出：[[11,9],[9,4],[4,5],[5,1]]
//解释：
//输出的是一个合法重新排列，因为每一个 endi-1 都等于 starti 。
//end0 = 9 == 9 = start1 
//end1 = 4 == 4 = start2
//end2 = 5 == 5 = start3
// 
//
// 示例 2： 
//
// 
//输入：pairs = [[1,3],[3,2],[2,1]]
//输出：[[1,3],[3,2],[2,1]]
//解释：
//输出的是一个合法重新排列，因为每一个 endi-1 都等于 starti 。
//end0 = 3 == 3 = start1
//end1 = 2 == 2 = start2
//重新排列后的数组 [[2,1],[1,3],[3,2]] 和 [[3,2],[2,1],[1,3]] 都是合法的。
// 
//
// 示例 3： 
//
// 
//输入：pairs = [[1,2],[1,3],[2,1]]
//输出：[[1,2],[2,1],[1,3]]
//解释：
//输出的是一个合法重新排列，因为每一个 endi-1 都等于 starti 。
//end0 = 2 == 2 = start1
//end1 = 1 == 1 = start2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= pairs.length <= 10⁵ 
// pairs[i].length == 2 
// 0 <= starti, endi <= 10⁹ 
// starti != endi 
// pairs 中不存在一模一样的数对。 
// 至少 存在 一个合法的 pairs 重新排列。 
// 
// Related Topics 深度优先搜索 图 欧拉回路 👍 21 👎 0

import java.util.HashMap;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int idx;
    int[][] ans;
    HashMap<Integer, LinkedList<Integer>> next = new HashMap<>();

    public int[][] validArrangement(int[][] pairs) {
        HashMap<Integer, Integer> outdegree = new HashMap<>(), indegree = new HashMap<>();
        for (int[] pair: pairs) {
            outdegree.put(pair[0], outdegree.getOrDefault(pair[0], 0) + 1);
            indegree.put(pair[1], indegree.getOrDefault(pair[1], 0) + 1);
            if (next.get(pair[0]) == null) {
                next.put(pair[0], new LinkedList<>());
            }
            next.get(pair[0]).add(pair[1]);
        }
        int start = pairs[0][0];
        idx = pairs.length - 1;
        ans = new int[pairs.length][2];
        for (int k: outdegree.keySet()) {
            int v = outdegree.get(k);
            if (!indegree.containsKey(k) || v == indegree.get(k) + 1) {
                start = k;
                break;
            }
        }
        dfs(start);
        return ans;
    }

    void dfs(int node) {
        if (!next.containsKey(node)) {
            return;
        }
        LinkedList<Integer> temp = next.get(node);
        while (!temp.isEmpty()) {
            int nextNode = temp.pollFirst();
            dfs(nextNode);
            ans[idx--] = new int[] {node, nextNode};
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)
