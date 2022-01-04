//给你两个 从小到大排好序 且下标从 0 开始的整数数组 nums1 和 nums2 以及一个整数 k ，请你返回第 k （从 1 开始编号）小的 nums1
//[i] * nums2[j] 的乘积，其中 0 <= i < nums1.length 且 0 <= j < nums2.length 。
// 
//
// 示例 1： 
//
// 输入：nums1 = [2,5], nums2 = [3,4], k = 2
//输出：8
//解释：第 2 小的乘积计算如下：
//- nums1[0] * nums2[0] = 2 * 3 = 6
//- nums1[0] * nums2[1] = 2 * 4 = 8
//第 2 小的乘积为 8 。
// 
//
// 示例 2： 
//
// 输入：nums1 = [-4,-2,0,3], nums2 = [2,4], k = 6
//输出：0
//解释：第 6 小的乘积计算如下：
//- nums1[0] * nums2[1] = (-4) * 4 = -16
//- nums1[0] * nums2[0] = (-4) * 2 = -8
//- nums1[1] * nums2[1] = (-2) * 4 = -8
//- nums1[1] * nums2[0] = (-2) * 2 = -4
//- nums1[2] * nums2[0] = 0 * 2 = 0
//- nums1[2] * nums2[1] = 0 * 4 = 0
//第 6 小的乘积为 0 。
// 
//
// 示例 3： 
//
// 输入：nums1 = [-2,-1,0,1,2], nums2 = [-3,-1,2,4,5], k = 3
//输出：-6
//解释：第 3 小的乘积计算如下：
//- nums1[0] * nums2[4] = (-2) * 5 = -10
//- nums1[0] * nums2[3] = (-2) * 4 = -8
//- nums1[4] * nums2[0] = 2 * (-3) = -6
//第 3 小的乘积为 -6 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums1.length, nums2.length <= 5 * 10⁴ 
// -10⁵ <= nums1[i], nums2[j] <= 10⁵ 
// 1 <= k <= nums1.length * nums2.length 
// nums1 和 nums2 都是从小到大排好序的。 
// 
// Related Topics 数组 二分查找 👍 13 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int len1, len2;
    long long k;
    vector<int> arr1, arr2;

    long long kthSmallestProduct(vector<int>& nums1, vector<int>& nums2, long long k) { // 二分
        this->k = k;
        if (nums1.size() > nums2.size()) {
            swap(nums1, nums2);
        }
        arr1 = nums1, arr2 = nums2, len1 = (int) nums1.size(), len2 = (int) nums2.size();
        long long left = min((long long) arr1[0] * arr2[0],
                             min((long long) arr1[0] * arr2[len2 - 1],
                                 (long long) arr1[len1 - 1] * arr2[0]));
        long long right = max((long long) arr1[0] * arr2[0],
                              max((long long) arr1[len1 - 1] * arr2[len2 - 1],
                                  max((long long) arr1[0] * arr2[len2 - 1],
                                      (long long) arr1[len1 - 1] * arr2[0])));
        while (left <= right) {
            long long mid = (left + right) / 2;
            if (check(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    bool check(long long target) {
        long long count = 0;
        for (int i = 0; i < len1 && count < k; i++) {
            if (arr1[i] != 0) {
                long long temp = target / arr1[i];
                if (arr1[i] < 0) {
                    if (target < 0 && temp * arr1[i] != target) {
                        temp++;
                    }
                    count += len2 - 1 - bisect(temp, true);
                } else {
                    if (target < 0 && temp * arr1[i] != target) {
                        temp--;
                    }
                    count += bisect(temp, false);
                }
            } else if (target >= 0){
                count += len2;
            }
        }
        return count >= k;
    }

    int bisect(long long target, bool equal) {
        int left = 0, right = len2 - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (arr2[mid] > target || equal && arr2[mid] == target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return equal ? right : left;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
