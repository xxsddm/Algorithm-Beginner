//给你一个正整数 n ，请你找出符合条件的最小整数，其由重新排列 n 中存在的每位数字组成，并且其值大于 n 。如果不存在这样的正整数，则返回 -1 。 
//
// 注意 ，返回的整数应当是一个 32 位整数 ，如果存在满足题意的答案，但不是 32 位整数 ，同样返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 12
//输出：21
// 
//
// 示例 2： 
//
// 
//输入：n = 21
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 2³¹ - 1 
// 
// Related Topics 数学 双指针 字符串 👍 168 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int nextGreaterElement(int n) {
        if (n == INT32_MAX) {
            return -1;
        }
        vector<int> container(to_string(n).length());
        for (int i = (int) container.size() - 1; i >= 0; i--) {
            container[i] = n % 10;
            n /= 10;
        }
        int idx = (int) container.size() - 2;
        while (idx >= 0) {
            if (container[idx] < container[idx + 1]) {
                break;
            }
            idx--;
        }
        if (idx == -1) {
            return -1;
        }
        int temp = container[idx];
        int change = bisect(idx, container);
        container[idx] = container[change];
        container[change] = temp;
        // idx+1~container.length-1需反转
        int left = idx + 1, right = (int) container.size() - 1;
        while (left < right) {
            temp = container[left];
            container[left++] = container[right];
            container[right--] = temp;
        }
        // 可以不用long
        int ans = 0, limit = INT32_MAX;
        for (int num : container) {
            if (ans > (limit - num) / 10) {
                return -1;
            }
            ans = ans * 10 + num;
        }
        return ans;
    }

    int bisect(int idx, vector<int>& container) {   // 二分搜索比idx元素大的idx最右侧数字
        int pivot = container[idx];
        int left = idx + 1, right = (int) container.size() - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (container[mid] > pivot) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return right;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
