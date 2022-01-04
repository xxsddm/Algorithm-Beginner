//「力扣挑战赛」中有一个由 `N` 个关卡组成的**环形**闯关游戏，关卡编号为 `0`~`N-1`，编号 `0` 的关卡和编号 `N-1` 的关卡相邻。每个
//关卡均有积分要求，`challenge[i]` 表示挑战编号 `i` 的关卡最少需要拥有的积分。
//![图片.png](https://pic.leetcode-cn.com/1630392170-ucncVS-%E5%9B%BE%E7%89%87.
//png){:width="240px"}
//
//
//小扣想要挑战关卡，闯关的具体规则如下：
//
//- 初始小扣可以指定其中一个关卡为「开启」状态，其余关卡将处于「未开启」状态。
//- 小扣可以挑战处于「开启」状态且**满足最少积分要求**的关卡，若小扣挑战该关卡前积分为 `score`，挑战结束后，积分将增长为 `score|
//challenge[i]`（即位运算中的 `"OR"` 运算）
//- 在挑战某个关卡后，该关卡两侧相邻的关卡将会开启（若之前未开启）
//
//请帮助小扣进行计算，初始最少需要多少积分，可以挑战 **环形闯关游戏** 的所有关卡。
//
//**示例1：**
//
//> 输入：`challenge = [5,4,6,2,7]`
//>
//> 输出：`4`
//> 
//> 解释： 初始选择编号 3 的关卡开启，积分为 4
//>挑战编号 3 的关卡，积分变为 $4 | 2 = 6$，开启 2、4 处的关卡
//>挑战编号 2 的关卡，积分变为 $6 | 6 = 6$，开启 1 处的关卡
//>挑战编号 1 的关卡，积分变为 $6 | 4 = 6$，开启 0 处的关卡
//>挑战编号 0 的关卡，积分变为 $6 | 5 = 7$
//>挑战编号 4 的关卡，顺利完成全部的关卡
//
//
//**示例2：**
//
//> 输入：`challenge = [12,7,11,3,9]`
//>
//> 输出：`8`
//>
//> 解释： 初始选择编号 3 的关卡开启，积分为 8
//>挑战编号 3 的关卡，积分变为 $8 | 3 = 11$，开启 2、4 处的关卡
//>挑战编号 2 的关卡，积分变为 $11 | 11 = 11$，开启 1 处的关卡
//>挑战编号 4 的关卡，积分变为 $11 | 9 = 11$，开启 0 处的关卡
//>挑战编号 1 的关卡，积分变为 $11 | 7 = 15$
//>挑战编号 0 的关卡，顺利完成全部的关卡
//
//**示例3：**
//
//> 输入：`challenge = [1,1,1]`
//>
//> 输出：`1`
//
//**提示：** 
//- `1 <= challenge.length <= 5*10^4`
//- `1 <= challenge[i] <= 10^18` 👍 1 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int length;
    long[] nums;

    public long ringGame(long[] challenge) {    // 从数组最大数的最高二进制位次开始枚举每一位是否必须为1
        length = challenge.length;
        if (length == 1) {
            return challenge[0];
        }
        nums = challenge;
        // challenge中最大数字的二进制最高位必然取1才有机会完整遍历
        int maxIdx = 62;    // 二进制最高位次(从右往左第maxIdx+1位数字为1(1<<maxIdx))
        long maxnum = 0, ans = (long) 1 << maxIdx;
        for (long num: nums) {
            maxnum = Math.max(maxnum, num);
        }
        while (ans > maxnum) {
            ans >>= 1;
            maxIdx--;
        }
//        if (maxIdx == 0) {
//            return 1;
//        }

        // 依次枚举最高位之后的各个位次是否为1(二进制)
        for (int i = maxIdx - 1; i >= 0; i--) {
            // 检查该位次,则更小的位次全部取1(即较小位次取最大值)
            long temp = ans ^ ((1L << i) - 1);
            // 该位次取0时若不存在可以完整遍历的情况,则该位次必须为1
            if (!check(temp)) {
                ans ^= 1L << i;
            }
        }

        return ans;
    }

    // 考虑过使用并查集,但效果很差
    // 这里同时处理next和prev对start剩余可能取值范围的缩减
    private boolean check(long num) {   // 输入的num是否可以作为初始值遍历challenge
        int start = 0, limit = length;
        while (start < limit){
            long curr = num;
            if (curr < nums[start]) {
                start++;
                continue;
            }
            curr |= nums[start];
            // prev:待考虑的前节点; next:待考虑的后节点
            int prev = getIdx(start - 1), next = getIdx(start + 1);
            while (prev != next) {
                if (curr >= nums[prev]) {
                    curr |= nums[prev];
                    prev = getIdx(prev - 1);
                }
                else if (curr >= nums[next]) {
                    curr |= nums[next];
                    next = getIdx(next + 1);
                }
                else  {
                    break;
                }
            }
            if (prev != next) {
                // 被prev~next覆盖的区域不必作为start点(若next<prev)
                if (prev > start) {
                    // 若prev穿过首端从末端开始,则prev穿过部分作为start也无法遍历数组
                    limit = Math.min(limit, prev);
                }
                if (next <= start) {
                    // next可能穿过末端从首端开始,而next则已由之前较小start作为起点尝试过
                    // start~nums.length-1被包含在本次遍历可达范围,也不需要作为start
                    return false;
                }
                start = next + 1;
                continue;
            }
            return curr >= nums[next];
        }
        return false;
    }

    private int getIdx(int idx) {   // 返回根据challenge长度修正的环形索引
        if (idx == -1) {
            return length - 1;
        }
        if (idx == length) {
            return 0;
        }
        return idx;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
