//在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。 
//
// 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。 
//
// 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。 
//
// 说明: 
//
// 
// 如果题目有解，该答案即为唯一答案。 
// 输入数组均为非空数组，且长度相同。 
// 输入数组中的元素均为非负数。 
// 
//
// 示例 1: 
//
// 输入: 
//gas  = [1,2,3,4,5]
//cost = [3,4,5,1,2]
//
//输出: 3
//
//解释:
//从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
//开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
//开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
//开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
//开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
//开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
//因此，3 可为起始索引。 
//
// 示例 2: 
//
// 输入: 
//gas  = [2,3,4]
//cost = [3,4,3]
//
//输出: -1
//
//解释:
//你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
//我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
//开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
//开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
//你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
//因此，无论怎样，你都不可能绕环路行驶一周。 
// Related Topics 贪心 数组 👍 713 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int ans = -1, start = 0;
        int[] before = {0, 0, gas[0] - cost[0]};    // 保存前一段失败的经历(起点, 行程, 该行程失败时剩余汽油)
        while (start < gas.length) {
            int rest = 0;
            int distance = 0;
            while (distance <= gas.length) {
                int idx = (start + distance) % gas.length;
                rest += gas[idx] - cost[idx];
                if (idx == before[0]) {         // 走到上一段失败经历的起点, 直接继承, 跳过其经历
                    distance += before[1];      // 因为下一段由上一段结束点(未到达点)的下一个点开始, distance不会超过gas.length
                    rest += before[2] - (gas[idx] - cost[idx]);
                }

                if (rest < 0) {
                    before[0] = start;
                    before[1] = distance;
                    before[2] = rest;

// start~start+distance-1(从start开始)累和均为非负, 到start+distance转为负数, 则从其中间部分开始也一定不可能通过start+distance, 跳过
// >= gas.length则说明可以走到末端点
// 但0~start-1(start之前已走过)均不可能实现循环路径
// start+1~gas.length-1<=start+distance部分不可能有起点能成立, 因为start~start+distance全程非负
                    start += distance + 1;
                    break;
                }
                if (distance == gas.length) {
                    ans = start;
                    return ans;
                }
                distance++;
            }
        }
        return ans;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int ans = -1, rest, idx, start = 0;
        while (start < gas.length) {
            rest = gas[start] - cost[start];
            if (rest < 0) {
                start++;
                continue;
            }
            for (int distance = 1; distance <= gas.length; distance++) {
                idx = (start + distance) % gas.length;
                rest = rest + gas[idx] - cost[idx];
                if (rest < 0) {

                    // start~start+distance-1(从start开始)累和均为非负, 到start+distance转为负数, 则从其中间部分开始也一定不可能通过start+distance, 跳过
                    // >= gas.length则说明可以走到末端点
                    // 但0~start-1(start之前已走过)均不可能实现循环路径
                    // start+1~gas.length-1<=start+distance部分不可能有起点能成立, 因为start~start+distance全程非负
                    start += distance + 1;
                    break;
                }
                if (distance == gas.length) {
                    ans = start;
                    return ans;
                }
            }
        }
        return ans;
    }
}
