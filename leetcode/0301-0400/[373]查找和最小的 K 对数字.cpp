//给定两个以 升序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。
//
// 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
//
// 请找到和最小的 k 个数对 (u1,v1), (u2,v2) ... (uk,vk) 。
//
//
//
// 示例 1:
//
//
//输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
//输出: [1,2],[1,4],[1,6]
//解释: 返回序列中的前 3 对数：
//     [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
//
//
// 示例 2:
//
//
//输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
//输出: [1,1],[1,1]
//解释: 返回序列中的前 2 对数：
//     [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
//
//
// 示例 3:
//
//
//输入: nums1 = [1,2], nums2 = [3], k = 3
//输出: [1,3],[2,3]
//解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
//
//
//
//
// 提示:
//
//
// 1 <= nums1.length, nums2.length <= 10⁵
// -10⁹ <= nums1[i], nums2[i] <= 10⁹
// nums1 和 nums2 均为升序排列
// 1 <= k <= 1000
//
// Related Topics 数组 堆（优先队列） 👍 310 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int length, k;
    vector<int> arr1, arr2;

    vector<vector<int>> kSmallestPairs(vector<int>& nums1, vector<int>& nums2, int k) { // 二分
        int len1 = min((int) nums1.size(), k), len2 = min((int) nums2.size(), k);
        vector<vector<int>> ans, container;
        length = min(k, len1 * len2), this->k = k;
        arr1 = nums1, arr2 = nums2;
        int left = nums1[0] + nums2[0], right = nums1[len1 - 1] + nums2[len2 - 1];
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        int target = left, count = 0;
        for (int &num1 : arr1) {
            for (int &num2 : arr2) {
                if (num1 + num2 > target) {
                    break;
                }
                if (num1 + num2 == target) {
                    container.push_back({num1, num2});
                    continue;
                }
                ans.push_back({num1, num2});
                if (++count == length) {
                    return ans;
                }
            }
        }
        while (count++ < length) {
            ans.push_back(container.back());
            container.pop_back();
        }
        return ans;
    }

    bool check(int sum) {
        int count = 0;
        for (int &num : arr1) {
            count += bisect(sum - num);
            if (count >= length) {
                return true;
            }
        }
        return false;
    }

    int bisect(int target) {
        int left = 0, right = min((int) arr2.size() - 1, k - 1);
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (target < arr2[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
