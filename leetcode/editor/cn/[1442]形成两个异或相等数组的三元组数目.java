//给你一个整数数组 arr 。 
//
// 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。 
//
// a 和 b 定义如下： 
//
// 
// a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1] 
// b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k] 
// 
//
// 注意：^ 表示 按位异或 操作。 
//
// 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。 
//
// 
//
// 示例 1： 
//
// 输入：arr = [2,3,1,6,7]
//输出：4
//解释：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
// 
//
// 示例 2： 
//
// 输入：arr = [1,1,1,1,1]
//输出：10
// 
//
// 示例 3： 
//
// 输入：arr = [2,3]
//输出：0
// 
//
// 示例 4： 
//
// 输入：arr = [1,3,5,7,9]
//输出：3
// 
//
// 示例 5： 
//
// 输入：arr = [7,11,12,9,5,2,7,17,22]
//输出：8
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 300 
// 1 <= arr[i] <= 10^8 
// 
// Related Topics 位运算 数组 哈希表 数学 前缀和 👍 172 👎 0


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int countTriplets(int[] arr) {
        // 若有两段0~a, 0~b异或值相同, 则a+1(前段部分起点)~b异或值为0(x^0=x), 异或值为0则其任意分隔的两段必然相同
        HashMap<Integer, List<Integer>> container = new HashMap<>();        // 异或值 -> 索引
        int value = 0, count = 0;
        container.put(0, new ArrayList<>());        // 初始化, 考虑前段部分从索引0开始
        container.get(0).add(-1);
        for (int i = 0; i < arr.length; i++) {
            value ^= arr[i];
            if (!container.containsKey(value)) {
                container.put(value, new ArrayList<>());
            }
            else {
                for (int idx: container.get(value)) {
                    count += Math.max(i - idx - 1, 0);      // idx+1~i, j有i-idx-2+1种位置
                }
            }
            container.get(value).add(i);
        }
        return count;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
