//请你设计一个数据结构，它能求出给定子数组内一个给定值的 频率 。 
//
// 子数组中一个值的 频率 指的是这个子数组中这个值的出现次数。 
//
// 请你实现 RangeFreqQuery 类： 
//
// 
// RangeFreqQuery(int[] arr) 用下标从 0 开始的整数数组 arr 构造一个类的实例。 
// int query(int left, int right, int value) 返回子数组 arr[left...right] 中 value 的 频
//率 。 
// 
//
// 一个 子数组 指的是数组中一段连续的元素。arr[left...right] 指的是 nums 中包含下标 left 和 right 在内 的中间一段连续
//元素。 
//
// 
//
// 示例 1： 
//
// 输入：
//["RangeFreqQuery", "query", "query"]
//[[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]
//输出：
//[null, 1, 2]
//
//解释：
//RangeFreqQuery rangeFreqQuery = new RangeFreqQuery([12, 33, 4, 56, 22, 2, 34, 
//33, 22, 12, 34, 56]);
//rangeFreqQuery.query(1, 2, 4); // 返回 1 。4 在子数组 [33, 4] 中出现 1 次。
//rangeFreqQuery.query(0, 11, 33); // 返回 2 。33 在整个子数组中出现 2 次。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 10⁵ 
// 1 <= arr[i], value <= 10⁴ 
// 0 <= left <= right < arr.length 
// 调用 query 不超过 10⁵ 次。 
// 
// Related Topics 设计 线段树 数组 哈希表 二分查找 👍 18 👎 0

import java.util.ArrayList;

//leetcode submit region begin(Prohibit modification and deletion)
class RangeFreqQuery {
    ArrayList<Integer>[] nums = (ArrayList<Integer>[]) new ArrayList[10001];

    public RangeFreqQuery(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (nums[num] == null) {
                nums[num] = new ArrayList<>();
            }
            nums[num].add(i);
        }
    }

    public int query(int left, int right, int value) {
        if (nums[value] == null || nums[value].get(0) > right) {
            return 0;
        }
        int idxRight = bisect(nums[value], right, -1);
        int idxLeft = left < nums[value].get(0) ? -1 : bisect(nums[value], left - 1, idxRight);
        return idxRight - idxLeft;
    }

    int bisect(ArrayList<Integer> arr, int target, int limit) {    // 二分
        int left = 0, right = limit > 0 ? limit : (arr.size() - 1);
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (arr.get(mid) > target) {
                right = mid - 1;
            } else if (arr.get(mid) < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return right;
    }
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery obj = new RangeFreqQuery(arr);
 * int param_1 = obj.query(left,right,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
