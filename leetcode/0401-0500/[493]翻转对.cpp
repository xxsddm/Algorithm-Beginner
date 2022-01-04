//给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。 
//
// 你需要返回给定数组中的重要翻转对的数量。 
//
// 示例 1: 
//
// 
//输入: [1,3,2,3,1]
//输出: 2
// 
//
// 示例 2: 
//
// 
//输入: [2,4,3,5,1]
//输出: 3
// 
//
// 注意: 
//
// 
// 给定数组的长度不会超过50000。 
// 输入数组中的所有数字都在32位整数的表示范围内。 
// 
// Related Topics 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序 👍 310 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int ans = 0, length;
    vector<int> aux;

    int reversePairs(vector<int>& nums) {   // 归并(若左右部分已排序,则便于计数)
        length = (int) nums.size();
        aux = vector<int> (length);
        sort(nums, 0, length - 1);
        return ans;
    }

    void sort(vector<int>& nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) >> 1, idx = start;
        sort(nums, start, mid);
        sort(nums, mid + 1, end);

        // 计算翻转对数量
        for (int right = mid + 1, left = start; right <= end; right++) {
            long long pivot = (long long) nums[right] * 2;
            while (left <= mid && nums[left] <= pivot) {
                left++;
            }
            ans += mid - left + 1;
        }

        for (int i = start; i <= end; i++) {
            aux[i] = nums[i];
        }
        int left = start, right = mid + 1;
        while (true) {
            if (left == mid + 1) {
                while (right <= end) {
                    nums[idx++] = aux[right++];
                }
                break;
            }
            if (right == end + 1) {
                while (left <= mid) {
                    nums[idx++] = aux[left++];
                }
                break;
            }
            if (aux[left] <= aux[right]) {
                nums[idx++] = aux[left++];
            } else {
                nums[idx++] = aux[right++];
            }
        }
    }
};

//leetcode submit region end(Prohibit modification and deletion)
