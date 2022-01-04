//给定一个字符串，请将字符串里的字符按照出现的频率降序排列。 
//
// 示例 1: 
//
// 
//输入:
//"tree"
//
//输出:
//"eert"
//
//解释:
//'e'出现两次，'r'和't'都只出现一次。
//因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
// 
//
// 示例 2: 
//
// 
//输入:
//"cccaaa"
//
//输出:
//"cccaaa"
//
//解释:
//'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
//注意"cacaca"是不正确的，因为相同的字母必须放在一起。
// 
//
// 示例 3: 
//
// 
//输入:
//"Aabb"
//
//输出:
//"bbAa"
//
//解释:
//此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
//注意'A'和'a'被认为是两种不同的字符。
// 
// Related Topics 哈希表 字符串 桶排序 计数 排序 堆（优先队列） 👍 336 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String frequencySort(String s) {
        // 数量,字符(含大小写字母和数字)
        int[][] counter = new int[128][2];
        for (int i = 0; i < 128; i++) {
            counter[i][1] = i;
        }
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i)][0]++;
        }
        Arrays.sort(counter, (o1, o2) -> (o2[0] - o1[0]));
        StringBuilder sb = new StringBuilder(s.length());
        for (int i = 0; i < 128; i++) {
            for (int j = 0; j < counter[i][0]; j++) {
                sb.append((char) (counter[i][1]));
            }
        }
        return sb.toString();
    }
}

//leetcode submit region end(Prohibit modification and deletion)
