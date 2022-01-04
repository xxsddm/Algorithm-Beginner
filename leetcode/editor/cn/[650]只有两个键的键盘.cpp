//最初记事本上只有一个字符 'A' 。你每次可以对这个记事本进行两种操作： 
//
// 
// Copy All（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。 
// Paste（粘贴）：粘贴 上一次 复制的字符。 
// 
//
// 给你一个数字 n ，你需要使用最少的操作次数，在记事本上输出 恰好 n 个 'A' 。返回能够打印出 n 个 'A' 的最少操作次数。 
//
// 
//
// 示例 1： 
//
// 
//输入：3
//输出：3
//解释：
//最初, 只有一个字符 'A'。
//第 1 步, 使用 Copy All 操作。
//第 2 步, 使用 Paste 操作来获得 'AA'。
//第 3 步, 使用 Paste 操作来获得 'AAA'。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 1000 
// 
// Related Topics 数学 动态规划 👍 321 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int minSteps(int n) {   // DFS
        for (int divisor = 2; divisor <= n / divisor; divisor++) {
            if ((n % divisor) == 0) {
                // a*b=n,可DFS生成a,再(1)复制,(b-1)粘贴组成n
                // 优先生成最大的a,便于对a分解,且最小化对a的复制粘贴(b次)次数
                return minSteps(n / divisor) + divisor;
            }
        }
        // 无法继续分解, (1)复制+(n-1)粘贴 组成n
        // n=1则直接返回0(如果输入不为1, 则可省略)
        return n > 1 ? n : 0;
    }
};

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
public:
    int minSteps(int n) {   // 由DFS观察可得(注意退出循环条件)
        int count = 0;
        bool skip = false;
        while (!skip) {
            if (n < 4) {
                break;
            }
            for (int divisor = 2; divisor <= n / divisor; divisor++) {
                if ((n % divisor) == 0) {
                    // a*b=n,可DFS生成a,再(1)复制,(b-1)粘贴组成n
                    // 优先生成最大的a,便于对a分解,且最小化对a的复制粘贴(b次)次数
                    count += divisor;
                    n /= divisor;
                    break;
                }
                if (divisor + 1 >= n / (divisor + 1)) {
                    skip = true;
                }
            }
        }
        // 无法继续分解, (1)复制+(n-1)粘贴 组成n
        // n=1则直接返回0(如果输入不为1, 则可省略)
        if (n > 1) {
            count += n;
        }
        return count;
    }
};

class Solution {
public:
    int minSteps(int n) {   // 质因数分解(参考官方题解)
        int count = 0;
        // 若原始n的因数divisor不是质数, 则n一定由较小的质数prime(prime是divisor的因数)除到无法继续除
        for (int divisor = 2; divisor <= n / divisor; divisor++) {
            while (n % divisor == 0) {
                n /= divisor;
                count += divisor;
            }
        }
        // 考虑最后无法继续分解的质数
        if (n > 1) {
            count += n;
        }
        return count;
    }
};
