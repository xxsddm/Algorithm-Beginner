//两位玩家分别扮演猫和老鼠，在一张 无向 图上进行游戏，两人轮流行动。 
//
// 图的形式是：graph[a] 是一个列表，由满足 ab 是图中的一条边的所有节点 b 组成。 
//
// 老鼠从节点 1 开始，第一个出发；猫从节点 2 开始，第二个出发。在节点 0 处有一个洞。 
//
// 在每个玩家的行动中，他们 必须 沿着图中与所在当前位置连通的一条边移动。例如，如果老鼠在节点 1 ，那么它必须移动到 graph[1] 中的任一节点。 
//
// 此外，猫无法移动到洞中（节点 0）。 
//
// 然后，游戏在出现以下三种情形之一时结束： 
//
// 
// 如果猫和老鼠出现在同一个节点，猫获胜。 
// 如果老鼠到达洞中，老鼠获胜。 
// 如果某一位置重复出现（即，玩家的位置和移动顺序都与上一次行动相同），游戏平局。 
// 
//
// 给你一张图 graph ，并假设两位玩家都都以最佳状态参与游戏： 
//
// 
// 如果老鼠获胜，则返回 1； 
// 如果猫获胜，则返回 2； 
// 如果平局，则返回 0 。 
// 
// 
//
// 示例 1： 
//
// 
//输入：graph = [[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
//输出：0
// 
//
// 示例 2： 
//
// 
//输入：graph = [[1,3],[0],[3],[0,2]]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 3 <= graph.length <= 50 
// 1 <= graph[i].length < graph.length 
// 0 <= graph[i][j] < graph.length 
// graph[i][j] != i 
// graph[i] 互不相同 
// 猫和老鼠在游戏中总是移动 
// 
// Related Topics 广度优先搜索 图 记忆化搜索 数学 动态规划 博弈 👍 165 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int length, limit;
    int[][] container;
    int[][][] dp;

    public int catMouseGame(int[][] graph) {    // 记忆化搜索
        length = graph.length;
        limit = length * 2 - 2;
        container = graph;
        dp = new int[length][length][limit];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                for (int k = 0; k < limit; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        return dfs(1, 2, 0);
    }

    int dfs(int jerry, int tom, int turn) {
        if (jerry == 0) {
            return 1;
        }
        if (jerry == tom) {
            return 2;
        }
        int player = (turn & 1) == 0 ? 1 : 2;
        if (turn == limit) {
            return 0;
        }
        if (dp[jerry][tom][turn] != -1) {
            return dp[jerry][tom][turn];
        }
        boolean equal = false;
        int[] next = player == 1 ? container[jerry] : container[tom];
        if (player == 1) {
            for (int nextNode : next) {
                int temp = dfs(nextNode, tom, turn + 1);
                if (temp == 1) {
                    dp[jerry][tom][turn] = 1;
                    return 1;
                }
                if (temp == 0) {
                    equal = true;
                }
            }
        } else {
            for (int nextNode : next) {
                if (nextNode == 0) {
                    continue;
                }
                int temp = dfs(jerry, nextNode, turn + 1);
                if (temp == 2) {
                    dp[jerry][tom][turn] = 2;
                    return 2;
                }
                if (temp == 0) {
                    equal = true;
                }
            }
        }
        dp[jerry][tom][turn] = equal ? 0 : (3 - player);
        return dp[jerry][tom][turn];
    }
}

//leetcode submit region end(Prohibit modification and deletion)
