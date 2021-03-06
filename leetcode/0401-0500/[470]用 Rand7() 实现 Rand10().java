//已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。 
//
// 不要使用系统的 Math.random() 方法。 
//
// 
// 
//
// 
//
// 示例 1: 
//
// 
//输入: 1
//输出: [7]
// 
//
// 示例 2: 
//
// 
//输入: 2
//输出: [8,4]
// 
//
// 示例 3: 
//
// 
//输入: 3
//输出: [8,1,10]
// 
//
// 
//
// 提示: 
//
// 
// rand7 已定义。 
// 传入参数: n 表示 rand10 的调用次数。 
// 
//
// 
//
// 进阶: 
//
// 
// rand7()调用次数的 期望值 是多少 ? 
// 你能否尽量少调用 rand7() ? 
// 
// Related Topics 数学 拒绝采样 概率与统计 随机化 👍 228 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */

class Solution extends SolBase {
    public int rand10() {
        int num = rand7(), odd = rand7();
        while (odd == 7) {  // [1, 7]和[1, 10]奇偶概率不同, 需调整
            odd = rand7();  // 判断奇偶性
        }
        while (num > 5) {   // 生成[1, 5]随机数
            num = rand7();
        }
        // [1, 10]任意数概率为0.1
        return 2 * num - (odd <= 3 ? 1 : 0);
    }
}

//leetcode submit region end(Prohibit modification and deletion)
