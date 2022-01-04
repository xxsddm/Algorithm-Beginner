//这里有 n 个航班，它们分别从 1 到 n 进行编号。 
//
// 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 
//firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。 
//
// 请你返回一个长度为 n 的数组 answer，其中 answer[i] 是航班 i 上预订的座位总数。 
//
// 
//
// 示例 1： 
//
// 
//输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
//输出：[10,55,45,25,25]
//解释：
//航班编号        1   2   3   4   5
//预订记录 1 ：   10  10
//预订记录 2 ：       20  20
//预订记录 3 ：       25  25  25  25
//总座位数：      10  55  45  25  25
//因此，answer = [10,55,45,25,25]
// 
//
// 示例 2： 
//
// 
//输入：bookings = [[1,2,10],[2,2,15]], n = 2
//输出：[10,25]
//解释：
//航班编号        1   2
//预订记录 1 ：   10  10
//预订记录 2 ：       15
//总座位数：      10  25
//因此，answer = [10,25]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 2 * 10⁴ 
// 1 <= bookings.length <= 2 * 10⁴ 
// bookings[i].length == 3 
// 1 <= firsti <= lasti <= n 
// 1 <= seatsi <= 10⁴ 
// 
// Related Topics 数组 前缀和 👍 166 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {  // 差分(转换成累和问题, 扫描的改进版)
        int[] ans = new int[n];
        for (int[] book: bookings) {
            ans[book[0] - 1] += book[2];
            if (book[1] < n) {
                // 不可累加的部分, 下一个索引处扣除
                ans[book[1]] -= book[2];
            }
        }
        for (int i = 0; i < n - 1; i++) {
            ans[i + 1] += ans[i]
        }
        return ans;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {  // 扫描
        int idx = 0, cumsum = 0;
        int[] ans = new int[n];
        int[][] container = new int[2 * n][2];
        for (int[] booking: bookings) {
            container[idx++] = new int[] {booking[0] - 1, booking[2]};
            container[idx++] = new int[] {booking[1], - booking[2]};
        }
        Arrays.sort(container, Comparator.comparing(o -> o[0]));
        idx = 0;
        for (int i = 0; i < n; i++) {
            while (idx < 2 * n && container[idx][0] == i) {
                cumsum += container[idx++][1];
            }
            ans[i] = cumsum;
        }
        return ans;
    }
}


class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {  // 硬刚
        int[] container = new int[n];
        for (int[] book: bookings) {
            int start = book[0] - 1, end = book[1] - 1, num = book[2];
            for (int i = start; i <= end; i++) {
                container[i] += num;
            }
        }
        return container;
    }
}
