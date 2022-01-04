//给你一个按递增顺序排序的数组 arr 和一个整数 k 。数组 arr 由 1 和若干 素数 组成，且其中所有整数互不相同。 
//
// 对于每对满足 0 < i < j < arr.length 的 i 和 j ，可以得到分数 arr[i] / arr[j] 。 
//
// 那么第 k 个最小的分数是多少呢? 以长度为 2 的整数数组返回你的答案, 这里 answer[0] == arr[i] 且 answer[1] == 
//arr[j] 。 
// 
//
// 示例 1： 
//
// 
//输入：arr = [1,2,3,5], k = 3
//输出：[2,5]
//解释：已构造好的分数,排序后如下所示: 
//1/5, 1/3, 2/5, 1/2, 3/5, 2/3
//很明显第三个最小的分数是 2/5
// 
//
// 示例 2： 
//
// 
//输入：arr = [1,7], k = 1
//输出：[1,7]
// 
//
// 
//
// 提示： 
//
// 
// 2 <= arr.length <= 1000 
// 1 <= arr[i] <= 3 * 10⁴ 
// arr[0] == 1 
// arr[i] 是一个 素数 ，i > 0 
// arr 中的所有数字 互不相同 ，且按 严格递增 排序 
// 1 <= k <= arr.length * (arr.length - 1) / 2 
// 
// Related Topics 数组 二分查找 堆（优先队列） 👍 103 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int k, length, num1, num2;
    long long limit = 1e7;
    vector<int> nums;

    vector<int> kthSmallestPrimeFraction(vector<int>& arr, int k) { // 二分(整数版)(归并见java)
        this->k = k;
        nums = arr;
        length = (int) nums.size();
        int left = 1, right = (int) limit - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (check(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        // check(left);     // 精度问题,可省略
        return {num1, num2};
    }

    bool check(int target) {
        int cumsum = 0;
        long long temp = target;
        for (int i = length - 2, j = length - 1; j > 0 && i >= 0 && cumsum < k; j--) {
            while (i >= 0 && limit * nums[i] - temp * nums[j] > nums[j]) {
                i--;
            }
            cumsum += i + 1;
            if (i >= 0 && temp * nums[j] - limit * nums[i] <= nums[j]) {    // 仅最后check(left)需要
                num1 = nums[i];
                num2 = nums[j];
            }
        }
        return cumsum >= k;
    }
};

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
public:
    double eps = 1e-8;
    int k, length, num1, num2;
    vector<int> nums;

    vector<int> kthSmallestPrimeFraction(vector<int>& arr, int k) { // 二分(小数版)
        this->k = k;
        nums = arr;
        length = (int) nums.size();
        double left = 0, right = 1;
        while (left < right - eps) {
            double mid = (left + right) / 2;
            if (check(mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return {num1, num2};
    }

    bool check(double target) {
        int cumsum = 0;
        for (int i = length - 2, j = length - 1; j > 0 && i >= 0 && cumsum < k; j--) {
            while (i >= 0 && nums[i] * 1.0 / nums[j] > target) {
                i--;
            }
            cumsum += i + 1;
            if (i >= 0 && abs(nums[i] * 1.0 / nums[j] - target) < eps) {
                num1 = nums[i];
                num2 = nums[j];
            }
        }
        return cumsum >= k;
    }
};
