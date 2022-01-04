//给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。 
//
// 如果数组元素个数小于 2，则返回 0。 
//
// 示例 1: 
//
// 输入: [3,6,9,1]
//输出: 3
//解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。 
//
// 示例 2: 
//
// 输入: [10]
//输出: 0
//解释: 数组元素个数小于 2，因此返回 0。 
//
// 说明: 
//
// 
// 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。 
// 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。 
// 
// Related Topics 数组 桶排序 基数排序 排序 👍 407 👎 0

import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maximumGap(int[] nums) { // 基数排序(测试)
        if (nums.length <= 1) {
            return 0;
        }
        int divisor = 1, maxNum = 0, maxgap = 0;
        int[] cache1 = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            maxNum = Math.max(maxNum, nums[i]);
            cache1[i] = nums[i];
        }
        while (maxNum >= divisor) {
            int[] counter = new int[11];
            int[] cache2 = new int[nums.length];
            for (int num: nums) {
                // 计算索引
                counter[(num / divisor) % 10 + 1]++;
            }
            for (int i = 1; i < 9; i++) {
                counter[i + 1] += counter[i];
            }
            for (int num: cache1) {
                cache2[counter[(num / divisor) % 10]++] = num;
            }
            cache1 = cache2;
            divisor *= 10;
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = cache1[i];
        }
        for (int i = 1; i < nums.length; i++) {
            maxgap = Math.max(maxgap, nums[i] - nums[i - 1]);
        }
        return maxgap;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public int maximumGap(int[] nums) { // 桶排序测试
        sort(nums, 500);
        int maxgap = 0;
        for (int i = 1; i < nums.length; i++) {
            maxgap = Math.max(maxgap, nums[i] - nums[i - 1]);
        }
        return maxgap;
    }
    public static void sort(int[] nums) {
        sort(nums, 10);
    }

    public static void sort(int[] nums, int step) { // 桶排序(可视为计数排序的推广)(仅适用于整数)
        if (nums.length == 0) {
            return;
        }
        // idx为nums中当前待填入排序元素的索引
        int minNum = nums[0], maxNum = nums[0], idx = 0;
        for (int num: nums) {
            minNum = Math.min(minNum, num);
            maxNum = Math.max(maxNum, num);
        }
        // 归一化, 根据相对位置选择不同的桶
        int numBucket = (maxNum - minNum) / step + 1;
        Stack<Integer>[] buckets =(Stack<Integer>[]) new Stack[numBucket];
        for (int num: nums) {
            int loc = (num - minNum) / step;
            if (buckets[loc] == null) {
                buckets[loc] = new Stack<>();
            }
            buckets[loc].add(num);
        }
        for (Stack<Integer> bucket: buckets) {
            if (bucket == null) {
                continue;
            }
            int[] temp = new int[bucket.size()];
            for (int i = 0; i < temp.length; i++) {
                temp[i] = bucket.pop();
            }
            // 桶内单独排序, 这里使用插入排序
            // InsertSort.sort(temp);
            for (int i = 1; i < temp.length; i++) {
                // 插入排序将较小值向前交换
                while (i > 0 && temp[i] < temp[i - 1]) {
                    int back = temp[i];
                    temp[i] = temp[i - 1];
                    temp[i - 1] = back;
                    i--;
                }
            }
            for (int num: temp) {
                nums[idx++] = num;
            }
        }
    }
}

class Solution {
    public int maximumGap(int[] nums) { // 桶排序(不进行桶内排序, 只计算每个桶内最大最小值)
        if (nums.length <= 1) {
            return 0;
        }
        int minNum = nums[0], maxNum = nums[0], maxgap = 0;
        int prev = Integer.MAX_VALUE, bucketMax = 0, bucketMin;
        for (int num: nums) {
            minNum = Math.min(minNum, num);
            maxNum = Math.max(maxNum, num);
        }
        // 差值设置为n-1个值的平均差, 最大差值一定出现在两桶之间
        int distance = Math.max((maxNum - minNum) / (nums.length - 1), 1);
        int numBucket = (maxNum - minNum) / distance + 1;
        Stack<Integer>[] buckets =(Stack<Integer>[]) new Stack[numBucket];
        for (int num: nums) {
            int loc = (num - minNum) / distance;
            if (buckets[loc] == null) {
                buckets[loc] = new Stack<>();
            }
            buckets[loc].add(num);
        }
        for (Stack<Integer> bucket: buckets) {
            if (bucket == null) {
                continue;
            }
            bucketMin = Integer.MAX_VALUE;
            while (!bucket.isEmpty()) {
                int temp = bucket.pop();
                bucketMax = Math.max(bucketMax, temp);
                bucketMin = Math.min(bucketMin, temp);
            }
            maxgap = Math.max(maxgap, bucketMin - prev);
            prev = bucketMax;
        }
        return maxgap;
    }
}
