//给你两个正整数数组 nums1 和 nums2 ，数组的长度都是 n 。 
//
// 数组 nums1 和 nums2 的 绝对差值和 定义为所有 |v1 - v2|（0 <= i < n）的 总和（下标从 0 开始
//）。 
//
// 你可以选用 nums1 中的 任意一个 元素来替换 nums1 中的 至多 一个元素，以 最小化 绝对差值和。 
//
// 在替换数组 nums1 中最多一个元素 之后 ，返回最小绝对差值和。因为答案可能很大，所以需要对 109 + 7 取余 后返回。 
//
// |x| 定义为： 
//
// 
// 如果 x >= 0 ，值为 x ，或者 
// 如果 x <= 0 ，值为 -x 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,7,5], nums2 = [2,3,5]
//输出：3
//解释：有两种可能的最优方案：
//- 将第二个元素替换为第一个元素：[1,7,5] => [1,1,5] ，或者
//- 将第二个元素替换为第三个元素：[1,7,5] => [1,5,5]
//两种方案的绝对差值和都是 |1-2| + (|1-3| 或者 |5-3|) + |5-5| = 3
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [2,4,6,8,10], nums2 = [2,4,6,8,10]
//输出：0
//解释：nums1 和 nums2 相等，所以不用替换元素。绝对差值和为 0
// 
//
// 示例 3： 
//
// 
//输入：nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]
//输出：20
//解释：将第一个元素替换为第二个元素：[1,10,4,4,2,7] => [10,10,4,4,2,7]
//绝对差值和为 |10-9| + |10-3| + |4-5| + |4-1| + |2-7| + |7-4| = 20
// 
//
// 
//
// 提示： 
//
// 
// n == nums1.length 
// n == nums2.length 
// 1 <= n <= 105 
// 1 <= v1, v2 <= 105 
// 
// Related Topics 贪心 数组 二分查找 有序集合 
// 👍 48 👎 0


import java.util.ArrayList;
import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        long start = 0;     // 还是要考虑溢出
        for (int i=0; i < nums1.length; i++){
            start += Math.abs(nums1[i] - nums2[i]);
        }
        long least = start;
        int[] sort1 = nums1.clone();
        Arrays.sort(sort1);
        for (int i=0; i < nums1.length; i++){
            int v1 = nums1[i];
            int v2 = nums2[i];
            if (v1 - v2 == 0) continue;
            if (sort1[nums1.length-1] <= v2){
                least = Math.min(start-Math.abs(v1-v2)+Math.abs(sort1[nums1.length-1]-v2), least);
                continue;
            }
            if (sort1[0] >= v2){
                least = Math.min(start-Math.abs(v1-v2)+Math.abs(sort1[0]-v2), least);
                continue;
            }
            int left = 0;
            int right = nums1.length - 1;
            int loc = (left + right) / 2;
            while (right >= left){
                if (sort1[loc] > v2) right = loc - 1;
                else if (sort1[loc] < v2) left = loc + 1;
                else{
                    least = Math.min(start - Math.abs(v1 - v2), least);
                    break;
                }
                loc = (left + right) / 2;
            }
            if (Math.abs(sort1[right] - v2) >= Math.abs(v2 - sort1[left])){
                least = Math.min(start - Math.abs(v1 - v2) + Math.abs(sort1[left] - v2), least);
            }

            else{
                least = Math.min(start - Math.abs(v1 - v2) + Math.abs(sort1[right] - v2), least);
            }
        }
        return (int) (least % (Math.pow(10, 9) + 7));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
