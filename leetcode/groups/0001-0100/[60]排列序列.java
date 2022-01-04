//给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。 
//
// 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下： 
//
// 
// "123" 
// "132" 
// "213" 
// "231" 
// "312" 
// "321" 
// 
//
// 给定 n 和 k，返回第 k 个排列。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3, k = 3
//输出："213"
// 
//
// 示例 2： 
//
// 
//输入：n = 4, k = 9
//输出："2314"
// 
//
// 示例 3： 
//
// 
//输入：n = 3, k = 1
//输出："123"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 9 
// 1 <= k <= n! 
// 
// Related Topics 递归 数学 👍 569 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int numUsed = 0;
    int[] container;
    StringBuilder sb;

    public String getPermutation(int n, int k) {    // DFS(利用排列数量, 非回溯)
        container = new int[n + 1];
        sb = new StringBuilder(n);
        container[0] = 1;
        for (int i = 1; i <= n; i++) {
            container[i] = i * container[i - 1];
        }
        dfs(k);
        return sb.toString();
    }

    private void dfs(int k) {
        if (k == 1) {
            for (int num = 1; num <= container.length - 1; num++) {
                // 剩下的从小到大加入即可
                if ((numUsed & (1 << num)) == 0) {
                    numUsed |= 1 << num;
                    sb.append(num);
                }
            }
            return;
        }
        // 低位涉及数字数量numLow(较大数字), turns为需要多少轮(numLow!), 剩余数字组合为用其排列的第k%(numLow!)小的数字
        int temp =  maxNoGreater(k);
        int numLow = temp + ((k == container[temp]) ? 0 : 1);
        int turns = k / container[temp], rest = k % container[temp];
        int numHigh = (container.length - 1) - sb.length() - numLow;
        // 加入不用考虑的高位数字(较小数字)(优先加入小的)
        for (int num = 1, count = 0; count < numHigh; num++) {
            if ((numUsed & (1 << num)) == 0) {
                numUsed |= 1 << num;
                sb.append(num);
                count++;
            }
        }

        // 若余数rest为0
        // turns>1则k正好为(temp!*turns), 则先填入第turns小的数字(代表(numLow!)进行了turns轮), 剩余数字从大到小加入即可
        // turns=1则k正好为(temp!)剩余数字从大到小加入即可
        if (rest == 0) {
            if (turns > 1) {
                for (int num = 1, count = 1; count <= turns; num++) {
                    if ((numUsed & (1 << num)) == 0) {
                        if (count == turns) {
                            numUsed |= 1 << num;
                            sb.append(num);
                            break;
                        }
                        count++;
                    }
                }
            }
            for (int num = container.length - 1; num >= 1; num--) {
                // 剩下的从大到小加入即可
                if ((numUsed & (1 << num)) == 0) {
                    numUsed |= 1 << num;
                    sb.append(num);
                }
            }
            return;
        }

        // rest!=0, 未加入的低位数字(较大数字)中, 第turns+1小的放在低位部分的最高位(左端)
        for (int num = 1, count = 0; count < turns + 1; num++) {
            if ((numUsed & (1 << num)) == 0) {
                if (count == turns) {
                    numUsed |= 1 << num;
                    sb.append(num);
                    break;
                }
                count++;
            }
        }
        dfs(rest);
    }

    private int maxNoGreater(int num) { // 返回的是不大于num的最大阶乘为(返回值!), 故数字右侧(低位)(含其自身)需要(返回值-1)个数
        int left = 1, right = container.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (num > container[mid]) {
                left = mid + 1;
            }
            else if (num < container[mid]) {
                right = mid - 1;
            }
            else {
                return mid;
            }
        }
        return right;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
