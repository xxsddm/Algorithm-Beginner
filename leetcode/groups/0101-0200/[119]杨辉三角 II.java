//给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。 
//
// 在「杨辉三角」中，每个数是它左上方和右上方的数的和。 
//
// 
//
// 
//
// 示例 1: 
//
// 
//输入: rowIndex = 3
//输出: [1,3,3,1]
// 
//
// 示例 2: 
//
// 
//输入: rowIndex = 0
//输出: [1]
// 
//
// 示例 3: 
//
// 
//输入: rowIndex = 1
//输出: [1,1]
// 
//
// 
//
// 提示: 
//
// 
// 0 <= rowIndex <= 33 
// 
//
// 
//
// 进阶： 
//
// 你可以优化你的算法到 O(rowIndex) 空间复杂度吗？ 
// Related Topics 数组 动态规划 👍 318 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> getRow(int rowIndex) { // 杨辉三角的排列组合
        int length = rowIndex + 1;
        List<Integer> container = new ArrayList<>(length);
        container.add(1);
        // 前半部分需计算
        for (int i = 1; i < (length + 1) >> 1; i++) {
            container.add((int) ((long) container.get(i - 1) * (rowIndex - i + 1) / i));
        }
        // 后半部分直接复制前半部分
        for (int i = container.size(); i < length; i++) {
            container.add(container.get(length - i - 1));
        }
        return container;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
