//给你一个整数数组 arr ，你一开始在数组的第一个元素处（下标为 0）。 
//
// 每一步，你可以从下标 i 跳到下标： 
//
// 
// i + 1 满足：i + 1 < arr.length 
// i - 1 满足：i - 1 >= 0 
// j 满足：arr[i] == arr[j] 且 i != j 
// 
//
// 请你返回到达数组最后一个元素的下标处所需的 最少操作次数 。 
//
// 注意：任何时候你都不能跳到数组外面。 
//
// 
//
// 示例 1： 
//
// 输入：arr = [100,-23,-23,404,100,23,23,23,3,404]
//输出：3
//解释：那你需要跳跃 3 次，下标依次为 0 --> 4 --> 3 --> 9 。下标 9 为数组的最后一个元素的下标。
// 
//
// 示例 2： 
//
// 输入：arr = [7]
//输出：0
//解释：一开始就在最后一个元素处，所以你不需要跳跃。
// 
//
// 示例 3： 
//
// 输入：arr = [7,6,9,6,9,6,9,7]
//输出：1
//解释：你可以直接从下标 0 处跳到下标 7 处，也就是数组的最后一个元素处。
// 
//
// 示例 4： 
//
// 输入：arr = [6,1,9]
//输出：2
// 
//
// 示例 5： 
//
// 输入：arr = [11,22,7,7,7,7,7,7,7,22,13]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 5 * 10^4 
// -10^8 <= arr[i] <= 10^8 
// 
// Related Topics 广度优先搜索 数组 哈希表 👍 116 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int minJumps(vector<int>& arr) {    // BFS
        int length = (int) arr.size(), count = 0;
        if (length == 1) {
            return 0;
        }
        bool visited[length];
        queue<int> container;
        unordered_map<int, vector<int>> cluster;
        memset(visited, false, sizeof(visited));
        for (int i = 0; i < length; i++) {
            cluster[arr[i]].push_back(i);
        }

        visited[0] = true;
        container.push(0);
        while (true) {
            count++;
            for (int i = 0, size = (int) container.size(); i < size; i++) {
                int node = container.front();
                container.pop();
                if (node - 1 >= 0 && !visited[node - 1]) {
                    visited[node - 1] = true;
                    container.push(node - 1);
                }
                if (node + 1 < length && !visited[node + 1]) {
                    if (node + 1 == length - 1) {
                        return count;
                    }
                    visited[node + 1] = true;
                    container.push(node + 1);
                }
                if (!cluster.count(arr[node])) {
                    continue;
                }
                for (int &nextNode : cluster[arr[node]]) {
                    if (visited[nextNode]) {
                        continue;
                    }
                    visited[nextNode] = true;
                    if (nextNode == length - 1) {
                        return count;
                    }
                    container.push(nextNode);
                }
                cluster.erase(arr[node]);
            }
        }
    }
};

//leetcode submit region end(Prohibit modification and deletion)
