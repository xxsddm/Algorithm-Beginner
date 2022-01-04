//给定一个正整数 n，找出小于或等于 n 的非负整数中，其二进制表示不包含 连续的1 的个数。 
//
// 示例 1: 
//
// 输入: 5
//输出: 5
//解释: 
//下面是带有相应二进制表示的非负整数<= 5：
//0 : 0
//1 : 1
//2 : 10
//3 : 11
//4 : 100
//5 : 101
//其中，只有整数3违反规则（有两个连续的1），其他5个满足规则。 
//
// 说明: 1 <= n <= 10⁹ 
// Related Topics 动态规划 👍 139 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int ans = 0;
    // 二进制长度, 首端数字(0, 1) -> 不含连续1的数字数量
    int[][] dp;

    public int findIntegers(int n) {    // DFS(极速)
        int temp = 1, count = 0;    // count记录二进制数字长度
        while (temp <= n) {
            temp <<= 1;
            count++;
        }
        dp = new int[count + 1][2];
        dp[0][0] = 1;
        for (int length = 1; length <= count; length++) {
            dp[length][0] = dp[length - 1][0] + dp[length - 1][1];
            dp[length][1] = dp[length - 1][0];
        }
        dfs(n);
        return ans;
    }

    private void dfs(int n) {
        int temp = 1, count = 0;    // count记录二进制数字长度
        while (temp <= n) {
            temp <<= 1;
            count++;
        }
        ans += dp[count][0];    // 同等二进制长度0为首端的数字一定不超过n, 直接加入
        // 长度为count(即n的二进制长度)时, 数字不能超过n, 因此从高位(左2位开始)扫描第一个取1的数
        int length = count - 1, num = length == 0 ? 0 : 1 << (length - 1);
        while (num > 0 && (n & num) == 0) {
            num >>= 1;
            length--;
        }
        if (num > 0) {  // 找到1
            if (length == count - 1) {  // 若1在第2位, 则直接加入length长度情况下0开头的种类
                ans += dp[length][0];
            }
            else {  // 若1不在第2位, 则递归实现, 只考虑1及后续数字组成所包含的种类数量
                int xor = 1 << (count - 1);
                for (int i = count - 1; i >= length; i--) {
                    if ((n & xor) != 0) {
                        n ^= xor;
                    }
                    xor >>= 1;
                }
                dfs(n);
            }
            return;
        }
        ans += 1;   // 没找到1(则长度为count的只有n本身)
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    int count = 2;
    int limit;

    public int findIntegers(int n) {    // DFS(很慢)
        limit = n;
        dfs(1);
        return count;
    }

    private void dfs(int num) {
        num <<= 1;
        if (num <= limit) {
            count++;
            dfs(num);
            if ((num & 2) == 0 && num < limit) {
                count++;
                dfs(num | 1);
            }
        }
    }
}

class Solution {
    public int findIntegers(int n) {    // BFS(超时, 应该是LinkedList出入队列太慢)
        int count = 2;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(1);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int num = queue.poll() << 1;
                if (num <= n) {
                    count++;
                    queue.add(num);
                    if ((num & 2) == 0 && num < n) {
                        count++;
                        queue.add(num | 1);
                    }
                }
            }
        }
        return count;
    }
}
