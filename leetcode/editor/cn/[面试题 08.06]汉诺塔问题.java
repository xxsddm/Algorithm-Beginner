//在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只
//能放在更大的盘子上面)。移动圆盘时受到以下限制: 
//(1) 每次只能移动一个盘子; 
//(2) 盘子只能从柱子顶端滑出移到下一根柱子; 
//(3) 盘子只能叠在比它大的盘子上。 
//
// 请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。 
//
// 你需要原地修改栈。 
//
// 示例1: 
//
//  输入：A = [2, 1, 0], B = [], C = []
// 输出：C = [2, 1, 0]
// 
//
// 示例2: 
//
//  输入：A = [1, 0], B = [], C = []
// 输出：C = [1, 0]
// 
//
// 提示: 
//
// 
// A中盘子的数目不大于14个。 
// 
// Related Topics 递归 数组 👍 110 👎 0

import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        // 列表作为栈使用, 塔顶端元素放在列表末尾
        move(A.size(), A, B, C);
    }

    private void move(int size, List<Integer> A, List<Integer> B, List<Integer> C) {
        if (size == 1) {
            // 结束条件: A的顶端元素移入C(移入后A非空)
            C.add(A.remove(A.size() - 1));
            return;
        }
        // 把上层size-1个元素从A移入备用的B
        move(size - 1, A, C, B);
        // A的底端元素(第size个元素)移入C
        C.add(A.remove(A.size() - 1));  // 此时A非空
        // 把备份的A上层size-1个元素从B移入C
        move(size - 1, B, A, C);
    }
}

//leetcode submit region end(Prohibit modification and deletion)
