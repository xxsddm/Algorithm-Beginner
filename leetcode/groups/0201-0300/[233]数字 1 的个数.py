# 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：n = 13
# 输出：6
#  
# 
#  示例 2： 
# 
#  
# 输入：n = 0
# 输出：0
#  
# 
#  
# 
#  提示： 
# 
#  
#  0 <= n <= 2 * 109 
#  
#  Related Topics 递归 数学 动态规划 
#  👍 237 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def countDigitOne(self, n: int) -> int:
        temp = n                    # 每次除以10后, 得到的值即相对于指针time的高位部分
        time = 0                    # temp除以10的次数, 即从右往左扫描的指针, 从索引0开始
        count = 0
        while temp > 0:
            num = temp // 10 + 1    # 高位部分可以取到的值的种数(考虑0)(不存在更高位时仍成立)
            if temp % 10 > 1:       # 若当前位置取值大于1, 则低位部分可以任意取值(不存在更低位时仍成立)
                num *= 10 ** time   # 高低位组合数量
            else:
                # 若当前位置取值不大于1, 则低位部分在高位取最大值(即原值)时不可取所有值(最多只可取到低位数值部分, 种数则+1)
                # 当前位置(temp % 10)取0和1两种情况可以合并
                num = (temp % 10) * (n % 10 ** time + 1) + (num - 1) * 10 ** time
            count += num
            temp //= 10
            time += 1
        return count

# leetcode submit region end(Prohibit modification and deletion)
