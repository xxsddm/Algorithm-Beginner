# 在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。 
# 
#  如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记； 
# 
#  而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。 
# 
#  
# 
#  给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。 
# 
#  
# 
#  示例 1： 
# 
#  输入：label = 14
# 输出：[1,3,4,14]
#  
# 
#  示例 2： 
# 
#  输入：label = 26
# 输出：[1,2,6,10,26]
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= label <= 10^6 
#  
#  Related Topics 树 数学 二叉树 
#  👍 85 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def pathInZigZagTree(self, label: int) -> List[int]:
        # 初始化
        maxdepth = 0
        container = []
        while label > 2 ** maxdepth - 1:
            maxdepth += 1
            container.append(0)

        # 计算相应上层信息和底层填入label
        container[-1] = label
        loc = label - 2 ** (maxdepth - 1) + 1
        loc = (loc + 1) // 2    # 如果每层排序顺序相同则上一层位置
        i = 2

        while maxdepth - i >= 0:
            depth = maxdepth - i + 1    # 当前深度
            loc = 2 ** (depth - 1) - loc + 1    # 上一层传递的loc转换为本层loc
            container[maxdepth - i] = 2 ** (depth - 1) + loc - 1    # 本层loc位置的值
            loc = (loc + 1) // 2  # 如果每层排序顺序相同则上一层位置
            i += 1
        return container
# leetcode submit region end(Prohibit modification and deletion)
