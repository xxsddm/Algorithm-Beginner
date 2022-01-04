# 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。 
# 
#  
# 
#  示例： 
# 
#  输入：
# 
#    1
#     \
#      3
#     /
#    2
# 
# 输出：
# 1
# 
# 解释：
# 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
#  
# 
#  
# 
#  提示： 
# 
#  
#  树中至少有 2 个节点。 
#  本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/ 
# 相同 
#  
#  Related Topics 树 深度优先搜索 广度优先搜索 二叉搜索树 二叉树 👍 265 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right


class Solution:
    def getMinimumDifference(self, root: TreeNode) -> int:      # 递归
        self.before = 200000
        self.mindiff = 200000
        self.dfs(root)
        return self.mindiff

    def dfs(self, node):
        if node.left:
            self.dfs(node.left)
        temp = abs(node.val - self.before)
        if temp < self.mindiff:
            self.mindiff = temp
        self.before = node.val
        if node.right:
            self.dfs(node.right)

# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def getMinimumDifference(self, root: TreeNode) -> int:      # 迭代
        before = 200000
        mindiff = 200000
        container = [root]
        while container:
            node = container.pop()
            if node:
                if node.right:
                    container.append(node.right)
                container.append(node)
                container.append(None)
                if node.left:
                    container.append(node.left)
            else:
                node = container.pop()
                if (temp := abs(node.val - before)) < mindiff:
                    mindiff = temp
                before = node.val
        return mindiff
