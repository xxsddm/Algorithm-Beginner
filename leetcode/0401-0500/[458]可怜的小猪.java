//有 buckets 桶液体，其中 正好 有一桶含有毒药，其余装的都是水。它们从外观看起来都一样。为了弄清楚哪只水桶含有毒药，你可以喂一些猪喝，通过观察猪是否
//会死进行判断。不幸的是，你只有 minutesToTest 分钟时间来确定哪桶液体是有毒的。 
//
// 喂猪的规则如下： 
//
// 
// 选择若干活猪进行喂养 
// 可以允许小猪同时饮用任意数量的桶中的水，并且该过程不需要时间。 
// 小猪喝完水后，必须有 minutesToDie 分钟的冷却时间。在这段时间里，你只能观察，而不允许继续喂猪。 
// 过了 minutesToDie 分钟后，所有喝到毒药的猪都会死去，其他所有猪都会活下来。 
// 重复这一过程，直到时间用完。 
// 
//
// 给你桶的数目 buckets ，minutesToDie 和 minutesToTest ，返回在规定时间内判断哪个桶有毒所需的 最小 猪数。 
//
// 
//
// 示例 1： 
//
// 
//输入：buckets = 1000, minutesToDie = 15, minutesToTest = 60
//输出：5
// 
//
// 示例 2： 
//
// 
//输入：buckets = 4, minutesToDie = 15, minutesToTest = 15
//输出：2
// 
//
// 示例 3： 
//
// 
//输入：buckets = 4, minutesToDie = 15, minutesToTest = 30
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= buckets <= 1000 
// 1 <= minutesToDie <= minutesToTest <= 100 
// 
// Related Topics 数学 动态规划 组合数学 👍 297 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int poorPigs(int k, int minutesToDie, int minutesToTest) {
        // t轮限定,k个桶,则所需最少数量时,正好结束t轮
        // 假设需要数量n,则每个死前经历轮数为1~t,以及不死共t+1种
        // 则总共有(t+1)^n种组合,需要用这些组合数表示k种情况(目标分布有k种情况)
        // 应满足(t+1)^n>=k才能表示所有可能的情况
        int t = minutesToTest / minutesToDie;
        double temp = Math.log(k) / Math.log(t + 1);
        int n = (int) temp;
        if (Math.pow(t + 1, n) < k) {
            n++;
        }
        return n;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
