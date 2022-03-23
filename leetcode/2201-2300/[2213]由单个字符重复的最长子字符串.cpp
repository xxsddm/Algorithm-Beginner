//给你一个下标从 0 开始的字符串 s 。另给你一个下标从 0 开始、长度为 k 的字符串 queryCharacters ，一个下标从 0 开始、长度也是 
//k 的整数 下标 数组 queryIndices ，这两个都用来描述 k 个查询。 
//
// 第 i 个查询会将 s 中位于下标 queryIndices[i] 的字符更新为 queryCharacters[i] 。 
//
// 返回一个长度为 k 的数组 lengths ，其中 lengths[i] 是在执行第 i 个查询 之后 s 中仅由 单个字符重复 组成的 最长子字符串 的
// 长度 。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babacc", queryCharacters = "bcb", queryIndices = [1,3,3]
//输出：[3,3,4]
//解释：
//- 第 1 次查询更新后 s = "bbbacc" 。由单个字符重复组成的最长子字符串是 "bbb" ，长度为 3 。
//- 第 2 次查询更新后 s = "bbbccc" 。由单个字符重复组成的最长子字符串是 "bbb" 或 "ccc"，长度为 3 。
//- 第 3 次查询更新后 s = "bbbbcc" 。由单个字符重复组成的最长子字符串是 "bbbb" ，长度为 4 。
//因此，返回 [3,3,4] 。 
//
// 示例 2： 
//
// 
//输入：s = "abyzz", queryCharacters = "aa", queryIndices = [2,1]
//输出：[2,3]
//解释：
//- 第 1 次查询更新后 s = "abazz" 。由单个字符重复组成的最长子字符串是 "zz" ，长度为 2 。
//- 第 2 次查询更新后 s = "aaazz" 。由单个字符重复组成的最长子字符串是 "aaa" ，长度为 3 。
//因此，返回 [2,3] 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁵ 
// s 由小写英文字母组成 
// k == queryCharacters.length == queryIndices.length 
// 1 <= k <= 10⁵ 
// queryCharacters 由小写英文字母组成 
// 0 <= queryIndices[i] < s.length 
// 
// Related Topics 线段树 数组 字符串 有序集合 👍 11 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int *maxnum, *leftPart, *rightPart;
    string word;

    // 线段树维护区间最值, leftPart, rightPart分别维护区间内左, 右连续最大值
    vector<int> longestRepeating(string &s, string &queryCharacters, vector<int>& queryIndices) {
        word = s;
        int length = (int) s.size();
        maxnum = new int[length << 2];
        leftPart = new int[length << 2];
        rightPart = new int[length << 2];
        memset(maxnum, 0, sizeof(int) * (length << 2));
        memset(leftPart, 0, sizeof(int) * (length << 2));
        memset(rightPart, 0, sizeof(int) * (length << 2));
        build(1, 1, length);
        vector<int> ans(queryCharacters.size());
        for (int i = 0, size = (int) queryCharacters.size(); i < size; i++) {
            change(1, 1, length, queryIndices[i] + 1, queryCharacters[i]);
            ans[i] = maxnum[1];
        }
        return ans;
    }

    void merge(int node, int start, int end) {
        int mid = (start + end) >> 1;
        int nextNode1 = node << 1, nextNode2 = nextNode1 ^ 1;
        leftPart[node] = leftPart[nextNode1], rightPart[node] = rightPart[nextNode2];
        maxnum[node] = max(maxnum[nextNode1], maxnum[nextNode2]);
        if (word[mid - 1] == word[mid]) {
            maxnum[node] = max(maxnum[node], rightPart[nextNode1] + leftPart[nextNode2]);
            if (maxnum[nextNode1] == mid - start + 1) {
                leftPart[node] += leftPart[nextNode2];
            }
            if (maxnum[nextNode2] == end - mid) {
                rightPart[node] += rightPart[nextNode1];
            }
        }
    }

    void build(int node, int start, int end) {
        if (start == end) {
            maxnum[node] = leftPart[node] = rightPart[node] = 1;
            return;
        }
        int mid = (start + end) >> 1;
        int nextNode1 = node << 1, nextNode2 = nextNode1 ^ 1;
        build(nextNode1, start, mid);
        build(nextNode2, mid + 1, end);
        merge(node, start, end);
    }

    void change(int node, int start, int end, int idx, char &c) {
        if (idx < start || idx > end) {
            return;
        }
        if (start == end) {
            word[idx - 1] = c;
            return;
        }
        int mid = (start + end) >> 1;
        int nextNode1 = node << 1, nextNode2 = nextNode1 ^ 1;
        change(nextNode1, start, mid, idx, c);
        change(nextNode2, mid + 1, end, idx, c);
        merge(node, start, end);
    }
};

//leetcode submit region end(Prohibit modification and deletion)
