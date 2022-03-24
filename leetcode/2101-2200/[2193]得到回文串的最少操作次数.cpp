//给你一个只包含小写英文字母的字符串 s 。 
//
// 每一次 操作 ，你可以选择 s 中两个 相邻 的字符，并将它们交换。 
//
// 请你返回将 s 变成回文串的 最少操作次数 。 
//
// 注意 ，输入数据会确保 s 一定能变成一个回文串。 
//
// 
//
// 示例 1： 
//
// 输入：s = "aabb"
//输出：2
//解释：
//我们可以将 s 变成 2 个回文串，"abba" 和 "baab" 。
//- 我们可以通过 2 次操作得到 "abba" ："aabb" -> "abab" -> "abba" 。
//- 我们可以通过 2 次操作得到 "baab" ："aabb" -> "abab" -> "baab" 。
//因此，得到回文串的最少总操作次数为 2 。
// 
//
// 示例 2： 
//
// 输入：s = "letelt"
//输出：2
//解释：
//通过 2 次操作从 s 能得到回文串 "lettel" 。
//其中一种方法是："letelt" -> "letetl" -> "lettel" 。
//其他回文串比方说 "tleelt" 也可以通过 2 次操作得到。
//可以证明少于 2 次操作，无法得到回文串。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 2000 
// s 只包含小写英文字母。 
// s 可以通过有限次操作得到一个回文串。 
// 
// Related Topics 贪心 树状数组 双指针 字符串 👍 24 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int *nums, *aux;
    deque<int> container[26];

    int minMovesToMakePalindrome(string &word) {    // 贪心分配位置, 归并排序
        int length = (int) word.size();
        nums = new int[length], aux = new int[length];
        for (int i = 0; i < length; i++) {
            container[word[i] - 'a'].push_back(i);
        }
        memset(nums, -1, sizeof(int) * (length));
        priority_queue<int, vector<int>, greater<>> pq;
        for (int i = 0, mid = length >> 1; i < length; i++) {
            if (nums[i] != -1) {
                continue;
            }
            int idx = word[i] - 'a';
            // 分配该类字符每对前后位置
            // 要求左侧元素尽量靠左
            if (container[idx].size() == 1) {
                nums[i] = mid;
                container[idx].pop_front();
                if (i < mid) {
                    pq.push(i);
                }
            } else {
                int idx1 = container[idx].front(), idx2 = container[idx].back();
                container[idx].pop_front(), container[idx].pop_back();
                if (!pq.empty() && idx1 > pq.top()) {
                    idx1 = pq.top();
                    pq.pop();
                    if (i < mid) {
                        pq.push(i);
                    }
                }
                nums[i] = idx1;
                nums[idx2] = length - 1 - idx1;
                if (idx2 < mid) {
                    pq.push(idx2);
                }
            }
        }
        return mergeSort(0, length - 1);
    }

    int mergeSort(int start, int end) {
        if (start == end) {
            return 0;
        }
        int mid = (start + end) >> 1;
        int ans = mergeSort(start, mid) + mergeSort(mid + 1, end);
        for (int i = start; i <= end; i++) {
            aux[i] = nums[i];
        }
        int idx = start, left = start, right = mid + 1;
        while (true) {
            if (left == mid + 1) {
                while (idx <= end) {
                    nums[idx++] = aux[right++];
                }
                return ans;
            }
            if (right == end + 1) {
                while (idx <= end) {
                    nums[idx++] = aux[left++];
                }
                return ans;
            }
            if (aux[left] <= aux[right]) {
                nums[idx++] = aux[left++];
            } else {
                nums[idx++] = aux[right++];
                ans += mid - left + 1;
            }
        }
    }
};

//leetcode submit region end(Prohibit modification and deletion)
