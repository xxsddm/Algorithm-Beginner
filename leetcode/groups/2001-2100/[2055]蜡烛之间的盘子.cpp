//给你一个长桌子，桌子上盘子和蜡烛排成一列。给你一个下标从 0 开始的字符串 s ，它只包含字符 '*' 和 '|' ，其中 '*' 表示一个 盘子 ，'|
//' 表示一支 蜡烛 。 
//
// 同时给你一个下标从 0 开始的二维整数数组 queries ，其中 queries[i] = [lefti, righti] 表示 子字符串 s[
//lefti...righti] （包含左右端点的字符）。对于每个查询，你需要找到 子字符串中 在 两支蜡烛之间 的盘子的 数目 。如果一个盘子在 子字符串中 左边和右边
// 都 至少有一支蜡烛，那么这个盘子满足在 两支蜡烛之间 。 
//
// 
// 比方说，s = "||**||**|*" ，查询 [3, 8] ，表示的是子字符串 "*||**|" 。子字符串中在两支蜡烛之间的盘子数目为 2 ，子字符
//串中右边两个盘子在它们左边和右边 都 至少有一支蜡烛。 
// 
//
// 请你返回一个整数数组 answer ，其中 answer[i] 是第 i 个查询的答案。 
//
// 
//
// 示例 1: 
//
// 
//
// 输入：s = "**|**|***|", queries = [[2,5],[5,9]]
//输出：[2,3]
//解释：
//- queries[0] 有两个盘子在蜡烛之间。
//- queries[1] 有三个盘子在蜡烛之间。
// 
//
// 示例 2: 
//
// 
//
// 输入：s = "***|**|*****|**||**|*", queries = [[1,17],[4,5],[14,17],[5,11],[15,16
//]]
//输出：[9,0,0,0,0]
//解释：
//- queries[0] 有 9 个盘子在蜡烛之间。
//- 另一个查询没有盘子在蜡烛之间。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= s.length <= 10⁵ 
// s 只包含字符 '*' 和 '|' 。 
// 1 <= queries.length <= 10⁵ 
// queries[i].length == 2 
// 0 <= lefti <= righti < s.length 
// 
// 👍 5 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    vector<int> platesBetweenCandles(string s, vector<vector<int>>& queries) {
        int length = (int) s.length(), start = 0, end = length - 1;
        vector<int> ans(queries.size());
        while (start < length && s[start] == '*') {
            start++;
        }
        if (start == length) {
            return ans;
        }
        while (s[end] == '*') {
            end--;
        }
        int count = 0, preCount = 0, blockCount = 0, nextStart = 0;
        // pre记录该索引及之前的蜡烛间盘子数量, block记录该索引所在盘子区域的盘子数量
        vector<int> pre(length), block(length);
        for (int i = start; i < length; i++) {
            if (s[i] == '*') {
                count++;
                pre[i] = preCount;
            }
            else {
                pre[i] = count;
                preCount = count;
            }
        }
        for (int i = start; i <= end; i++) {
            if (s[i] == '*') {
                if (blockCount == 0) {
                    nextStart = i;
                }
                blockCount++;
            }
            else if (blockCount != 0) {
                for (int j = nextStart; j < i; j++) {
                    block[j] = blockCount;
                }
                blockCount = 0;
            }
        }
        for (int i = 0; i < queries.size(); i++) {
            vector<int>& query = queries[i];
            // 末端对应盘子数 - 首端对应盘子数
            ans[i] = pre[query[1]] - pre[query[0]];
            // 若首端和末端不在同一个盘子区域,需额外减去首端区域盘子数
            if (ans[i] != 0) {
                ans[i] -= block[query[0]];
            }
        }
        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
